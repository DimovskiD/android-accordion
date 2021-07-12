package com.deluxe1.accordion

import android.content.Context
import android.widget.TextView
import com.deluxe1.androidaccordion.ExpandableView

open class TestLayout(context: Context) : ExpandableView(context) {

    override fun getLayoutResId(): Int = R.layout.test_layout
    override fun getParentViewId() : Int = R.id.parent
    override fun getExpandableViewId(): Int = R.id.expandable
    override fun getRootViewId(): Int = R.id.root
    override fun onExpandableViewAdded(view: ExpandableView) {
        view.findViewById<TextView>(R.id.title).text = "Title"
    }

}