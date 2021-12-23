package com.example.ethmonitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_calc.*
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class CalcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)
        retrieveWebInfo()
        goHomeButton2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun retrieveWebInfo() {
        val avg_hash = intent.getStringExtra("avg_hash")
        thread {
            val doc = Jsoup.connect("https://miningcalc.kr/calc/eth/" + "$avg_hash").get()
            val textElements = doc.getElementsByTag("p")
            this.runOnUiThread {
                editdayCalc.text = textElements[5].text()
                editweekCalc.text = textElements[7].text()
                editmonthCalc.text = textElements[9].text()
                edityearCalc.text = textElements[11].text()
            }
        }
    }
}