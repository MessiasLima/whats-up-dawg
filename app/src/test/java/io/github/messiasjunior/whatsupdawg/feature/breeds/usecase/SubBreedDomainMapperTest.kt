package io.github.messiasjunior.whatsupdawg.feature.breeds.usecase

import io.github.messiasjunior.whatsupdawg.core.testing.UnitTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SubBreedDomainMapperTest : UnitTest<SubBreedDomainMapper>()  {
    override fun buildSut() = SubBreedDomainMapper()

    @Test
    fun `should map sub breed`() {
        val fixtBreed = fixture<String>()
        val fixtSubBreed = "subreed"

        val actual = sut.map(breedId = fixtBreed, subBreedId = fixtSubBreed)

        assertEquals("$fixtBreed/$fixtSubBreed", actual.id)
        assertEquals("Subreed $fixtBreed", actual.name)
    }
}
