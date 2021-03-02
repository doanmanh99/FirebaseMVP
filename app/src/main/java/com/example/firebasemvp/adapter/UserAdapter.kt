package com.example.firebasemvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasemvp.R
import com.example.firebasemvp.model.User
import com.example.firebasemvp.screen.main.MainActivity

class UserAdapter(private var user: ArrayList<User>, private val listener: OnItemCLickListener):RecyclerView.Adapter<UserAdapter.ItemHolder>() {
     inner class ItemHolder(itemview: View) : RecyclerView.ViewHolder(itemview),View.OnClickListener {
        var txvUsername: TextView = itemview.findViewById<TextView>(R.id.txvUsername)
        var txvPassword: TextView = itemview.findViewById<TextView>(R.id.txvPassword)
         init {
            itemview.setOnClickListener(this)
         }
         override fun onClick(v: View?) {
             if (adapterPosition != RecyclerView.NO_POSITION)
                 listener.onItemCLick(adapterPosition)
         }


     }
    interface OnItemCLickListener {
        fun onItemCLick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemHolder(inflater.inflate(R.layout.line_user, parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = user[position]
        holder.txvUsername.text = item.username
        holder.txvPassword.text = item.password
    }

    override fun getItemCount(): Int {
        return user.size
    }
}