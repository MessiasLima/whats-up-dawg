package io.github.messiasjunior.whatsupdawg.feature.breedimages.usecase

import io.github.messiasjunior.whatsupdawg.core.network.DogApi
import io.github.messiasjunior.whatsupdawg.core.network.model.ImagesResponse
import io.github.messiasjunior.whatsupdawg.core.network.model.SubBreedsResponse
import io.github.messiasjunior.whatsupdawg.core.testing.UnitTest
import io.github.messiasjunior.whatsupdawg.domain.SubBreed
import io.github.messiasjunior.whatsupdawg.feature.breeds.usecase.SubBreedDomainMapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class BreedImagesRepositoryTest : UnitTest<BreedImagesRepository>() {
    private val mockDogApi = mockk<DogApi>()
    private val mockSubBreedDomainMapper = mockk<SubBreedDomainMapper>()

    override fun buildSut() = BreedImagesRepository(
        dogApi = mockDogApi,
        subBreedDomainMapper = mockSubBreedDomainMapper,
    )

    @Test
    fun `should find sub breeds by breed id - with sub breeds`() = runTest {
        val fixtBreedId = fixture<String>()
        val fixtSubBreedsResponse = fixture<SubBreedsResponse>()
        val fixtSubBreed = fixture<SubBreed>()
        val fixtImages = fixture<List<String>>()
        val fixtImagesResponse = ImagesResponse(message = fixtImages, "success")

        coEvery { mockDogApi.findAllSubBreedsByBreed(fixtBreedId) } returns fixtSubBreedsResponse
        fixtSubBreedsResponse.message.forEach {
            every { mockSubBreedDomainMapper.map(fixtBreedId, it) } returns fixtSubBreed
        }
        coEvery { mockDogApi.findAllImagesByBreed(fixtSubBreed.id) } returns fixtImagesResponse

        val actual = sut.findSubBreedsByBreedId(fixtBreedId)
            .first()

        assertEquals(fixtSubBreedsResponse.message.size, actual.size)
        actual.forEach {
            assertEquals(fixtSubBreed.id, it.id)
            assertEquals(fixtSubBreed.name, it.name)
            assertEquals(fixtImages, it.images)
        }
    }

    @Test
    fun `should find sub breeds by breed id - with no sub breeds`() = runTest {
        val fixtBreedId = fixture<String>()
        val fixtSubBreedsResponse = SubBreedsResponse(message = emptyList(), status = "success")
        val fixtImages = fixture<List<String>>()
        val fixtImagesResponse = ImagesResponse(message = fixtImages, "success")

        coEvery { mockDogApi.findAllSubBreedsByBreed(fixtBreedId) } returns fixtSubBreedsResponse
        coEvery { mockDogApi.findAllImagesByBreed(fixtBreedId) } returns fixtImagesResponse

        val actual = sut.findSubBreedsByBreedId(fixtBreedId)
            .first()

        assertEquals(1, actual.size)
        assertEquals(fixtBreedId, actual.first().id)
        assertEquals(fixtImages, actual.first().images)
    }
}
