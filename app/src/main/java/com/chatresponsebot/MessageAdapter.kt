package com.chatresponsebot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chatresponsebot.objects.Constants.receiver_id
import com.chatresponsebot.objects.Constants.sender_id
import kotlinx.android.synthetic.main.item_list.view.*

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageVH>(){

    var messageList = mutableListOf<Message>()


    inner class MessageVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                messageList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageVH {
        return  MessageVH(LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false))
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: MessageVH, position: Int) {

        val currentMessage = messageList[position]
        when (currentMessage.id){

            sender_id -> {
                holder.itemView.userMessage_TextView.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.botMessage_TextView.visibility = View.GONE
            }

            receiver_id -> {
                holder.itemView.botMessage_TextView.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.userMessage_TextView.visibility = View.GONE
            }
        }

    }

    fun insertMessage(message: Message){
        this.messageList.add(message)
        notifyItemInserted(messageList.size)
    }

}