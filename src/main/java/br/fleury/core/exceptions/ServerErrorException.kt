package br.com.fleury.core.exceptions

class ServerErrorException : Exception{
    constructor() : super()
    @Suppress("unused")
    constructor(message: String?) : super(message)
}