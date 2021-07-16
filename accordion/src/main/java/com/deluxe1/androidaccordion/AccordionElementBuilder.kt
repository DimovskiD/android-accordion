package com.deluxe1.androidaccordion

import android.content.Context
import com.deluxe1.androidaccordion.elements.DescriptionAccordionElement
import com.deluxe1.androidaccordion.elements.ImageAccordionElement
import com.deluxe1.androidaccordion.elements.TitleAccordionElement

class AccordionElementBuilder(
    private var context: Context,
    private var title: String = "",
    private var subtitle: String = "",
    private var longDescription: String = "",
    private var imageRes: Int = -1,
    private var clickableViewColor: Int = -1,
    private var expandableViewColor: Int = -1
) {

    fun setTitle(title: String): AccordionElementBuilder {
        this.title = title
        return this
    }

    fun setSubtitle(subtitle: String): AccordionElementBuilder {
        this.subtitle = subtitle
        return this
    }

    fun setLongDescription(longDescription: String): AccordionElementBuilder {
        this.longDescription = longDescription
        return this
    }

    fun setImageRes(imageRes: Int): AccordionElementBuilder {
        this.imageRes = imageRes
        return this
    }

    fun setClickableViewColor(color: Int): AccordionElementBuilder {
        this.clickableViewColor = color
        return this
    }

    fun setExpandableViewColor(color: Int): AccordionElementBuilder {
        this.expandableViewColor = color
        return this
    }

    fun build(): ExpandableView {
        var expandableView: ExpandableView =
            TitleAccordionElement(context, this.title, this.longDescription)
        if (subtitle.isNotEmpty()) expandableView =
            DescriptionAccordionElement(context, title, subtitle, longDescription)
        if (imageRes != -1) expandableView =
            ImageAccordionElement(context, title, subtitle, longDescription, imageRes)
        if (clickableViewColor != -1) expandableView.setClickableViewColor(clickableViewColor)
        if (expandableViewColor != -1) expandableView.setExpandableViewColor(expandableViewColor)
        return expandableView

    }
}