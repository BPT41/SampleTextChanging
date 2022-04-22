package com.example.ch13activityintent

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class TextSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_setting)

        val size = intent.getIntExtra("size",14)
        val color = intent.getStringExtra("color")
        val styles = intent.getStringArrayListExtra("styles")

        val editSize = findViewById<EditText>(R.id.editText_size)
        editSize.setText("$size")

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        when(color!!.toLowerCase()){
            "red" -> radioGroup.check(R.id.radio_red)
            "green" -> radioGroup.check(R.id.radio_Green)
            "blue"-> radioGroup.check(R.id.radio_blue)
        }
        val checkBold = findViewById<CheckBox>(R.id.check_bold)
        val checkItalic = findViewById<CheckBox>(R.id.check_italic)
        if(styles!!.contains("bold")){
            checkBold.isChecked = true
        }
        if(styles.contains("italic")){
            checkItalic.isChecked = true
        }
        findViewById<Button>(R.id.button_ok).apply {
            setOnClickListener{
                if(editSize.text.isEmpty()){
                    editSize.setText("$size")
                }
                val sz:Int = editSize.text.toString().toInt()
                if(sz !in 14..40){
                    toast("ขนาดต้องอยู่ระหว่าง 14-40")
                    return@setOnClickListener
                }
                val co = when(radioGroup.checkedRadioButtonId){
                    R.id.radio_red ->"red"
                    R.id.radio_Green->"green"
                    R.id.radio_blue->"blue"
                    else -> color
                }
                val st = arrayListOf<String>()
                if(checkBold.isChecked){
                    st.add("bold")
                }
                if(checkItalic.isChecked){
                    st.add("italic")
                }
                intent.apply{
                    putExtra("size",sz)
                    putExtra("color",co)
                    putStringArrayListExtra("styles",st)
                }
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }

    }

    private fun toast(msg: String) {
        Toast.makeText(baseContext,msg,Toast.LENGTH_LONG).show()
    }
}