package io.github.messiasjunior.whatsupdawg.feature.breeds.usecase

import io.github.messiasjunior.whatsupdawg.core.network.model.BreedsResponse
import io.github.messiasjunior.whatsupdawg.core.testing.UnitTest
import io.github.messiasjunior.whatsupdawg.domain.SubBreed
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class BreedDomainMapperTest : UnitTest<BreedDomainMapper>() {
    private val mockSubBreedDomainMapper = mockk<SubBreedDomainMapper>()

    override fun buildSut() = BreedDomainMapper(
        subBreedDomainMapper = mockSubBreedDomainMapper
    )

    @Test
    fun `should map domain mappers`() {
        val fixtBreedsResponse = fixture<BreedsResponse>()
        val fixtSubBreed = fixture<SubBreed>()

        fixtBreedsResponse.message.entries.forEach { entry ->
            entry.value.forEach { subBreed ->
                every {
                    mockSubBreedDomainMapper.map(
                        breedId = entry.key,
                        subBreedId = subBreed
                    )
                } returns fixtSubBreed
            }
        }


        val actual = sut.map(fixtBreedsResponse)

        assertEquals(fixtBreedsResponse.message.size, actual.size)
        fixtBreedsResponse.message.entries.forEachIndexed { index, entry ->
            assertEquals(entry.key, actual[index].id)
        }
    }
}
