package com.example.radmushroom.breathe

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import com.example.radmushroom.breathe.util.Prefs
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = Prefs(this)

        startIntroAnimation()
        sessionTxt.text = "${prefs.getSessions()} minutes today"
        breathsTakenTxt.text = "${prefs.getBreaths()} Breaths"
        lastBreathTxt.text = "Last time: ${prefs.getDate()}"

        startButton.setOnClickListener {
            startAnimation()
        }
    }


    private fun startIntroAnimation(){
        ViewAnimator
                .animate(guideTxt)
                .scale(0f, 1f)
                .duration(1500)
                .onStart({
                    guideTxt.text = "Breathe"
                })
                .start()
    }

    private fun startAnimation(){
        ViewAnimator
                .animate(lotusImage)
                .alpha(0f, 1f)
                .onStart({
                   guideTxt.text = "Inhale...Exhale"
                })
                .decelerate()
                .duration(1000)
                .thenAnimate(lotusImage)
                .scale(0.02f, 1.5f, 0.02f)
                .rotation(360f)
                .repeatCount(5)
                .accelerate()
                .duration(5000)
                .onStop { guideTxt.text = "Good job"
                          lotusImage.scaleX = 1.0f
                          lotusImage.scaleY = 1.0f
                    prefs.setSessions(prefs.getSessions() + 1)
                    prefs.setBreaths(prefs.getBreaths() + 1)
                    prefs.setDate(System.currentTimeMillis())

                    //refreshActivity
                    object : CountDownTimer(2000, 1000) {
                        override fun onFinish() {
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                            finish()
                        }

                        override fun onTick(p0: Long) {
                            //put code to show ticking
                        }
                    }.start()

                }
                .start()
    }
}
