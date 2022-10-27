package io.github.messiasjunior.whatsupdawg.core.network

data class BreedsResponse(
    val message: Map<String, List<String>>,
    val status: String,
)
