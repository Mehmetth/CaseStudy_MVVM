package com.honeycomb.casestudy.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.honeycomb.casestudy.R

class AndroidUtils {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var globalContext : Context

        fun shakeView(view: View?, x: Int, num: Int) {
            if (view == null) {
                return
            }
            if (num == 6) {
                view.translationX = 0f
                return
            }
            val animatorSet = AnimatorSet()
            val value = view.resources.displayMetrics.density * x
            animatorSet.playTogether(ObjectAnimator.ofFloat(view, "translationX", value))
            animatorSet.duration = 50
            animatorSet.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    shakeView(view, if (num == 5) 0 else -x, num + 1)
                }
            })
            animatorSet.start()
        }

        fun showError(view: View, message: String): Snackbar {
            return Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction(globalContext.getString(R.string.close)){}
        }

        fun showServiceProgressBar(view: View, value: Boolean){
            if(value){
                view.visibility = View.VISIBLE
            }else{
                view.visibility = View.INVISIBLE
            }
        }
        fun showServiceErrorImage(view: View, value: Boolean){
            if(value){
                view.visibility = View.VISIBLE
            }else{
                view.visibility = View.INVISIBLE
            }
        }
        fun showServiceError(view: View, error: String){
            val snackBar = showError(view,error)
            snackBar.show()
            shakeView(snackBar.view, 10, 0)
        }
    }
}