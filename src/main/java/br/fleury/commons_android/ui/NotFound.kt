package br.com.fleury.commons_android.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import br.com.fleury.commons_android.R

class NotFound : LinearLayout {
    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        inflate(context, R.layout.not_found, this)
    }
}
