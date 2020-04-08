package br.com.fleury.commons_android.exceptions

class ServerErrorException : Exception{
    constructor() : super()
    @Suppress("unused")
    constructor(message: String?) : super(message)
}