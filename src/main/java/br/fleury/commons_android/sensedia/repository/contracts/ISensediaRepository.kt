package br.com.fleury.commons_android.sensedia.repository.contracts

import br.com.fleury.commons_android.sensedia.models.sensedia.oauth.AccessToken

interface ISensediaRepository {
    var accessToken: AccessToken?
    val isAuthenticated: Boolean
}