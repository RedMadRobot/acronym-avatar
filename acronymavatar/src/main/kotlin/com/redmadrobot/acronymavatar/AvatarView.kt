package com.redmadrobot.acronymavatar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.DisplayMetrics
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import kotlin.properties.Delegates


class AvatarView : AppCompatImageView {
    companion object {
        private const val DEFAULT_ZOOM_OUT = 2.3f
        private const val DEFAULT_WIDTH_DP = 40
        private const val DEFAULT_HEIGHT_DP = 40
    }

    private var rawText by Delegates.notNull<String>()
    private var placeholder by Delegates.notNull<Drawable>()
    private var zoomOut by Delegates.notNull<Float>()

    private val colors = context.resources.getIntArray(R.array.avatar_colors_set)

    private val wordRegex = Regex("([a-zа-я]+)", RegexOption.IGNORE_CASE)

    constructor(context: Context) : super(context) {
        initView()
    }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet, defStyle: Int = 0) : super(context, attrs, defStyle) {
        val additionalAttrs = context.obtainStyledAttributes(attrs, R.styleable.AvatarView, defStyle, 0)

        rawText = additionalAttrs.getString(R.styleable.AvatarView_text) ?: ""
        placeholder = additionalAttrs.getDrawable(R.styleable.AvatarView_placeholder) ?: getDefaultPlaceholder()!!
        zoomOut = additionalAttrs.getFloat(R.styleable.AvatarView_zoom_out, DEFAULT_ZOOM_OUT)

        additionalAttrs.recycle()

        initView()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(calculateWidth(widthMeasureSpec), calculateHeight(heightMeasureSpec))
    }

    fun setText(value: String) {
        rawText = value
        initView()
    }

    private fun initView() {
        if (rawText.isEmpty() || wordRegex.findAll(rawText).count() == 0) {
            setImageDrawable(placeholder)
        } else {
            val acronym = getAcronym(rawText)
            val backgroundColor = colors[Math.abs(rawText.hashCode() % colors.size)]

            setImageDrawable(AcronymDrawable(acronym, backgroundColor, zoomOut))
        }
    }

    private fun getAcronym(text: String): String {
        val words = wordRegex.findAll(text)

        return when (words.count()) {
            0 -> ""
            1 -> words.first().value.take(2)
            else -> words.take(2).map { it.value.take(1) }.reduce { s1, s2 -> "$s1$s2" }
        }.toUpperCase()
    }

    private fun getDefaultPlaceholder() = ContextCompat.getDrawable(context, R.drawable.default_placeholder)

    private fun calculateWidth(widthMeasureSpec: Int) = getMeasurment(widthMeasureSpec, dp2px(DEFAULT_WIDTH_DP))
    private fun calculateHeight(heightMeasureSpec: Int) = getMeasurment(heightMeasureSpec, dp2px(DEFAULT_HEIGHT_DP))

    @SuppressLint("SwitchIntDef")
    private fun getMeasurment(measureSpec: Int, defaultSize: Int): Int {
        val specSize = MeasureSpec.getSize(measureSpec)

        return when(MeasureSpec.getMode(measureSpec)) {
            MeasureSpec.EXACTLY -> specSize
            else -> defaultSize
        }
    }

    private fun dp2px(dp: Int) = dp * (resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}
