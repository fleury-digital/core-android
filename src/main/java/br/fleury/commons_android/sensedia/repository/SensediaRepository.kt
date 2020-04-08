package br.com.fleury.commons_android.sensedia.repository

import br.com.fleury.commons_android.sensedia.models.sensedia.oauth.AccessToken
import br.com.fleury.commons_android.sensedia.repository.contracts.ISensediaRepository

open class SensediaRepository : ISensediaRepository {

    companion object {
        const val REDIRECT_URI = "http://localhost"
    }

    override var accessToken: AccessToken? = null

    override val isAuthenticated: Boolean
        get() = accessToken?.accessToken?.isNotEmpty() ?: false
}