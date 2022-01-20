package com.example.ethmonitor

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.ethmonitor.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_recycler.*
import org.jsoup.Jsoup
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var helper:RoomHelper
    lateinit var memoAdapter:RecyclerAdapter
    val memoList = mutableListOf<RoomMemo>()
    lateinit var memoDAO:RoomMemoDAO
    var contractsList = mutableListOf<RoomMemo>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(toolbar1)
        retrieveWebInfo()
        helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_db")
            .allowMainThreadQueries()
            .build()
        memoDAO = helper.roomMemoDao()
        memoAdapter = RecyclerAdapter(memoList)

        refreshAdapter()

        with(binding){
            recyclerMemo.adapter = memoAdapter
            recyclerMemo.layoutManager = LinearLayoutManager(this@MainActivity)
            refreshAdapter()
        }
        refresh_layout.setOnRefreshListener {
            memoList.clear()
            memoList.addAll(memoDAO.getAll())
            memoAdapter.notifyDataSetChanged()
            refresh_layout.isRefreshing = false
            Toast.makeText(this@MainActivity, "새로고침 완료", Toast.LENGTH_SHORT).show()
        }

        gopax.setOnClickListener {
            val intent = Intent(this, CoinActivity::class.java)
            intent.putExtra("website", "gopax")
            startActivity(intent)
        }
        editcoinText.setOnClickListener {
            val intent = Intent(this, CoinActivity::class.java)
            intent.putExtra("website", "gopax")
            startActivity(intent)
        }
        editcoinText2.setOnClickListener {
            val intent = Intent(this, CoinActivity::class.java)
            intent.putExtra("website", "gopax")
            startActivity(intent)
        }
        editcoinText3.setOnClickListener {
            val intent = Intent(this, CoinActivity::class.java)
            intent.putExtra("website", "gopax")
            startActivity(intent)
        }

        bithumb.setOnClickListener {
            val intent = Intent(this, CoinActivity::class.java)
            intent.putExtra("website", "bithumb")
            startActivity(intent)
        }
        editcoinText4.setOnClickListener {
            val intent = Intent(this, CoinActivity::class.java)
            intent.putExtra("website", "bithumb")
            startActivity(intent)
        }
        editcoinText5.setOnClickListener {
            val intent = Intent(this, CoinActivity::class.java)
            intent.putExtra("website", "bithumb")
            startActivity(intent)
        }
        editcoinText6.setOnClickListener {
            val intent = Intent(this, CoinActivity::class.java)
            intent.putExtra("website", "bithumb")
            startActivity(intent)
        }

        upbit.setOnClickListener {
            val intent = Intent(this, CoinActivity::class.java)
            intent.putExtra("website", "upbit")
            startActivity(intent)
        }
        editcoinText7.setOnClickListener {
            val intent = Intent(this, CoinActivity::class.java)
            intent.putExtra("website", "upbit")
            startActivity(intent)
        }
        editcoinText8.setOnClickListener {
            val intent = Intent(this, CoinActivity::class.java)
            intent.putExtra("website", "upbit")
            startActivity(intent)
        }
        editcoinText9.setOnClickListener {
            val intent = Intent(this, CoinActivity::class.java)
            intent.putExtra("website", "upbit")
            startActivity(intent)
        }

    }
    private fun retrieveWebInfo() {


        thread {
            val doc = Jsoup.connect("https://www.ddengle.com/").get()
            val textElements = doc.select("#exchange-gopax").select("li")
            val percentElements = doc.select("#exchange-gopax").select("span")

            val textElements2 = doc.select("#exchange-bithumb").select("li")
            val percentElements2 = doc.select("#exchange-bithumb").select("span")

            val textElements3 = doc.select("#exchange-upbit").select("li")
            val percentElements3 = doc.select("#exchange-upbit").select("span")
            this.runOnUiThread {
                editcoinText.text = textElements[0].text()
                editcoinText2.text = percentElements[0].text()
                editcoinText3.text = percentElements[1].text()
                if(editcoinText2.text.contains("▲")) editcoinText2.setTextColor(Color.rgb(200,0,0))
                else if(editcoinText2.text.contains("▼")) editcoinText2.setTextColor(Color.rgb(0,0,200))
                if(editcoinText3.text.contains("+")) editcoinText3.setTextColor(Color.rgb(200,0,0))
                else if(editcoinText3.text.contains("-")) editcoinText3.setTextColor(Color.rgb(0,0,200))

                editcoinText4.text = textElements2[0].text()
                editcoinText5.text = percentElements2[0].text()
                editcoinText6.text = percentElements2[1].text()
                if(editcoinText5.text.contains("▲")) editcoinText5.setTextColor(Color.rgb(200,0,0))
                else if(editcoinText5.text.contains("▼")) editcoinText5.setTextColor(Color.rgb(0,0,200))
                if(editcoinText6.text.contains("+")) editcoinText6.setTextColor(Color.rgb(200,0,0))
                else if(editcoinText6.text.contains("-")) editcoinText6.setTextColor(Color.rgb(0,0,200))

                editcoinText7.text = textElements3[0].text()
                editcoinText8.text = percentElements3[0].text()
                editcoinText9.text = percentElements3[1].text()
                if(editcoinText8.text.contains("▲")) editcoinText8.setTextColor(Color.rgb(200,0,0))
                else if(editcoinText8.text.contains("▼")) editcoinText8.setTextColor(Color.rgb(0,0,200))
                if(editcoinText9.text.contains("+")) editcoinText9.setTextColor(Color.rgb(200,0,0))
                else if(editcoinText9.text.contains("-")) editcoinText9.setTextColor(Color.rgb(0,0,200))
            }

        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.add -> {
                val builder = AlertDialog.Builder(this)
                val dialogView = layoutInflater.inflate(R.layout.dialog, null)
                val dialogText = dialogView.findViewById<EditText>(R.id.editName)
                val dialogText2 = dialogView.findViewById<EditText>(R.id.editAddress1)
                val dialogText3 = dialogView.findViewById<EditText>(R.id.editAvgHash1)
                builder.setTitle("장치등록")
                builder.setIcon(R.mipmap.ic_launcher)
                builder.setView(dialogView)
                    .setPositiveButton("저장") { dialogInterface, i ->
                        helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_db")
                            .allowMainThreadQueries()
                            .build()
                        memoDAO = helper.roomMemoDao()
                        memoAdapter = RecyclerAdapter(memoList)

                        refreshAdapter()

                        with(binding){
                            recyclerMemo.adapter = memoAdapter
                            recyclerMemo.layoutManager = LinearLayoutManager(this@MainActivity)
                            val memo = RoomMemo(dialogText.text.toString(),dialogText2.text.toString(),dialogText3.text.toString())
                            memoDAO.insert(memo)
                            refreshAdapter()
                        }
                    }
                    .setNegativeButton("취소") { dialogInterface, i ->

                    }
                builder.show()
            }
            R.id.delete -> {
                AlertDialog.Builder(this)
                    .setMessage("전체 삭제")
                    .setPositiveButton("확인", object : DialogInterface.OnClickListener {
                     override fun onClick(dialog: DialogInterface, which: Int) {
                           memoDAO.delete()
                            refreshAdapter()
                      }
                    })
                    .setNegativeButton("취소", object : DialogInterface.OnClickListener {
                         override fun onClick(dialog: DialogInterface, which: Int) {
                    }
                })
                    .create()
                    .show()
            }
        }
        val adapter = RecyclerAdapter(contractsList)
        return super.onOptionsItemSelected(item)
    }
    fun refreshAdapter(){
        memoList.clear()
        memoList.addAll(memoDAO.getAll())
        memoAdapter.notifyDataSetChanged()
    }
}