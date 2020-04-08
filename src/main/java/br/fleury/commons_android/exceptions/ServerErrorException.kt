package br.com.fleury.commons.exceptions

class ServerErrorException : Exception{
    constructor() : super()
    @Suppress("unused")
    constructor(message: String?) : super(message)
}