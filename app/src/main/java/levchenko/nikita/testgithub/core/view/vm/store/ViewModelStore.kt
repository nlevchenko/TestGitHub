package levchenko.nikita.testgithub.core.view.vm.store

import levchenko.nikita.testgithub.core.view.vm.ViewModel

interface ViewModelStore {
    fun <T : ViewModel<*>> get(key: String, init: () -> T): T

    fun <T : ViewModel<*>> put(key: String, viewModel: T)

    fun remove(key: String)
}