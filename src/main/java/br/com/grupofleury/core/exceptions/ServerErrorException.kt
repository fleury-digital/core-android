package br.com.grupofleury.core.exceptions

class ServerErrorException : Exception{
    constructor() : super()
    @Suppress("unused")
    constructor(message: String?) : super(message)
}