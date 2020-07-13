package com.example.chiptogglebutton

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myButton.text = "Optional"

        myButton.borderWidth = 8
        myButton.radius = 60f
        myButton.unSelectedBorderColor = Color.CYAN

        myButton.setToggleButton(true)

        myButton.status.observe(this, Observer { value ->
            Toast.makeText(this, "Status is $value", Toast.LENGTH_SHORT).show()
        })

        myButton.setOnClickListener {
            Log.i(
                "MainActivity",
                "onClickListener"
            )
        }
    }
}