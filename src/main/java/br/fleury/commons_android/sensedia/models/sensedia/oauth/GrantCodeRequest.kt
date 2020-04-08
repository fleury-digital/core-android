package br.com.fleury.commons_android.sensedia.models.sensedia.oauth

import com.google.gson.annotations.SerializedName

data class GrantCodeRequest(
    @SerializedName("client_id") val clientId: String,
    @SerializedName("redirect_uri") val redirectUri: String
)