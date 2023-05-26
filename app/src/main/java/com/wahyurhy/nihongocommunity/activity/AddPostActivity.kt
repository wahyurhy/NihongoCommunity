package com.wahyurhy.nihongocommunity.activity

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.github.dhaval2404.imagepicker.ImagePicker
import com.wahyurhy.nihongocommunity.R
import com.wahyurhy.nihongocommunity.databinding.ActivityAddPostBinding

class AddPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClick()
    }

    private fun initClick() {
        val startForProfileImageResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                val resultCode = result.resultCode
                val data = result.data

                when (resultCode) {
                    Activity.RESULT_OK -> {
                        //Image Uri will not be null for RESULT_OK
                        val fileUri = data?.data!!

                        binding.imageView.setImageURI(fileUri)
                        binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                        binding.btnAddImage.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            null,
                            null,
                            null,
                            ResourcesCompat.getDrawable(resources, R.drawable.ic_edit_image, null)
                        )
                    }
                    ImagePicker.RESULT_ERROR -> {
                        Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        binding.btnAddImage.setOnClickListener {
            ImagePicker.with(this)
                .crop(
                    1080f,
                    680f
                )                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )    //Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }
    }
}