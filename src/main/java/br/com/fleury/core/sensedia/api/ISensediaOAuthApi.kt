package br.com.fleury.core.sensedia.api

import br.com.fleury.core.sensedia.models.sensedia.oauth.AccessToken
import br.com.fleury.core.sensedia.models.sensedia.oauth.AccessTokenRequest
import br.com.fleury.core.sensedia.models.sensedia.oauth.GrantCode
import br.com.fleury.core.sensedia.models.sensedia.oauth.GrantCodeRequest
import retrofit2.Call
import retrofit2.http.*

@Suppress("unused")
interface ISensediaOAuthApi {
    @POST("oauth/grant-code")
    fun getGrantCode(
        @Body grantCodeRequest: GrantCodeRequest
    ): Call<GrantCode>

    @POST("oauth/access-token")
    fun getAccessToken(
        @Header("Authorization") authorization: String,
        @Body accessTokenRequest: AccessTokenRequest
    ): Call<AccessToken>

    @POST("oauth/access-token")
    fun getRefreshToken(
        @Header("Authorization") authorization: String,
        @Body accessTokenRequest: AccessTokenRequest
    ): Call<AccessToken>
}