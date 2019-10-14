package levchenko.nikita.testgithub.core.view.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import levchenko.nikita.testgithub.core.view.ui.list.ListAdapter.ViewHolder

abstract class ListAdapter<T : Identifiable, VH : ViewHolder<T>> :
    androidx.recyclerview.widget.ListAdapter<T, VH>(object : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem

        override fun getChangePayload(oldItem: T, newItem: T): Any? {
            return getChangePayload(oldItem, newItem)
        }

    }), Consumer<List<T>> {

    open fun getChangePayload(oldItem: T, newItem: T): Any?  = null

    override fun accept(list: List<T>) {
        submitList(list)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBindViewHolder(getItem(position))
    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        holder.onBindViewHolder(getItem(position), payloads)
    }


    abstract class ViewHolder<T : Identifiable>(view: View) : RecyclerView.ViewHolder(view) {

        abstract fun onBindViewHolder(entity: T)
        fun onBindViewHolder(entity: T, payloads: MutableList<Any>) {
            if (payloads.isEmpty()) {
                onBindViewHolder(entity)
            } else {
                payloads.forEach {
                    if (it is Identifiable) {
                        applyPayload(entity, it)
                    } else {
                        (it as List<*>).forEach { payload -> applyPayload(entity, payload) }
                    }
                }
            }
        }

        open fun applyPayload(entity: T, payload: Any?) {}
    }
}

fun <T : Identifiable> Observable<List<T>>.subscribeAdapter(adapter: ListAdapter<T, *>) =
    subscribe { items -> adapter.submitList(items) }

fun ViewGroup.inflate(@LayoutRes layoutRes: Int) =
    LayoutInflater.from(context).inflate(layoutRes, this, false)
