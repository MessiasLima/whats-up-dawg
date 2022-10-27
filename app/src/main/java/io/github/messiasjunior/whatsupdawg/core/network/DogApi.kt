package io.github.messiasjunior.whatsupdawg.core.network

import retrofit2.http.GET

interface DogApi {
    @GET("api/breeds/list/all")
    suspend fun findAllBreeds(): Response
}
