package io.github.messiasjunior.whatsupdawg.feature.common.dispatcherprovider

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun getDispatcher(): CoroutineDispatcher
}
