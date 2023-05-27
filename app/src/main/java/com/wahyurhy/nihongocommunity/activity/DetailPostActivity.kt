package com.wahyurhy.nihongocommunity.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wahyurhy.nihongocommunity.databinding.ActivityDetailPostBinding

class DetailPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.attachment.setOnClickListener {
            Toast.makeText(this, "Attachment clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}