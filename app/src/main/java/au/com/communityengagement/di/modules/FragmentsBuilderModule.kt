package au.com.communityengagement.di.modules

import au.com.communityengagement.fragments.SelectedPostFragment
import au.com.communityengagement.fragments.PostsViewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBuilderModule {

    //This will contain remaining fragments
    @ContributesAndroidInjector
    abstract fun providesPostViewFragment() : PostsViewFragment

    @ContributesAndroidInjector
    abstract fun providesPostItemFragment() : SelectedPostFragment

}