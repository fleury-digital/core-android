package br.com.fleury.commons.sensedia.api.interceptors

import br.com.fleury.commons.sensedia.ISensediaConfigurations
import br.com.fleury.commons.sensedia.models.sensedia.oauth.AccessToken
import br.com.fleury.commons.sensedia.models.sensedia.oauth.AccessTokenRequest
import br.com.fleury.commons.sensedia.repository.contracts.ISensediaRepository
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.net.HttpURLConnection

class SensediaAuthenticator(
    private val sensediaConfigurations: ISensediaConfigurations,
    private val repository: ISensediaRepository,
    private val gson: Gson
) : Authenticator {

    companion object {
        fun create(
            sensediaConfigurations: ISensediaConfigurations,
            repository: ISensediaRepository,
            gson: Gson
        ): SensediaAuthenticator {
            return SensediaAuthenticator(sensediaConfigurations, repository, gson)
        }
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        val responseRequest = response.request

        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            val client = OkHttpClient
                .Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()

            val accessTokenRequest = AccessTokenRequest(
                refreshToken = repository.accessToken?.refreshToken,
                grantType = AccessTokenRequest.GRANT_TYPE_REFRESH_TOKEN
            )

            val json = gson.toJson(accessTokenRequest)
            val contentType = "application/json; charset=utf-8".toMediaTypeOrNull()
            val requestBody = json.toRequestBody(contentType)

            val refreshTokenRequest = responseRequest.newBuilder()
            refreshTokenRequest.url("${sensediaConfigurations.baseUrl}/oauth/access-token")
            refreshTokenRequest.addHeader("Authorization", sensediaConfigurations.credentials)
            refreshTokenRequest.post(requestBody)
            val refreshTokenResponse = client.newCall(refreshTokenRequest.build()).execute()

            if (refreshTokenResponse.isSuccessful) {
                refreshTokenResponse.body?.let { accessTokenBody ->
                    val accessToken =
                        gson.fromJson(accessTokenBody.string(), AccessToken::class.java)

                    repository.accessToken = accessToken

                    return responseRequest
                        .newBuilder()
                        .header("access_token", accessToken.accessToken)
                        .header("client_id", sensediaConfigurations.clientId)
                        .build()
                }
            }

            return null
        }
//        else if (response.code() == HttpURLConnection.HTTP_FORBIDDEN) {
//
//        }

        return responseRequest
    }

}