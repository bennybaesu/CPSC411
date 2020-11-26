package org.csuf.cpsc411.simplehttpclient

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView as TextView1
import org.csuf.cpsc411.simplehttpclient.Claim


class ValueColumnGenerator(val ctx : Context) {
    fun generate() : LinearLayout {
        val layoutObj = LinearLayout(ctx)
        val lParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutObj.layoutParams = lParams
        layoutObj.orientation = LinearLayout.VERTICAL
        layoutObj.setBackgroundColor(Color.BLACK)
        //
        val vParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        val lbParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        lbParams.weight = -10.0F
        lbParams.topMargin = 5
        lbParams.bottomMargin = 69
        lbParams.setMargins(5, 30, 5, 50)
        vParams.topMargin = 5
        vParams.bottomMargin = 0
        var value = EditText(ctx)
        value.id = R.id.claim_title
        value.setHint("Enter Claim Title")
        value.setBackgroundColor(Color.LTGRAY)
        layoutObj.addView(value, vParams)
        value = EditText(ctx)
        value.id = R.id.date
        value.setHint("Enter Date")
        value.setBackgroundColor(Color.LTGRAY)
        layoutObj.addView(value, vParams)

        val buttonParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        var lbl = android.widget.TextView(ctx)

        var layoutObj2 = layoutObj

        val addButton = Button(ctx)
        addButton.text = "ADD"
        layoutObj2.addView(addButton, buttonParams)
    var status_string = "Status: "
        addButton.setOnClickListener {
            // Handler code here.
            if (R.id.date == null || R.id.claim_title == null) {
                lbl.text = "Status: Not Added"
            } else {
                lbl.text = "Status: Added"
            }
        }

        lbl.text = status_string
        lbl.gravity = Gravity.CENTER
        lbl.setBackgroundColor(Color.GRAY)
        layoutObj.addView(lbl, lbParams)



        return layoutObj
    }
}