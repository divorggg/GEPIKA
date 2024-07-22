package com.example.projectmagangapp.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmagangapp.MainActivity
import com.example.projectmagangapp.databinding.ActivitySplashBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.thecode.onboardingviewagerexamples.utils.Animatoo

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        binding.apply {
            viewPager.adapter = SplashAdapter(
                this@SplashActivity,
                this@SplashActivity
            )
            TabLayoutMediator(binding.pageIndicator, viewPager) { _, _ -> }.attach()
            textSkip.setOnClickListener {
                finish()
                val intent =
                    Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                Animatoo.animateSlideLeft(this@SplashActivity)
            }

            btnNextStep.setOnClickListener {
                if (getItem() > viewPager.childCount) {
                    finish()
                    val intent =
                        Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    Animatoo.animateSlideLeft(this@SplashActivity)
                } else {
                    viewPager.setCurrentItem(getItem() + 1, true)
                }
            }
        }


    }

    private fun getItem(): Int {
        return binding.viewPager.currentItem
    }

}