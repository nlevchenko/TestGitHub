package levchenko.nikita.testgithub.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import levchenko.nikita.testgithub.App
import levchenko.nikita.testgithub.R
import levchenko.nikita.testgithub.core.view.navigation.Navigation
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class RootActivity : AppCompatActivity() {

    @Inject
    lateinit var navigation: Navigation

    private val navigator = SupportAppNavigator(this, R.id.rootContainerFrameLayout)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.component.inject(this)

        setContentView(R.layout.activity_root)

    }

    override fun onResume() {
        super.onResume()
        navigation.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigation.navigationHolder.removeNavigator()
        super.onPause()
    }
}
