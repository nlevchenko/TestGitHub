package levchenko.nikita.testgithub

import dagger.Component
import levchenko.nikita.testgithub.view.RootActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(rootActivity: RootActivity)

}