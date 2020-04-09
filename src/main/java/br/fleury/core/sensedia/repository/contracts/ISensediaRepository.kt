package br.com.fleury.core.sensedia.repository.contracts

import br.com.fleury.core.sensedia.models.sensedia.oauth.AccessToken

interface ISensediaRepository {
    var accessToken: AccessToken?
    val isAuthenticated: Boolean
}