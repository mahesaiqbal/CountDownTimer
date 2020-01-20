package com.mahesaiqbal.countdowntimer

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var onpause: Long = 0
    lateinit var countDownTimer: CountDownTimer
    var totaltime = 2500 * 1000.toLong()
    var sec: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start.setOnClickListener {
            try {
                countDownTimer.cancel()
            } catch (e: Exception) {

            }

            totaltime = 2500 * 1000.toLong()

            btn_resume.setEnabled(false)
            btn_pause.setEnabled(true)
            btn_start.setEnabled(false)
            btn_cancel.setEnabled(true)

            fn_countdown(totaltime)
        }

        btn_cancel.setOnClickListener {
            countDownTimer.cancel()

            btn_resume.setEnabled(false)
            btn_pause.setEnabled(false)
            btn_start.setEnabled(true)
            btn_cancel.setEnabled(false)
        }

        btn_pause.setOnClickListener {
            try {
                countDownTimer.cancel()
            } catch (e: Exception) {

            }

            btn_resume.setEnabled(true)
            btn_pause.setEnabled(false)
            btn_start.setEnabled(false)
            btn_cancel.setEnabled(false)
            countDownTimer.cancel()

            totaltime = onpause
//            onpause = totaltime
        }

        btn_resume.setOnClickListener {
            try {
                countDownTimer.cancel()
            } catch (e: Exception) {

            }

            fn_countdown(totaltime)

            btn_resume.setEnabled(false)
            btn_pause.setEnabled(true)
            btn_start.setEnabled(true)
            btn_cancel.setEnabled(true)

//            onpause = totaltime
        }
    }

    private fun fn_countdown(time: Long) {
        countDownTimer = object : CountDownTimer(time, sec) {
            override fun onTick(millisUntilFinished: Long) {
                onpause = millisUntilFinished

                val seconds = (millisUntilFinished / 1000).toInt() % 60
                val minutes = (millisUntilFinished / (1000 * 60) % 60).toInt()
                val hours = (millisUntilFinished / (1000 * 60 * 60) % 24).toInt()
                val newtime = "$hours:$minutes:$seconds"

                if (newtime == "0:0:0") {
                    tv_time.text = "00:00:00"
                } else if (hours.toString().length == 1 && minutes.toString().length == 1 && seconds.toString().length == 1) {
                    tv_time.text = "0$hours:0$minutes:0$seconds"
                } else if (hours.toString().length == 1 && minutes.toString().length == 1) {
                    tv_time.text = "0$hours:0$minutes:$seconds"
                } else if (hours.toString().length == 1 && seconds.toString().length == 1) {
                    tv_time.text = "0$hours:$minutes:0$seconds"
                } else if (minutes.toString().length == 1 && seconds.toString().length == 1) {
                    tv_time.text = "$hours:0$minutes:0$seconds"
                } else if (hours.toString().length == 1) {
                    tv_time.text = "0$hours:$minutes:$seconds"
                } else if (minutes.toString().length == 1) {
                    tv_time.text = "$hours:0$minutes:$seconds"
                } else if (seconds.toString().length == 1) {
                    tv_time.text = "$hours:$minutes:0$seconds"
                } else {
                    tv_time.text = "$hours:$minutes:$seconds"
                }
            }

            override fun onFinish() {
                tv_time.text = 0.toString() + ":" + 0 + ":" + 0
            }
        }.start()
    }
}
