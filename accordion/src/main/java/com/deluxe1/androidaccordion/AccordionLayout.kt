package com.deluxe1.androidaccordion

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi

/**Vertical accordion layout
 * Allows adding ExpandableViews and handles the state of the views on click*/
class AccordionLayout: LinearLayout, OnExpandableViewClickListener {

    /**List of views added to the Accordion*/
    private val viewList = arrayListOf<ExpandableView>()

    constructor(context: Context) : super(context) { init() }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) { init() }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) { init() }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) { init() }

    /**Initializes the layout. Defines its orientation*/
    private fun init() { orientation = VERTICAL }

    /**Overrides the base addView method to allow adding ExpendableViews only
     * Trying to add a different view will result in error
     * @throws InvalidViewAdded */
    override fun addView(child: View?) {
        if (child is ExpandableView) super.addView(child)
        else throw InvalidViewAdded("You can only add ExpandableViews to the AccordionLayout")
    }

    /**Add views to the accordion
     * @param views - list of ExpandableViews to add in the accordion*/
    fun addCustomViews(views : List<ExpandableView>) = views.forEach { addCustomView(it) }

    /**Adds a single view to the accordion
     * @param view - ExpandableView to add in the accordion*/
    fun addCustomView(view : ExpandableView) {
        view.init()
        viewList.add(view)
        view.addClickListener(this)
        addView(view)
        view.onExpandableViewAdded(view)
    }

    /**Handles click on ExpandableView. It expands or collapses the clicked view according to its state
     * And collapses all other views
     * @param expandableView - ExpandableView that was clicked*/
    override fun onExpandableViewClicked(expandableView: ExpandableView) {
        viewList.forEach { if (it != expandableView && it.isExpanded()) it.collapse() }
        if (!expandableView.isExpanded()) expandableView.expand()
        else expandableView.collapse()
    }

}
