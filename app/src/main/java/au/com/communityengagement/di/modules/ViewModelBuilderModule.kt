package au.com.communityengagement.di.modules

import androidx.lifecycle.ViewModel
import au.com.communityengagement.di.annotations.ViewModelKey
import au.com.communityengagement.models.viewmodels.PostViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilderModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostViewModel(viewModel: PostViewModel) : ViewModel


}