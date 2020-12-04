package com.chatresponsebot

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chatresponsebot.objects.Constants.open_Google
import com.chatresponsebot.objects.Constants.open_Search
import com.chatresponsebot.objects.Constants.receiver_id
import com.chatresponsebot.objects.Constants.sender_id
import com.chatresponsebot.objects.Response
import com.chatresponsebot.objects.Time
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : MessageAdapter
    private val bots = listOf("Walt","Jesse","Badger","Gus")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView()
        click()
        openedAgain()
        val random = (0..3).random()
        customMessage("Hello this is ${bots[random]}, what can I help you with?")

    }

    private fun click() {
        sendMessage_Button.setOnClickListener {
            sendMessage()
        }

        input_EditText.setOnClickListener {
            GlobalScope.launch {
                delay(100)
                withContext(Dispatchers.Main){
                    recyclerView.scrollToPosition(adapter.itemCount-1)
                }
            }
        }

    }
    // Remember where user left off
    private fun openedAgain(){
        super.onStart()
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                recyclerView.scrollToPosition(adapter.itemCount-1)
            }
        }
    }

    private fun recyclerView(){
        adapter = MessageAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun sendMessage(){
        val message = input_EditText.text.toString()
        val timeStamp = Time.timeStampCreator()
        if(message.isNotEmpty()){
            input_EditText.setText("")
            adapter.insertMessage(Message(message,sender_id,timeStamp))
            recyclerView.scrollToPosition(adapter.itemCount-1)
            response(message)
        }
    }

    private fun response(message: String) {
        val timeStamp = Time.timeStampCreator()
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                val response = Response.normalResponses(message)
                adapter.insertMessage(Message(response, receiver_id ,timeStamp))
                recyclerView.scrollToPosition(adapter.itemCount-1)

                when(response ){
                    open_Google -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://wwww.google.com/")
                        startActivity(site)
                    }
                }

                when(response ){
                    open_Search -> {
                        val search = Intent(Intent.ACTION_VIEW)
                        val searchGiven : String = message.substringAfter("search")
                        search.data = Uri.parse("https://wwww.google.com/search?&q=$searchGiven")
                        startActivity(search)
                    }
                }

            }
        }

    }

    private fun customMessage(message: String){
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                val timeStamp = Time.timeStampCreator()
                adapter.insertMessage(Message(message,receiver_id,timeStamp))
                recyclerView.scrollToPosition(adapter.itemCount-1)
            }
        }
    }


}