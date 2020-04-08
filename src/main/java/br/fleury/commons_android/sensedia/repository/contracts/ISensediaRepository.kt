package br.com.fleury.commons.sensedia.repository.contracts

import br.com.fleury.commons.sensedia.models.sensedia.oauth.AccessToken

interface ISensediaRepository {
    var accessToken: AccessToken?
    val isAuthenticated: Boolean
}