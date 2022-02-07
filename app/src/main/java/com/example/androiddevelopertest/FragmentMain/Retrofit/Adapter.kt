package com.example.androiddevelopertest.FragmentMain.Retrofit

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevelopertest.R
import com.example.androiddevelopertest.databinding.HistoryElementBinding
import com.squareup.picasso.Picasso

class Adapter(private val context: Context,private val historyInfoList: Users):RecyclerView.Adapter<Adapter.MyViewHolder>() {

    class MyViewHolder(binding: HistoryElementBinding): RecyclerView.ViewHolder(binding.root){

        val image: ImageView = binding.historyIcon
        val txt_firm: TextView = binding.historyFirm
        val txt_date: TextView = binding.historyDate
        val txt_price: TextView = binding.historyPrice
        val txt_currency: TextView = binding.historyCurrency
        val txt_price_in_currency: TextView = binding.historyPriceInCurrency

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.history_element, parent, false)
        return MyViewHolder(HistoryElementBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return historyInfoList.users[0].transaction_history.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Picasso.get().load(historyInfoList.users[0].transaction_history[position].icon_url).into(holder.image)
        holder.txt_firm.text = historyInfoList.users[0].transaction_history[position].title
        holder.txt_date.text = historyInfoList.users[0].transaction_history[position].date
        holder.txt_price.text = historyInfoList.users[0].transaction_history[position].amount
    }

}