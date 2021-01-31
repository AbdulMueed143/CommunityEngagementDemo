package au.com.communityengagement.di.modules

import au.com.communityengagement.PostActivity
import au.com.communityengagement.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilderModule {

    //All the activities will be here
    @ContributesAndroidInjector
    abstract fun contributesMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun contributesCommentsActivity() : PostActivity

}