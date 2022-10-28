package io.github.messiasjunior.whatsupdawg.core.testing

import io.github.messiasjunior.whatsupdawg.feature.common.dispatcherprovider.DispatcherProvider
import kotlinx.coroutines.Dispatchers

class TestDispatcherProvider : DispatcherProvider {
    override fun getDispatcher() = Dispatchers.Main
}
