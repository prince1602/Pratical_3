package com.example.pratical_3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // UI references
        val browserButton = findViewById<Button>(R.id.button)
        val urlInput = findViewById<EditText>(R.id.editTextText2)

        val callButton = findViewById<Button>(R.id.button1)
        val phoneInput = findViewById<EditText>(R.id.editTextPhone)

        val callLogButton = findViewById<Button>(R.id.button2)
        val galleryButton = findViewById<Button>(R.id.button3)
        val cameraButton = findViewById<Button>(R.id.button4)
        val alarmButton = findViewById<Button>(R.id.button5)
        val loginButton = findViewById<Button>(R.id.button6)

        // Browser
        browserButton.setOnClickListener {
            val url = urlInput.text.toString().trim()
            if (url.isEmpty()) {
                Toast.makeText(this, "Please enter a URL", Toast.LENGTH_SHORT).show()
            } else {
                var finalUrl = url
                if (!finalUrl.startsWith("http://") && !finalUrl.startsWith("https://")) {
                    finalUrl = "http://$finalUrl"
                }
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl))
                startActivity(intent)
            }
        }

        // Call
        callButton.setOnClickListener {
            val number = phoneInput.text.toString().trim()
            if (number.isEmpty()) {
                Toast.makeText(this, "Please enter the number", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
                startActivity(intent)
            }
        }

        // Call Log
        callLogButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("content://call_log/calls"))
            startActivity(intent)
        }

        // Gallery
        galleryButton.setOnClickListener {
            val intent = Intent( Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"))
            startActivity(intent)
        }

        // Camera
        cameraButton.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

        // Alarm Explicit intent
        alarmButton.setOnClickListener {
            val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
            startActivity(intent)
        }

        // Login Activity
        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}