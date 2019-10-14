package levchenko.nikita.testgithub.core.view.navigation

import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

interface Navigation {

    val router: Router
    val navigationHolder: NavigatorHolder

    fun getRouter(ciceroneModule: CiceroneModule): Router
    fun getNavigatorHolder(ciceroneModule: CiceroneModule): NavigatorHolder

    enum class CiceroneModule { ROOT }

    class Screens {

        companion object {

        }
    }
}