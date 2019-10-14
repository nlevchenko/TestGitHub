package levchenko.nikita.testgithub.core.view.navigation

import android.util.SparseArray
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class NavigationImpl : Navigation {

    private val mRootCicerone = Cicerone.create()

    private val mCiceroneSparseArray = SparseArray<Cicerone<Router>>().apply {
        append(Navigation.CiceroneModule.ROOT.ordinal, mRootCicerone)
    }

    override val router: Router get() = mRootCicerone.router
    override val navigationHolder: NavigatorHolder get() = mRootCicerone.navigatorHolder

    override fun getRouter(ciceroneModule: Navigation.CiceroneModule) =
        getCicerone(ciceroneModule).router!!

    override fun getNavigatorHolder(ciceroneModule: Navigation.CiceroneModule) =
        getCicerone(ciceroneModule).navigatorHolder!!

    private fun getCicerone(ciceroneModule: Navigation.CiceroneModule) =
        mCiceroneSparseArray[ciceroneModule.ordinal] ?: Cicerone.create().apply {
            mCiceroneSparseArray.put(ciceroneModule.ordinal, this)
        }
}