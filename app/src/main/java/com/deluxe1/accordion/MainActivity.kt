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
                TitleAccordionElement(this, "Test title 1", "realllllllllllllllllllllllllllyyyyyyyyyyyyyyyyyyyyyyyyyy long description"),
                DescriptionAccordionElement(this, "Test title", "Test description1111", "lorem imfsdjfdskflsdjfkl sdjfklsdfjsdkfjsdk jsdfklsdjfkl jfklsdjfklsdjf dslkfjsd ldfl;"),
                AccordionElementBuilder(this).setTitle("Test Builder").setSubtitle("Builder subtitle").setImageRes(android.R.drawable.ic_input_add).setClickableViewColor(getColor(android.R.color.holo_blue_dark)).setLongDescription("jdsfgkldfglkdfgfklgkldfl").setExpandableViewColor(getColor(R.color.design_default_color_primary_dark)).build(),
                ImageAccordionElement(this, "Test image", "Image desc", "Long desc sdkdflsfjdsklfkl lksdflksd", imgRes = android.R.drawable.btn_star)
            ))
    }
}