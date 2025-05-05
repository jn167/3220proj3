package com.example.test2

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.test2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentBackgroundColor = Color.WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set initial background color
        currentBackgroundColor = ContextCompat.getColor(this, android.R.color.white)
        binding.mainLayout.setBackgroundColor(currentBackgroundColor)

        // Set click listeners for buttons
        binding.redButton.setOnClickListener {
            changeImageWithAnimation(R.drawable.image2, R.color.red_background)
        }

        binding.greenButton.setOnClickListener {
            changeImageWithAnimation(R.drawable.image3, R.color.green_background)
        }

        binding.blueButton.setOnClickListener {
            changeImageWithAnimation(R.drawable.image4, R.color.blue_background)
        }
    }

    private fun changeImageWithAnimation(imageResId: Int, colorResId: Int) {
        // Image fade animation
        val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        binding.imageView.startAnimation(fadeIn)
        binding.imageView.setImageResource(imageResId)

        // Background color transition
        val newColor = ContextCompat.getColor(this, colorResId)
        val colorAnimation = ValueAnimator.ofObject(
            ArgbEvaluator(),
            currentBackgroundColor,
            newColor
        )
        colorAnimation.duration = 500
        colorAnimation.addUpdateListener { animator ->
            binding.mainLayout.setBackgroundColor(animator.animatedValue as Int)
        }
        colorAnimation.start()

        currentBackgroundColor = newColor
    }
}