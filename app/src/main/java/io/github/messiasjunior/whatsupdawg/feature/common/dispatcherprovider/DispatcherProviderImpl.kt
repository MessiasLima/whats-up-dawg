package io.github.messiasjunior.whatsupdawg.feature.common.dispatcherprovider

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {
    override fun getDispatcher() = Dispatchers.IO
}
