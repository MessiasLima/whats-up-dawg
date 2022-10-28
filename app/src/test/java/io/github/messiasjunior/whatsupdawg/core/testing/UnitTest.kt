package io.github.messiasjunior.whatsupdawg.core.testing

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import org.junit.Before

abstract class UnitTest<SubjectUnderTest : Any> {
    protected lateinit var sut: SubjectUnderTest
    protected val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    abstract fun buildSut(): SubjectUnderTest

    @Before
    fun setup() {
        sut = buildSut()
    }
}
