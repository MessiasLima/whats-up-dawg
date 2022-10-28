package io.github.messiasjunior.whatsupdawg.feature.breeds.presentation

import io.github.messiasjunior.whatsupdawg.core.testing.UnitTest
import io.github.messiasjunior.whatsupdawg.domain.Breed
import org.junit.Assert.assertEquals
import org.junit.Test

class BreedUiModelMapperTest : UnitTest<BreedUiModelMapper>() {
    override fun buildSut() = BreedUiModelMapper()

    @Test
    fun `should map ui models`() {
        val fixtDomainModels = fixture<List<Breed>>()

        val actual = sut.map(fixtDomainModels)

        actual.forEachIndexed { index, breedUiModel ->
            val domain = fixtDomainModels[index]

            assertEquals(domain.id, breedUiModel.id)
            assertEquals(domain.name, breedUiModel.name)
            assertEquals(domain.image, breedUiModel.imageUrl)
        }
    }
}
