package io.github.messiasjunior.whatsupdawg.core.network

import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("api/breeds/list/all")
    suspend fun findAllBreeds(): BreedsResponse

    @GET("api/breed/{breed}/images/random")
    suspend fun findImageByBreed(@Path("breed") breedId: String): ImageResponse
}
