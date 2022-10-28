package io.github.messiasjunior.whatsupdawg.feature.breedimages.usecase

import io.github.messiasjunior.whatsupdawg.core.network.DogApi
import io.github.messiasjunior.whatsupdawg.core.network.model.SubBreedsResponse
import io.github.messiasjunior.whatsupdawg.core.util.capitalizeFirstChar
import io.github.messiasjunior.whatsupdawg.domain.SubBreed
import io.github.messiasjunior.whatsupdawg.feature.breeds.usecase.SubBreedDomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val NUMBER_OF_IMAGES = 10

class BreedImagesRepository @Inject constructor(
    private val dogApi: DogApi,
    private val subBreedDomainMapper: SubBreedDomainMapper,
) {
    fun findSubBreedsByBreedId(breedId: String): Flow<List<SubBreed>> =
        flow { emit(dogApi.findAllSubBreedsByBreed(breedId)) }
            .map { mapResponse(breedId, it) }
            .map { addImages(it) }

    private fun mapResponse(breedId: String, response: SubBreedsResponse): List<SubBreed> {
        // If the breed has no sub breeds, treat main breed as sub breed.
        return if (response.message.isEmpty()) {
            listOf(mapBreedAsSubBreed(breedId))
        } else {
            response.message.map {
                subBreedDomainMapper.map(breedId = breedId, subBreedId = it)
            }
        }
    }

    private suspend fun addImages(subBreeds: List<SubBreed>): List<SubBreed> {
        return subBreeds.map {
            val images = dogApi.findAllImagesByBreed(it.id).message
            it.copy(images = images.take(NUMBER_OF_IMAGES))
        }
    }

    private fun mapBreedAsSubBreed(breedId: String) = SubBreed(
        id = breedId,
        name = breedId.capitalizeFirstChar()
    )
}
