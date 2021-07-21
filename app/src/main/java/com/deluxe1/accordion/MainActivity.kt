package com.deluxe1.accordion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deluxe1.androidaccordion.AccordionElementBuilder
import com.deluxe1.androidaccordion.AccordionLayout
import com.deluxe1.androidaccordion.elements.DescriptionAccordionElement
import com.deluxe1.androidaccordion.elements.ImageAccordionElement
import com.deluxe1.androidaccordion.elements.TitleAccordionElement

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<AccordionLayout>(R.id.accordion).addCustomViews(
            arrayListOf(
                TitleAccordionElement(this, getString(R.string.test_title), getString(R.string.lorem_ipsum)),
                DescriptionAccordionElement(
                    this,
                    getString(R.string.test_title),
                    getString(R.string.test_description),
                    getString(R.string.lorem_ipsum)
                ),
                AccordionElementBuilder(this).setTitle(getString(R.string.test_builder))
                    .setSubtitle(getString(R.string.builder_subtitle)).setImageRes(android.R.drawable.ic_input_add)
                    .setClickableViewColor(getColor(android.R.color.holo_blue_dark))
                    .setLongDescription(getString(R.string.lorem_ipsum))
                    .setExpandableViewColor(getColor(R.color.design_default_color_secondary))
                    .build(),
                ImageAccordionElement(
                    this,
                    getString(R.string.test_image),
                    getString(R.string.image_desc),
                    getString(R.string.lorem_ipsum),
                    imgRes = android.R.drawable.btn_star
                )
            )
        )
    }
}