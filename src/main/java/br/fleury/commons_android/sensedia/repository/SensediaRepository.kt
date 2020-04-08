package br.com.fleury.commons.sensedia.repository

import br.com.fleury.commons.sensedia.models.sensedia.oauth.AccessToken
import br.com.fleury.commons.sensedia.repository.contracts.ISensediaRepository

open class SensediaRepository : ISensediaRepository {

    companion object {
        const val REDIRECT_URI = "http://localhost"
    }

    override var accessToken: AccessToken? = null

    override val isAuthenticated: Boolean
        get() = accessToken?.accessToken?.isNotEmpty() ?: false
}