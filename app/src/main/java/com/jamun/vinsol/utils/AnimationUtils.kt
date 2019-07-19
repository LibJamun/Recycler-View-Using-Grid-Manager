package com.jamun.vinsol.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.jamun.vinsol.R

object AnimationUtils {

    fun startAnimation(context: Context, timeDuration: Int, imageView: ImageView) {
        val animatorSet = AnimatorSet()
        val rotationAnim = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
        rotationAnim.duration = timeDuration.toLong()
        rotationAnim.interpolator = AccelerateDecelerateInterpolator()
        val bounceAnimX = ObjectAnimator.ofFloat(imageView, "scaleX", 0.2f, 1f)
        bounceAnimX.duration = timeDuration.toLong()
        bounceAnimX.interpolator = OvershootInterpolator()
        val bounceAnimY = ObjectAnimator.ofFloat(imageView, "scaleY", 0.2f, 1f)
        bounceAnimY.duration = timeDuration.toLong()
        bounceAnimY.interpolator = OvershootInterpolator()
        bounceAnimY.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorWhite))
            }
        })
        animatorSet.play(rotationAnim)
        animatorSet.play(bounceAnimX).with(bounceAnimY).after(rotationAnim)
        animatorSet.start()
    }


}
