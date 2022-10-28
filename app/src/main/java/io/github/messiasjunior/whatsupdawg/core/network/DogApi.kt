package io.github.messiasjunior.whatsupdawg.core.network

import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {
    @GET("api/breeds/list/all")
    suspend fun findAllBreeds(): BreedsResponse

    @GET("api/breed/{breed}/images/random")
    suspend fun findImageByBreed(@Path("breed") breedId: String): ImageResponse

    @GET("api/breed/{breed}/list")
    suspend fun findAllSubBreedsByBreed(@Path("breed") breedId: String): SubBreedsResponse

    @GET("api/breed/{breed}/images")
    suspend fun findAllImagesByBreed(@Path("breed", encoded = true) breedId: String): ImagesResponse
}
