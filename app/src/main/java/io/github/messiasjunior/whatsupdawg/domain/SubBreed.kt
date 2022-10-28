package io.github.messiasjunior.whatsupdawg.domain

data class SubBreed(
    val id: String,
    val name: String,
    val images: List<String> = emptyList(),
)
