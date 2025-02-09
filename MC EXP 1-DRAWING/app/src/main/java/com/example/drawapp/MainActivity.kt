package com.example.drawapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity // Ensure this import
import yuku.ambilwarna.AmbilWarnaDialog // Ensure this import

class MainActivity : AppCompatActivity() { // Use AppCompatActivity for compatibility

    private lateinit var drawingView: DrawingView
    private var currentColor: Int = Color.BLACK
    private var currentBrushSize: Float = 10f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawingView)
        val clearButton: Button = findViewById(R.id.clearButton)
        val colorPickerButton: Button = findViewById(R.id.colorPickerButton)
        val brushSizeSeekBar: SeekBar = findViewById(R.id.brushSizeSeekBar)

        // Clear Canvas
        clearButton.setOnClickListener {
            drawingView.clearCanvas()
        }

        // Open Color Picker
        colorPickerButton.setOnClickListener {
            showColorPickerDialog() // Ensure this method is inside the class
        }

        // Brush Size Selection
        brushSizeSeekBar.progress = currentBrushSize.toInt()
        brushSizeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                currentBrushSize = progress.coerceIn(1, 50).toFloat() // Ensure valid size range
                drawingView.changeBrushSize(currentBrushSize)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    // ✅ Make sure this function is inside the class
    private fun showColorPickerDialog() {
        val colorPicker = AmbilWarnaDialog(this, currentColor, object : AmbilWarnaDialog.OnAmbilWarnaListener {
            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                currentColor = color
                drawingView.changeColor(color)
            }

            override fun onCancel(dialog: AmbilWarnaDialog?) {}
        })
        colorPicker.show()
    }
}
