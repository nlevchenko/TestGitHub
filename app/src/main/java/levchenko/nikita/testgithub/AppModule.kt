package levchenko.nikita.testgithub

import android.content.Context
import dagger.Module
import dagger.Provides
import levchenko.nikita.testgithub.core.view.navigation.Navigation
import levchenko.nikita.testgithub.core.view.navigation.NavigationImpl
import levchenko.nikita.testgithub.core.view.vm.store.ViewModelStore
import levchenko.nikita.testgithub.core.view.vm.store.ViewModelStoreImpl
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    fun context(): Context = context.applicationContext

    @Provides
    @Singleton
    fun navigation(): Navigation = NavigationImpl()


    // View
    @Provides
    @Singleton
    fun viewModelStore(): ViewModelStore = ViewModelStoreImpl()
}