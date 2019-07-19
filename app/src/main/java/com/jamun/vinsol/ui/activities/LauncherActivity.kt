package com.jamun.vinsol.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.jamun.vinsol.R
import com.jamun.vinsol.utils.AnimationUtils
import com.jamun.vinsol.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : BaseActivity() {
    private var runnableHome = Runnable { this@LauncherActivity.onStartActivity() }
    private var handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        initializeView()
    }

    override fun initializeView() {
        super.initializeView()
        AnimationUtils.startAnimation(this, 1000, id_image)
        handler.postDelayed(runnableHome, 1500)
    }

    private fun onStartActivity() {
        startActivity(Intent(this@LauncherActivity, MainActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        closeEverything()
    }

    override fun onDestroy() {
        super.onDestroy()
        closeEverything()
    }

    override fun closeEverything() {
        super.closeEverything()
        handler.removeCallbacks(runnableHome)
    }
}
