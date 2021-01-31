package au.com.communityengagement.di.component

import android.app.Application
import au.com.communityengagement.BaseApp
import au.com.communityengagement.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

//Application component is singleton
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class, //Default module to inject android injector components
        ApplicationModule::class,
        ViewModelFactoryModule::class,
        ViewModelBuilderModule::class,
        ActivityBuilderModule::class,
        RoomBuilderModule::class,
        FragmentsBuilderModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<BaseApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : ApplicationComponent
    }
}