package br.com.fleury.commons_android.sensedia.models.sensedia.oauth

import com.google.gson.annotations.SerializedName

data class AccessTokenRequest(
    @SerializedName("code") val code: String? = null,
    @SerializedName("refresh_token") val refreshToken: String? = null,
    @SerializedName("grant_type") val grantType: String
) {
    companion object {
        const val GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code"
        const val GRANT_TYPE_REFRESH_TOKEN = "refresh_token"
    }
}