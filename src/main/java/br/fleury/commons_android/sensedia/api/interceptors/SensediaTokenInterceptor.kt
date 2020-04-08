package br.com.fleury.commons.sensedia.api.interceptors

import br.com.fleury.commons.exceptions.ServerErrorException
import br.com.fleury.commons.sensedia.ISensediaConfigurations
import br.com.fleury.commons.sensedia.exceptions.InvalidClientIdException
import br.com.fleury.commons.sensedia.exceptions.SensediaAuthException
import br.com.fleury.commons.sensedia.models.sensedia.oauth.AccessToken
import br.com.fleury.commons.sensedia.models.sensedia.oauth.AccessTokenRequest
import br.com.fleury.commons.sensedia.models.sensedia.oauth.GrantCode
import br.com.fleury.commons.sensedia.models.sensedia.oauth.GrantCodeRequest
import br.com.fleury.commons.sensedia.repository.SensediaRepository
import br.com.fleury.commons.sensedia.repository.contracts.ISensediaRepository
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.net.HttpURLConnection

class SensediaTokenInterceptor(
    private val sensediaConfigurations: ISensediaConfigurations,
    private val repository: ISensediaRepository,
    private val gson: Gson
) : Interceptor {
    companion object {
        fun create(
            sensediaConfigurations: ISensediaConfigurations,
            repository: ISensediaRepository,
            gson: Gson
        ): SensediaTokenInterceptor {
            return SensediaTokenInterceptor(sensediaConfigurations, repository, gson)
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val responseRequest = chain.request()

        return if (repository.isAuthenticated) {
            val newRequest = setAccessToken(responseRequest)
            chain.proceed(newRequest.build())
        } else {
            val client = OkHttpClient
                .Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()

            val grantCodeRequest = GrantCodeRequest(
                sensediaConfigurations.clientId,
                SensediaRepository.REDIRECT_URI
            )

            val grantCodeRequestJson = gson.toJson(grantCodeRequest)
            val contentType = "application/json; charset=utf-8".toMediaTypeOrNull()
            val requestBody = grantCodeRequestJson.toRequestBody(contentType)

            val grantCodeRequestBuilder = responseRequest.newBuilder()
            grantCodeRequestBuilder.url("${sensediaConfigurations.baseUrl}/oauth/grant-code")
            grantCodeRequestBuilder.post(requestBody)
            val grantCodeResponse = client.newCall(grantCodeRequestBuilder.build()).execute()

            if (grantCodeResponse.isSuccessful) {
                grantCodeResponse.body?.let { grantCodeBody ->
                    val grantCode = gson.fromJson(grantCodeBody.string(), GrantCode::class.java)

                    val accessTokenRequest = AccessTokenRequest(
                        code = grantCode.getCode(),
                        grantType = AccessTokenRequest.GRANT_TYPE_AUTHORIZATION_CODE
                    )

                    val accessTokenRequestJson = gson.toJson(accessTokenRequest)
                    val accessTokenRequestBody =
                        accessTokenRequestJson.toRequestBody(contentType)

                    val accessTokenRequestBuilder = responseRequest.newBuilder()
                    accessTokenRequestBuilder.url("${sensediaConfigurations.baseUrl}/oauth/access-token")
                    accessTokenRequestBuilder.addHeader(
                        "Authorization",
                        sensediaConfigurations.credentials
                    )
                    accessTokenRequestBuilder.post(accessTokenRequestBody)
                    val accessTokenResponse =
                        client.newCall(accessTokenRequestBuilder.build()).execute()

                    when {
                        accessTokenResponse.isSuccessful -> {
                            accessTokenResponse.body?.let { accessTokenBody ->
                                val accessToken =
                                    gson.fromJson(accessTokenBody.string(), AccessToken::class.java)

                                repository.accessToken = accessToken
                            }
                        }
                        accessTokenResponse.code == HttpURLConnection.HTTP_BAD_REQUEST -> {
                            throw InvalidClientIdException()
                        }
                        accessTokenResponse.code == HttpURLConnection.HTTP_INTERNAL_ERROR -> {
                            throw ServerErrorException()
                        }
                        else -> throw SensediaAuthException()
                    }
                }
            }

            val newRequest = setAccessToken(responseRequest)
            chain.proceed(newRequest.build())
        }
    }

    private fun setAccessToken(request: Request): Request.Builder {
        return request.newBuilder()
            .header("access_token", repository.accessToken?.accessToken ?: "")
            .header("client_id", sensediaConfigurations.clientId)
    }
}