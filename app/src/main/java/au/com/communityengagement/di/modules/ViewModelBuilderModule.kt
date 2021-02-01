package au.com.communityengagement.di.modules

import androidx.lifecycle.ViewModel
import au.com.communityengagement.di.annotations.ViewModelKey
import au.com.communityengagement.models.viewmodels.PostsViewModel
import au.com.communityengagement.models.viewmodels.SelectedPostViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilderModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostViewModel(viewModel: PostsViewModel) : ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(SelectedPostViewModel::class)
    abstract fun bindSelectedPostViewModel(viewModel: SelectedPostViewModel) : ViewModel

}