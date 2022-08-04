package com.irfanirawansukirman.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import com.irfanirawansukirman.about.databinding.AboutActivityBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: AboutActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        setContentView(binding.root)

        // show profile image
        binding.ivProfile.load("https://media-exp3.licdn.com/dms/image/C5103AQGWrgmD5-pIMw/profile-displayphoto-shrink_200_200/0/1517211446126?e=1631750400&v=beta&t=ZyiEvZoGDgAB2AfoJ6v5cz1VhbpvoF_JQWX2N1P0xYI") {
            crossfade(true)
            transformations(CircleCropTransformation())
        }

        // show email
        binding.tvEmail.text = "dadang.kotz@gmail.com"
    }

    private fun initBinding() {
        if (!::binding.isInitialized) {
            binding = AboutActivityBinding.inflate(layoutInflater)
        }
    }
}