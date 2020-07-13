package com.example.chiptogglebutton

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


enum class Clicked {
    SELECTED,
    UN_SELECTED
}

class ChipToggleButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) :
    androidx.appcompat.widget.AppCompatButton(context, attrs) {

    private var clickedStatus: Clicked = Clicked.UN_SELECTED
    private val gradientDrawable = GradientDrawable()
    private var _status: MutableLiveData<Boolean>

    val status: LiveData<Boolean>
        get() = _status

    var radius: Float = 40.0f
        set(value) {
            field = value
            showRadius()
        }

    var selectedBackgroundColor: Int = Color.parseColor("#1c1c1c")
        set(value) {
            field = value
            showBackgroundColor()
        }

    var unSelectedBackgroundColor: Int = Color.parseColor("#1c1c1c")
        set(value) {
            field = value
            showBackgroundColor()
        }

    var selectedTextColor: Int = Color.GRAY
        set(value) {
            field = value
            showTextColor()
        }

    var unSelectedTextColor: Int = Color.WHITE
        set(value) {
            field = value
            showTextColor()
        }

    var selectedBorderColor: Int = Color.GRAY
        set(value) {
            field = value
            showStroke()
        }

    var unSelectedBorderColor: Int = Color.parseColor("#1c1c1c")
        set(value) {
            field = value
            showStroke()
        }

    var borderWidth: Int = 5
        set(value) {
            field = value
            showStroke()
        }

    init {
        isClickable = true
        isAllCaps = false
        _status = MutableLiveData()
        _status.value = false

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ChipToggleButton,
            0, 0
        ).apply {
            try {
                radius = getFloat(R.styleable.ChipToggleButton_radius, radius)

                selectedBackgroundColor = getInteger(
                    R.styleable.ChipToggleButton_selectedBackgroundColor,
                    selectedBackgroundColor
                )

                unSelectedBackgroundColor = getInteger(
                    R.styleable.ChipToggleButton_unSelectedBackgroundColor,
                    unSelectedBackgroundColor
                )

                selectedTextColor = getInteger(
                    R.styleable.ChipToggleButton_selectedTextColor,
                    selectedTextColor
                )

                unSelectedTextColor = getInteger(
                    R.styleable.ChipToggleButton_unSelectedTextColor,
                    unSelectedTextColor
                )

                selectedBorderColor = getInteger(
                    R.styleable.ChipToggleButton_selectedBorderColor,
                    selectedBorderColor
                )

                unSelectedBorderColor = getInteger(
                    R.styleable.ChipToggleButton_unSelectedBorderColor,
                    unSelectedBorderColor
                )

                borderWidth = getInteger(
                    R.styleable.ChipToggleButton_borderWidth,
                    borderWidth
                )
                render()
            } finally {
                recycle()
            }
        }
    }

    override fun performClick(): Boolean {
        clickedStatus =
            when (clickedStatus) {
                Clicked.SELECTED -> Clicked.UN_SELECTED
                Clicked.UN_SELECTED -> Clicked.SELECTED
            }
        _status.value = when (clickedStatus) {
            Clicked.SELECTED -> true
            Clicked.UN_SELECTED -> false
        }
        render()
        return super.performClick()
    }

    fun setToggleButton(status: Boolean) {
        clickedStatus =
            when (status) {
                false -> Clicked.UN_SELECTED
                true -> Clicked.SELECTED
            }
        _status.value = when (clickedStatus) {
            Clicked.SELECTED -> true
            Clicked.UN_SELECTED -> false
        }
        render()
    }

    private fun render() {
        showRadius()
        showBackgroundColor()
        showTextColor()
        showStroke()
        background = gradientDrawable
    }

    private fun showRadius() {
        gradientDrawable.cornerRadius = radius
    }

    private fun showBackgroundColor() {
        gradientDrawable.setColor(
            when (clickedStatus) {
                Clicked.SELECTED -> selectedBackgroundColor
                Clicked.UN_SELECTED -> unSelectedBackgroundColor
            }
        )
    }

    private fun showTextColor() {
        setTextColor(
            when (clickedStatus) {
                Clicked.SELECTED -> selectedTextColor
                Clicked.UN_SELECTED -> unSelectedTextColor
            }
        )
    }

    private fun showStroke() {
        gradientDrawable.setStroke(
            borderWidth, when (clickedStatus) {
                Clicked.SELECTED -> selectedBorderColor
                Clicked.UN_SELECTED -> unSelectedBorderColor
            }
        )
    }
}