package krsoftware.mapotato

/**
 * Created by silver_android on 30/09/17.
 */
enum class StatusEnum(val string: String) {
    OK("OK"), ZERO_RESULTS("ZERO_RESULTS"), OVER_QUERY_LIMIT("OVER_QUERY_LIMIT"), REQUEST_DENIED("REQUEST_DENIED"), INVALID_REQUEST("INVALID_REQUEST")
}