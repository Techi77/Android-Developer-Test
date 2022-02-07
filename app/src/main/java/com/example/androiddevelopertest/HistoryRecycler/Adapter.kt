package com.example.androiddevelopertest.HistoryRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevelopertest.FragmentMain.Retrofit.Users
import com.example.androiddevelopertest.databinding.MyCardsElementBinding
import com.squareup.picasso.Picasso

class Adapter(private val historyInfoList: Users):RecyclerView.Adapter<Adapter.MyViewHolder>() {

    class MyViewHolder(binding: MyCardsElementBinding): RecyclerView.ViewHolder(binding.root){

        val cardNumber: TextView = binding.tvCustomCardNumber

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MyCardsElementBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return historyInfoList.users.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.cardNumber.text = historyInfoList.users[0].transaction_history[position].amount
    }

}