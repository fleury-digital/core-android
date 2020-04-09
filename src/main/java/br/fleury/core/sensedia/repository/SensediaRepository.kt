package br.com.fleury.core.sensedia.repository

import br.com.fleury.core.sensedia.models.sensedia.oauth.AccessToken
import br.com.fleury.core.sensedia.repository.contracts.ISensediaRepository

open class SensediaRepository : ISensediaRepository {

    companion object {
        const val REDIRECT_URI = "http://localhost"
    }

    override var accessToken: AccessToken? = null

    override val isAuthenticated: Boolean
        get() = accessToken?.accessToken?.isNotEmpty() ?: false
}