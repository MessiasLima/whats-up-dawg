package io.github.messiasjunior.whatsupdawg.feature.breeds.usecase

import io.github.messiasjunior.whatsupdawg.core.network.DogApi
import io.github.messiasjunior.whatsupdawg.core.network.model.BreedsResponse
import io.github.messiasjunior.whatsupdawg.core.network.model.ImageResponse
import io.github.messiasjunior.whatsupdawg.core.testing.UnitTest
import io.github.messiasjunior.whatsupdawg.domain.Breed
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
@FlowPreview
class BreedRepositoryTest : UnitTest<BreedRepository>() {
    private val mockDogApi = mockk<DogApi>()
    private val mockBreedDomainMapper = mockk<BreedDomainMapper>()

    override fun buildSut() = BreedRepository(
        dogApi = mockDogApi,
        breedDomainMapper = mockBreedDomainMapper,
    )

    @Test
    fun `should find all breeds`() = runTest {
        val fixtBreedsResponse = fixture<BreedsResponse>()
        val fixtBreeds = fixture<List<Breed>>()
        val fixtImageResponse = fixture<ImageResponse>()

        coEvery { mockDogApi.findAllBreeds() } returns fixtBreedsResponse
        every { mockBreedDomainMapper.map(fixtBreedsResponse) } returns fixtBreeds
        fixtBreeds.forEach {
            coEvery { mockDogApi.findImageByBreed(it.id) } returns fixtImageResponse
        }

        val actual = sut.findAll().first()

        fixtBreeds.forEachIndexed { index, entry ->
            assertEquals(entry.id, actual[index].id)
            assertEquals(fixtImageResponse.message, actual[index].image)
        }
    }
}
