package io.github.messiasjunior.whatsupdawg.feature.breeds.usecase

import io.github.messiasjunior.whatsupdawg.core.network.model.BreedsResponse
import io.github.messiasjunior.whatsupdawg.core.util.capitalizeFirstChar
import io.github.messiasjunior.whatsupdawg.domain.Breed
import javax.inject.Inject

class BreedDomainMapper @Inject constructor(
    private val subBreedDomainMapper: SubBreedDomainMapper
) {
    fun map(breedsResponse: BreedsResponse): List<Breed> {
        return breedsResponse.message.entries.map {
            mapEntryToBreed(it)
        }
    }

    private fun mapEntryToBreed(mapEntry: Map.Entry<String, List<String>>) =
        Breed(
            id = mapEntry.key,
            name = mapEntry.key.capitalizeFirstChar(),
            subBreeds = mapEntry.value.map {
                subBreedDomainMapper.map(breedId = mapEntry.key, subBreedId = it)
            }
        )
}
