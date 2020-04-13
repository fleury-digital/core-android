package br.com.grupofleury.core.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import android.view.animation.Animation
//import android.view.animation.AnimationUtils
import androidx.annotation.LayoutRes
import timber.log.Timber
import java.util.*

    abstract class BaseAdapter<TItem> :
    androidx.recyclerview.widget.RecyclerView.Adapter<BaseViewHolder<TItem>>() {

    private var lastPosition = -1

    val items = ArrayList<TItem?>()

    fun items(): ArrayList<TItem?> {
        return items
    }

    fun clearItems() {
        val count = items.size
        items.clear()
        notifyItemRangeRemoved(0, count)
    }

    fun removeItem(item: TItem?) {
        val index = items.indexOf(item)
        items.remove(item)
        notifyItemRemoved(index)
    }

    fun updateItem(item: TItem) {
        val index = items.indexOf(item)
        items[index] = item
        notifyItemChanged(index)
    }

    fun addItem(item: TItem) {
        items.add(item)
        val index = items.indexOf(item)
        notifyItemInserted(index)
    }

    fun addItems(list: Collection<TItem>) {
        items.addAll(ArrayList<TItem>(list))
    }

    fun removeSection(location: Int) {
        items.removeAt(location)
    }

    /**
     * Fetch the layout id.
     */
    protected abstract fun layout(item: TItem?): Int

    /**
     * Returns a new ViewHolder given a layout and view.
     */
    protected abstract fun viewHolder(@LayoutRes layoutId: Int, view: View): BaseViewHolder<TItem>


    override fun onViewDetachedFromWindow(holder: BaseViewHolder<TItem>) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, @LayoutRes layoutId: Int): BaseViewHolder<TItem> {
        val view = inflateView(viewGroup, layoutId)
        return viewHolder(layoutId, view)
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder<TItem>, position: Int) {
        val data = getItemByPosition(position)

        try {
            viewHolder.bindData(data)
//            val animation : Animation = AnimationUtils.loadAnimation(viewHolder.itemView.context , if (position > lastPosition) R.anim.up_from_bottom else R.anim.down_from_top)
//            viewHolder.itemView.startAnimation(animation)
            lastPosition = position
        } catch (e: Exception) {
            Timber.i(e.toString())
            e.printStackTrace()
        }

    }

    /**
     * Gets the data object associated with a position.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    protected fun getItemByPosition(position: Int): TItem? {
        return items[position]
    }

    override fun getItemViewType(position: Int): Int {
        val item: TItem? = items[position]
        return layout(item)
    }


    private fun inflateView(viewGroup: ViewGroup, @LayoutRes viewType: Int): View {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return layoutInflater.inflate(viewType, viewGroup, false)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}