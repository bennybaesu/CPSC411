package org.csuf.cpsc411.simplehttpclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText

class CustomActivity : AppCompatActivity() {
    lateinit var cList : MutableList<Claim>

    fun refreshScreen(cObj : Claim) {
        //
        Log.d("Claim Service", "Refreshing the Screen. ")
        val claimTitleView : EditText = findViewById(R.id.claim_title)
        val dateView : EditText = findViewById(R.id.date)
        val statusView : EditText = findViewById(R.id.status)
        //
        claimTitleView.setText(cObj.claimTitle)
        dateView.setText(cObj.date)
        statusView.setText(cObj.status)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val fldRowGenerator = FieldValueViewGenerator(this, "SSN")
        //val colGenerator = LableColumnGenerator(this)
        //val colView = colGenerator.generate()
        val fldRowGenerator = ObjDetailSectionGenerator(this)
        val colView = fldRowGenerator.generate()
        setContentView(colView)
        //
        ClaimService(this).getAll()

        //setContentView(R.layout.activity_main)
    }
}