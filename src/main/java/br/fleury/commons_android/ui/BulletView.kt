package br.com.fleury.commons_android.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import br.com.fleury.commons_android.R


class BulletView(context: Context?, attrs: AttributeSet?, defStyle: Int) :
    RelativeLayout(context, attrs, defStyle) {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        LayoutInflater.from(context).inflate(R.layout.bullet_view, this)
    }
}