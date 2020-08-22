package com.tutorial.mytodolistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Start animation at start
        todo_icon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_in))

        //Delay it for some seconds
        Handler().postDelayed({
            todo_icon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_out))
            Handler().postDelayed({
                todo_icon.visibility = View.GONE
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }, 500)
        }, 1500)
    }
}