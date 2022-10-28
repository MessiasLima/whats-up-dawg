package io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation

import io.github.messiasjunior.whatsupdawg.core.util.capitalizeFirstChar
import io.github.messiasjunior.whatsupdawg.domain.SubBreed
import javax.inject.Inject

class BreedImagesUiModelMapper @Inject constructor() {
    fun map(subBreeds: List<SubBreed>) = subBreeds.map {
        BreedImagesUiModel(breedName = it.name, images = it.images)
    }

    fun mapMainBreedName(breedId: BreedId) = breedId.value.capitalizeFirstChar()
}
