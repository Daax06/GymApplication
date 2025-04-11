package com.application.testing

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView

private lateinit var textView: TextView
private lateinit var editText: EditText
private lateinit var button: Button
private lateinit var switchToggle: Switch
private lateinit var imageView: ImageView

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    textView = findViewById(R.id.textView)
    editText = findViewById(R.id.editText)
    button = findViewById(R.id.button)
    switchToggle = findViewById(R.id.switchToggle)
    imageView = findViewById(R.id.imageView)

    // Button Click Listener: Update TextView with EditText input
    button.setOnClickListener {
        textView.text = editText.text.toString()
    }

    // Switch Change Listener: Toggle ImageView Visibility
    switchToggle.setOnCheckedChangeListener { _, isChecked ->
        imageView.visibility = if (isChecked) View.VISIBLE else View.INVISIBLE
    }

    // EditText Focus Listener: Change background color on focus
    editText.setOnFocusChangeListener { _, hasFocus ->
        editText.setBackgroundColor(if (hasFocus) Color.LTGRAY else Color.WHITE)
    }
}