package br.com.grupofleury.core.ui

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import br.com.grupofleury.core.R

class LoadingIndicator : LinearLayout {

    private var _bgColor: Int = ContextCompat.getColor(
        context,
        R.color.transparent
    )

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

    fun start() {
        visibility = View.VISIBLE
    }

    fun stop() {
        Handler().postDelayed({
            visibility = View.GONE
        }, 300)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        inflate(context, R.layout.loading_indicator, this)

//        val loadingView: LottieAnimationView = findViewById(R.id.lavLoading)

        val loadingIcon: ImageView = findViewById(R.id.ivLoadingIcon)

        val attributes = context.obtainStyledAttributes(
            attrs, R.styleable.LoadingIndicator, defStyle, 0
        )

        val bgColor = attributes.getColor(
            R.styleable.LoadingIndicator_bgColor,
            _bgColor
        )

        val icon = attributes.getDrawable(
            R.styleable.LoadingIndicator_icon
        )

//        this.visibility = View.GONE
        this.setBackgroundColor(bgColor)

//        val filter = SimpleColorFilter(spinnerColor)
//        val keyPath = KeyPath("Calque de forme 2")
//        val callback = LottieValueCallback<ColorFilter>(filter)
//        loadingView.addValueCallback<ColorFilter>(keyPath, LottieProperty.COLOR_FILTER, callback)

        icon?.let {
            loadingIcon.setImageDrawable(it)
        }

        attributes.recycle()
    }
}
