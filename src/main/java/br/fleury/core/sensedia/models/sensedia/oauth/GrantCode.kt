package br.com.fleury.core.sensedia.models.sensedia.oauth

import com.google.gson.annotations.SerializedName

class GrantCode(@SerializedName("redirect_uri") val uriCode: String) {

    fun getCode(): String {
        return uriCode.replace(CODE_URI, "", true)
    }

    companion object {
        /**
         * Constante criada para remover a parte referente a Url que chega com a resposta da requisição para
         * o Authorization Code, pois para o próximo serviço chamado precisamos apenas do Code (número após a String)
         */
        private const val CODE_URI = "http://localhost/?code="
    }
}