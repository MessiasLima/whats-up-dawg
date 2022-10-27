package io.github.messiasjunior.whatsupdawg.feature.breeds.usecase

import io.github.messiasjunior.whatsupdawg.core.util.capitalizeFirstChar
import io.github.messiasjunior.whatsupdawg.domain.SubBreed
import javax.inject.Inject

class SubBreedDomainMapper @Inject constructor() {
    fun map(breedId: String, subBreedId: String) = SubBreed(
        id = "$breedId/$subBreedId",
        name = "$subBreedId $breedId".capitalizeFirstChar()
    )
}
