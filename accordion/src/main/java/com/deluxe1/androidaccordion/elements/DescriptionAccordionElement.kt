package com.deluxe1.androidaccordion.elements

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.deluxe1.androidaccordion.ExpandableView
import com.deluxe1.androidaccordion.R

open class DescriptionAccordionElement(context: Context) : ExpandableView(context) {
    var title : String = ""
    var description : String = ""
    var longDescription : String = ""


    constructor(context: Context, title: String, description : String, longDescription : String) : this(context) {
        this.title = title
        this.description = description
        this.longDescription = longDescription
    }
    override fun getLayoutResId(): Int = R.layout.title_description_layout
    override fun getRootViewId() : Int = R.id.parent
    override fun getExpandableViewId(): Int = R.id.expandable
    override fun getClickableViewId(): Int = R.id.root
    override fun onExpandableViewAdded(view: ExpandableView) {
        view.findViewById<TextView>(R.id.title).text = title
        view.findViewById<TextView>(R.id.description).text = description
        view.findViewById<TextView>(R.id.longDescription).text = longDescription
    }

    override fun expand() {
        super.expand()
        findViewById<ImageView>(R.id.arrow).animate().rotation(0f).start()
    }

    override fun collapse() {
        super.collapse()
        findViewById<ImageView>(R.id.arrow).animate().rotationBy(-90f).start()

    }

}