package com.example.ch13activityintent

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val REQ_CODE = 5555
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_setting).apply {
            setOnClickListener{
                val intent = Intent (this@MainActivity,TextSettingActivity::class.java)

                intent.apply{
                    putExtra("size",14)
                    putExtra("color","green")
                    putStringArrayListExtra("styles", arrayListOf("normal"))
                }
                startActivityForResult(intent,REQ_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != REQ_CODE || resultCode != Activity.RESULT_OK){
            return
        }
        val size = data?.getIntExtra("size",14)
        val color = data?.getStringExtra("color")
        val col = when(color){
            "red" ->"#990000"
            "green"-> "#009900"
            "blue"-> "#000099"
            else -> "#009900"
        }
        val styles = data?.getStringArrayListExtra("styles")
        var typeFace = Typeface.NORMAL
        if(styles!!.contains("bold")){
            typeFace = typeFace or  Typeface.BOLD
        }
        if (styles!!.contains("italic")){
            typeFace = Typeface.ITALIC
        }
        val sampleText = findViewById<TextView>(R.id.textVew_sample)
        sampleText.apply{
            textSize = size!!.toFloat()
            setTextColor(Color.parseColor(col))
            setTypeface(null, typeFace)
        }
    }
}