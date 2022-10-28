package io.github.messiasjunior.whatsupdawg.feature.breedimages.presentation

import io.github.messiasjunior.whatsupdawg.core.testing.UnitTest
import io.github.messiasjunior.whatsupdawg.domain.SubBreed
import org.junit.Assert.assertEquals
import org.junit.Test

class BreedImagesUiModelMapperTest : UnitTest<BreedImagesUiModelMapper>() {
    override fun buildSut() = BreedImagesUiModelMapper()

    @Test
    fun `should map bub breed list`() {
        val fixtSubBreed = SubBreed(id = fixture(), name = "breed", images = fixture())
        val fixtSubBreedList = listOf(fixtSubBreed)

        val actual = sut.map(fixtSubBreedList)

        assertEquals(1, actual.size)
        actual.first().let {
            assertEquals(fixtSubBreed.name, it.breedName)
            assertEquals(fixtSubBreed.images, it.images)
        }
    }

    @Test
    fun `should map main breed name`() {
        val actual = sut.mapMainBreedName(BreedId("breed"))

        assertEquals("Breed", actual)
    }
}
