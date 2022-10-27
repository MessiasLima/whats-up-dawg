package io.github.messiasjunior.whatsupdawg.core.network

data class Response(
    val message: Map<String, List<String>>,
    val status: String,
)
