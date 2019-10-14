package levchenko.nikita.testgithub.core.view.vm

import android.os.Parcelable

interface ViewModel<Configurator : Parcelable> {

    val configurator: Configurator? get() = null

}