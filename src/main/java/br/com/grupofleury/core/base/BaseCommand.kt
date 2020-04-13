package br.com.grupofleury.core.base

@Suppress("unused")
open class BaseCommand {
    class NoContent : BaseCommand()
    class Unauthorized : BaseCommand()
    class Created : BaseCommand()
    class Success(val value: Any) : BaseCommand()
    class Error(val message: String? = null) : BaseCommand()
    class TimeOut : BaseCommand()
    class NotFound : BaseCommand()
    class Forbidden : BaseCommand()
    class ServerError(val message: String? = null) : BaseCommand()
    class PreConditionFail(val message: String? = null) : BaseCommand()
}