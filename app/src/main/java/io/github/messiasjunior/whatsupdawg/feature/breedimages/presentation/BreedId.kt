package io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation

import androidx.lifecycle.SavedStateHandle
import io.github.messiasjunior.whatsupdawg.feature.breedimages.BREED_IMAGES_ARGUMENT

@JvmInline
value class BreedId(val value: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        savedStateHandle.get<String>(BREED_IMAGES_ARGUMENT)!!
    )
}
