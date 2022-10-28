package io.github.messiasjunior.whatsupdawg.core.network.model

data class BreedsResponse(
    val message: Map<String, List<String>>,
    val status: String,
)
