package com.deluxe1.accordion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.deluxe1.androidaccordion.AccordionLayout
import com.deluxe1.androidaccordion.ExpandableView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<AccordionLayout>(R.id.accordion).addCustomViews(
            arrayListOf(TestLayout(this), TestLayout(this), DifferentTestLayout(this)))

        findViewById<AccordionLayout>(R.id.accordion).addCustomView(object : TestLayout(this) {
            override fun onExpandableViewAdded(view: ExpandableView) {
                super.onExpandableViewAdded(view)
                findViewById<TextView>(R.id.title).text = "aaaa"
            }
        })

    }
}