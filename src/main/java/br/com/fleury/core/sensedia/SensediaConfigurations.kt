package br.com.fleury.core.sensedia

import android.content.Context
import br.com.fleury.core.R
import br.com.fleury.core.sensedia.exceptions.InvalidConfigurationException

open class SensediaConfigurations(val context: Context) :
    ISensediaConfigurations {

    init {
        if (context.getString(R.string.sensediaBaseUrl).isBlank())
            throw InvalidConfigurationException(
                context.getString(
                    R.string.invalidConfigurationException,
                    "sensediaBaseUrl"
                )
            )

        if (context.getString(R.string.sensediaClientId).isBlank())
            throw InvalidConfigurationException(
                context.getString(
                    R.string.invalidConfigurationException,
                    "sensediaClientId"
                )
            )

        if (context.getString(R.string.sensediaCredentials).isBlank())
            throw InvalidConfigurationException(
                context.getString(
                    R.string.invalidConfigurationException,
                    "sensediaCredentials"
                )
            )
    }

    override var baseUrl: String
        get() = context.getString(R.string.sensediaBaseUrl)
        set(@Suppress("UNUSED_PARAMETER") value) {}

    override var clientId: String
        get() = context.getString(R.string.sensediaClientId)
        set(@Suppress("UNUSED_PARAMETER") value) {}

    override var credentials: String
        get() = context.getString(R.string.sensediaCredentials)
        set(@Suppress("UNUSED_PARAMETER") value) {}

}