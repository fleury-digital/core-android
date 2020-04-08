package br.com.fleury.commons.base

import androidx.recyclerview.widget.RecyclerView

abstract class EndlessScrollEventListener :
    RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        if (isLoading || isLastPage)
            return

        if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
            loadPage()
        }
    }

    protected abstract fun loadPage()

    abstract val isLastPage: Boolean

    abstract val isLoading: Boolean

    companion object {
        const val PAGE_START = 1
    }

}