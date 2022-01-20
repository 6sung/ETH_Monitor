package com.example.ethmonitor

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ethmonitor.databinding.ItemRecyclerBinding
import org.jetbrains.anko.doAsync
import org.jsoup.Jsoup

class RecyclerAdapter(val roomMemoList:List<RoomMemo>) : RecyclerView.Adapter<RecyclerAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setMemo(roomMemoList.get(position))

    }
    override fun getItemCount() = roomMemoList.size
    class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setMemo(roomMemo: RoomMemo) {
            with(binding) {
                textID.text = roomMemo.dvvName
                var review : String = ""
                review = roomMemo.ipAddress
                var textHash1: String = ""
                textHash1 = roomMemo.avg_hash
                doAsync {
                    val doc = Jsoup.connect("http://"+"$review").get()
                    val textElements = doc.getElementsByTag("font")
                    var abc: Int = 0
                    while (true) {
                        if (textElements[abc].text().contains("Average")) break
                        else abc++
                    }
                    textHash.text = textElements[abc].text().substring(27,textElements[abc].text().length)
                }
                doAsync {
                    val doc = Jsoup.connect("http://"+"$review").get()
                    val textElements = doc.getElementsByTag("font")
                    var abc:Int =0
                    while(true){
                        if(textElements[abc].text().contains("shares:")) break
                        else abc++
                    }
                    var share2 : Int =0
                    var front : Int =23
                    var end : Int = 0
                    var compare : String = ""
                    while(true){
                        compare = textElements[abc].text().substring(front,front+2)
                        if(compare == "sh") break
                        else front++

                    }
                    end = front
                    while(true){
                        compare = textElements[abc].text().substring(end,end+1)
                        if( compare == ",") break
                        else end++
                    }
                    textShares.text = textElements[abc].text().substring(front,end)
                }
                doAsync {
                    val doc = Jsoup.connect("http://"+"$review").get()
                    val textElements = doc.getElementsByTag("font")
                    var abc:Int =0
                    while(true){
                        if(textElements[abc].text().contains("time:") || textElements[abc].text().contains("Time:")) break
                        else abc++
                    }
                    var share2 : Int =0
                    var front : Int =23
                    var compare : String = ""
                    while(true){
                        compare = textElements[abc].text().substring(front,front+2)
                        if(compare == "ti" || compare == "Ti") break
                        else front++

                    }
                    textUpTime.text = textElements[abc].text().substring(front,textElements[abc].text().length)
                }
                doAsync {
                    val doc = Jsoup.connect("http://"+"$review").get()
                    val textElements = doc.getElementsByTag("font")
                    var abc:Int =0
                    while(true){
                        if(textElements[abc].text().contains("GPU1:") && textElements[abc].text().contains("%")) break
                        else abc++
                    }
                    var share2 : Int =0
                    var front : Int =5
                    var compare : String = ""
                    while(true){
                        compare = textElements[abc].text().substring(front,front+1)
                        if(compare == "C") break
                        else front++

                    }
                    textTem.text = "Temperature:"+textElements[abc].text().substring(5,front+1)
                }
                doAsync {
                    val doc = Jsoup.connect("http://"+"$review").get()
                    val textElements = doc.getElementsByTag("font")
                    var abc:Int =0
                    while(true){
                        if(textElements[abc].text().contains("power:")) break
                        else abc++
                    }
                    var share2 : Int =0
                    var front : Int =0
                    var end : Int = 0
                    var compare : String = ""
                    while(true){
                        compare = textElements[abc].text().substring(front,front+4)
                        if(compare == "GPUs") break
                        else front++

                    }
                    end = front
                    while(true){
                        compare = textElements[abc].text().substring(end,end+1)
                        if( compare == "W") break
                        else end++
                    }
                    textPower.text = textElements[abc].text().substring(front,end+1)
                }
                monitorCMD.setOnClickListener {
                    //화면이동
                    val intent = Intent(itemView.context, MonitorActivity::class.java)
                    intent.putExtra("ip_port", review)
                    startActivity(itemView.context, intent, null)
                }
                goToCalc.setOnClickListener {
                    //화면이동
                    val intent = Intent(itemView.context, CalcActivity::class.java)
                    intent.putExtra("avg_hash", textHash1)
                    startActivity(itemView.context, intent, null)
                }
            }
        }
    }
}



