package io.github.messiasjunior.whatsupdawg.domain

data class Breed(
    val id: String,
    val name: String,
    val subBreeds: List<SubBreed>
)
