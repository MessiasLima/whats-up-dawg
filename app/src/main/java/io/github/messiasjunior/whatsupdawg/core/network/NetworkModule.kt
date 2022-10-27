package io.github.messiasjunior.whatsupdawg.core.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val DOG_API_BASE_URL = "https://dog.ceo"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideDogApi(): DogApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(DOG_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(DogApi::class.java)
    }
}
