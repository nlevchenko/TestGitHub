package levchenko.nikita.testgithub.core.view.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import io.reactivex.disposables.Disposable
import levchenko.nikita.testgithub.core.view.vm.ViewModel
import levchenko.nikita.testgithub.core.view.vm.store.ViewModelStore


abstract class MVVMFragment<Configurator : Parcelable>(
    private val moduleKey: String = this::class.java.toString(),
    configurator: Configurator? = null
) : Fragment() {

    abstract val viewModel: ViewModel<Configurator>

    abstract val viewModelStore: ViewModelStore

    val configurator get() = arguments?.getParcelable(CONFIGURATOR_KEY) as? Configurator

    private var viewDisposable: Disposable? = null

    open fun subscribeView(): Disposable? = null

    companion object {
        private const val CONFIGURATOR_KEY = "CONFIGURATOR_KEY"
    }

    init {
        configurator?.let {
            arguments = Bundle().apply {
                putParcelable(CONFIGURATOR_KEY, it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDisposable = subscribeView()
    }

    override fun onStart() {
        super.onStart()

        if (viewDisposable?.isDisposed == true) {
            viewDisposable = subscribeView()
        }
    }

    override fun onStop() {
        viewDisposable?.dispose()
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(CONFIGURATOR_KEY, viewModel.configurator)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (activity?.isChangingConfigurations == false) {
            viewModelStore.remove(moduleKey)
        }
    }
}