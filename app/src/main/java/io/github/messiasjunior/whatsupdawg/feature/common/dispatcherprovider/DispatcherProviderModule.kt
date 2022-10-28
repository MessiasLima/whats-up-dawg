package io.github.messiasjunior.whatsupdawg.feature.common.dispatcherprovider

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DispatcherProviderModule {
    @Binds
    fun bindDispatcherProvider(implementation: DispatcherProviderImpl): DispatcherProvider
}
