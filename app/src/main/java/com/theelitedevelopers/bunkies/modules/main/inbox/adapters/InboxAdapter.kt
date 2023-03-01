package com.theelitedevelopers.bunkies.modules.main.inbox.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.theelitedevelopers.bunkies.databinding.InboxInLayoutBinding
import com.theelitedevelopers.bunkies.databinding.InboxOutLayoutBinding
import com.theelitedevelopers.bunkies.modules.main.data.models.Inbox

class InboxAdapter(var context : Context, var messageList : ArrayList<Inbox>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val RECEIVE = 0
    val SEND = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 0){
            //inflate the Received Layout
            val binding : InboxInLayoutBinding = InboxInLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return ReceivedViewHolder(binding)
        }else {
            //inflate the Send Layout
            val binding : InboxOutLayoutBinding = InboxOutLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            return SentViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder.javaClass == SentViewHolder::class.java){
            val viewHolder = holder as SentViewHolder

            //set the message
            holder.binding.inboxOutMessage.text = messageList[position].message

            //holder.binding.inboxOutMessage.text = AppUtils.convertStringToDate(messageList[position].date!!)

        }else {
            val viewHolder = holder as ReceivedViewHolder

            //set the message
            holder.binding.inboxInMessage.text = messageList[position].message

            //holder.binding.inboxInDate.text = AppUtils.convertStringToDate(messageList[position].date!!)

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(FirebaseAuth.getInstance().uid.equals(messageList[position].senderId)){
            SEND
        }else {
            RECEIVE
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class SentViewHolder(var binding : InboxOutLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    class ReceivedViewHolder(var binding : InboxInLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}