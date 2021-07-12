package com.deluxe1.androidaccordion

import android.animation.LayoutTransition
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout

/**Defines the base functionalities that an ExpandableView should implement*/
abstract class ExpandableView : ConstraintLayout {

    private var expanded = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)


    /**Adds click listener for the Expandable view
     * @param onClickListener - listener for the click event*/
    fun addClickListener(onClickListener: OnExpandableViewClickListener) {
        findViewById<View>(getRootViewId()).setOnClickListener {
            onClickListener.onExpandableViewClicked(this)
        }
    }

    /**Collapse the view*/
    fun collapse() {
        expanded = false
        findViewById<View>(getExpandableViewId()).visibility = View.GONE
    }

    /**Expand the view and show extra information*/
    fun expand() {
        expanded = true
        findViewById<ViewGroup>(getParentViewId()).layoutTransition.enableTransitionType(LayoutTransition.APPEARING)
        findViewById<View>(getExpandableViewId()).visibility = View.VISIBLE
        findViewById<ViewGroup>(getParentViewId()).layoutTransition.disableTransitionType(LayoutTransition.APPEARING)
    }

    abstract fun getExpandableViewId(): Int
    abstract fun getParentViewId() : Int
    abstract fun getRootViewId() : Int
    abstract fun getLayoutResId(): Int
    abstract fun onExpandableViewAdded(view: ExpandableView)


    /**Indicates if the view is currently expanded or not*/
    fun isExpanded() = expanded

    fun init() {
        inflate(context, getLayoutResId(), this)
        layoutTransition = LayoutTransition().also {
            it.disableTransitionType(LayoutTransition.APPEARING)
            it.disableTransitionType(LayoutTransition.CHANGING)
            it.disableTransitionType(LayoutTransition.DISAPPEARING)
            it.disableTransitionType(LayoutTransition.CHANGE_DISAPPEARING)
            it.disableTransitionType(LayoutTransition.CHANGE_APPEARING)
        }
        findViewById<View>(getExpandableViewId()).visibility = GONE
        findViewById<ViewGroup>(getParentViewId()).layoutTransition = layoutTransition
    }

}

interface OnExpandableViewClickListener {
    fun onExpandableViewClicked(expandableView: ExpandableView)
}

data class InvalidViewAdded(val error: String) : Throwable(error)