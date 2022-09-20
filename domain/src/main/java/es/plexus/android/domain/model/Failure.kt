package es.plexus.android.domain.model


const val DEFAULT_STRING: String = ""
const val DEFAULT_INTEGER: Int = -1


sealed class Failure(val message: String, val code: Int) {

    class Unauthorized(message: String = DEFAULT_STRING, code: Int = DEFAULT_INTEGER) :
        Failure(message, code)

    class Request(message: String = DEFAULT_STRING, code: Int = DEFAULT_INTEGER) :
        Failure(message, code)

    class NoNetwork(message: String = DEFAULT_STRING, code: Int = DEFAULT_INTEGER) :
        Failure(message, code)

    class Unknown(message: String = DEFAULT_STRING, code: Int = DEFAULT_INTEGER) :
        Failure(message, code)

    class NoData(message: String = DEFAULT_STRING, code: Int = DEFAULT_INTEGER) :
        Failure(message, code)

}