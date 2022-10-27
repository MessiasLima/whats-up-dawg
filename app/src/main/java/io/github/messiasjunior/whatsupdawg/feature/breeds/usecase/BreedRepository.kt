package io.github.messiasjunior.whatsupdawg.feature.breeds.usecase

import io.github.messiasjunior.whatsupdawg.core.network.DogApi
import io.github.messiasjunior.whatsupdawg.domain.Breed
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@FlowPreview
class BreedRepository @Inject constructor(
    private val dogApi: DogApi,
    private val breedDomainMapper: BreedDomainMapper,
) {
    fun findAll() = flow { emit(dogApi.findAllBreeds()) }
        .map(breedDomainMapper::map)
        .map { addImage(it) }

    private suspend fun addImage(breeds: List<Breed>) = breeds.map {
        val image = dogApi.findImageByBreed(it.id).message
        it.copy(image = image)
    }
}
