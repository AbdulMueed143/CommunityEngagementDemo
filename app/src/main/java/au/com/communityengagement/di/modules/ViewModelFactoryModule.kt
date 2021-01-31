package au.com.communityengagement.di.modules

import androidx.lifecycle.ViewModelProvider
import au.com.communityengagement.di.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(providerFactory: ViewModelProviderFactory) : ViewModelProvider.Factory
}