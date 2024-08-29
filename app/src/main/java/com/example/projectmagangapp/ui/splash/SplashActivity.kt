package com.example.projectmagangapp.ui.splash

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmagangapp.MainActivity
import com.example.projectmagangapp.databinding.ActivitySplashBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.thecode.onboardingviewagerexamples.utils.Animatoo

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)

        if (isFirstLaunch()) {
            setupViewPager()
        } else {
            navigateToMainActivity()
        }
    }

    private fun setupViewPager() {
        binding.apply {
            viewPager.adapter = SplashAdapter(
                this@SplashActivity,
                this@SplashActivity
            )
            TabLayoutMediator(binding.pageIndicator, viewPager) { _, _ -> }.attach()
            textSkip.setOnClickListener {
                finish()
                navigateToMainActivity()
            }

            btnNextStep.setOnClickListener {
                if (getItem() > viewPager.childCount) {
                    finish()
                    navigateToMainActivity()
                } else {
                    viewPager.setCurrentItem(getItem() + 1, true)
                }
            }
        }
    }

    private fun getItem(): Int {
        return binding.viewPager.currentItem
    }

    private fun isFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean("isFirstLaunch", true)
    }

    private fun navigateToMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        Animatoo.animateSlideLeft(this@SplashActivity)
        finish()
        // Set isFirstLaunch to false to avoid showing splash screen again
        sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
    }
}
