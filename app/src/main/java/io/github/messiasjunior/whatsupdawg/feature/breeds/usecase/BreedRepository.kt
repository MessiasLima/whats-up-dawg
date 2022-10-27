package io.github.messiasjunior.whatsupdawg.feature.breeds.usecase

import io.github.messiasjunior.whatsupdawg.core.network.DogApi
import io.github.messiasjunior.whatsupdawg.core.network.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BreedRepository @Inject constructor(
    private val dogApi: DogApi
){
    fun findAll(): Flow<Response> {
        return flow {
            val response = dogApi.findAllBreeds()
            emit(response)
        }
    }
}
