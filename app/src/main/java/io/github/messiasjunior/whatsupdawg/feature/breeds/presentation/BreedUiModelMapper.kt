package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import io.github.messiasjunior.whatsupdawg.domain.Breed
import javax.inject.Inject

class BreedUiModelMapper @Inject constructor() {
    fun map(domainModels: List<Breed>) = domainModels.map {
        BreedUiModel(
            id = it.id,
            name = it.name,
            imageUrl = it.image
        )
    }
}
