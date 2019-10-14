package levchenko.nikita.testgithub.core.view.vm.store

import levchenko.nikita.testgithub.core.view.vm.ViewModel

class ViewModelStoreImpl : ViewModelStore {

    private val hashMap = HashMap<String, ViewModel<*>>()

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel<*>> get(key: String, init: () -> T) =
        hashMap[key] as? T ?: init().apply {
            hashMap[key] = this
        }

    override fun <T : ViewModel<*>> put(key: String, viewModel: T) {
        hashMap[key] = viewModel
    }

    override fun remove(key: String) {
        hashMap.remove(key)
    }
}