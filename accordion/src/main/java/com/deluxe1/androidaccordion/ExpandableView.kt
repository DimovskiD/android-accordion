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

    private var clickableViewColor: Int = -1
    private var expandableViewColor: Int = -1

    private var expanded = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)


    /**Adds click listener for the Expandable view
     * @param onClickListener - listener for the click event*/
    fun addClickListener(onClickListener: OnExpandableViewClickListener) {
        findViewById<View>(getClickableViewId()).setOnClickListener {
            onClickListener.onExpandableViewClicked(this)
        }
    }

    /**Collapse the view*/
    open fun collapse() {
        expanded = false
        findViewById<View>(getExpandableViewId()).visibility = View.GONE
    }

    /**Expand the view and show extra information*/
    open fun expand() {
        expanded = true
        findViewById<ViewGroup>(getRootViewId()).layoutTransition.enableTransitionType(LayoutTransition.CHANGE_APPEARING)
        findViewById<ViewGroup>(getRootViewId()).layoutTransition.enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING)

        findViewById<View>(getExpandableViewId()).visibility = View.VISIBLE

        findViewById<ViewGroup>(getRootViewId()).layoutTransition.disableTransitionType(LayoutTransition.CHANGE_APPEARING)
        findViewById<ViewGroup>(getRootViewId()).layoutTransition.disableTransitionType(LayoutTransition.CHANGE_DISAPPEARING)

    }

    /**@return the id of the view that should be expanded/closed on click */
    abstract fun getExpandableViewId(): Int
    /**@return the id of the root view in the layout - used to enable/disable animation*/
    abstract fun getRootViewId() : Int
    /**@return the id of the clickable part of the accordion - this view is always visible*/
    abstract fun getClickableViewId() : Int
    /**@return the id of the layout file that corresponds to this view*/
    abstract fun getLayoutResId(): Int
    /**Called when an expandable view has been added to the accordion
     * The view is inflated and ready to be used
     * Use this callback to init/edit the UI of the element
     * @param view - expandable view that has been added to the Accordion layout*/
    open fun onExpandableViewAdded(view: ExpandableView) {}


    /**Indicates if the view is currently expanded or not*/
    fun isExpanded() = expanded

    /**Inflates and initializes the view
     * Disable all animations at start, we will later enable the one we need (avoiding bad UI when closing the accordion)*/
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
        findViewById<View>(getClickableViewId()).visibility = VISIBLE
        findViewById<ViewGroup>(getRootViewId()).layoutTransition = layoutTransition
        if (expandableViewColor != -1) findViewById<View>(getRootViewId()).setBackgroundColor(expandableViewColor)
        if (clickableViewColor != -1) findViewById<View>(getClickableViewId()).setBackgroundColor(clickableViewColor)

    }

    fun setClickableViewColor(clickableViewColor: Int) {
        this.clickableViewColor = clickableViewColor
    }

    fun setExpandableViewColor(expandableViewColor: Int) {
        this.expandableViewColor = expandableViewColor
    }
}

interface OnExpandableViewClickListener {
    fun onExpandableViewClicked(expandableView: ExpandableView)
}

data class InvalidViewAdded(val error: String) : Throwable(error)