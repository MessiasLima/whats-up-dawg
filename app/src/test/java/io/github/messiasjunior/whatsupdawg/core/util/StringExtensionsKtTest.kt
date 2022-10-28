package io.github.messiasjunior.whatsupdawg.core.util

import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionsKtTest {
    @Test
    fun `should capitalize first char`() {
        assertEquals("Dog", "dog".capitalizeFirstChar())
        assertEquals("Dog", "Dog".capitalizeFirstChar())
    }
}
