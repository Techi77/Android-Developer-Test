package com.example.androiddevelopertest.FragmentMain.Retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevelopertest.R
import com.example.androiddevelopertest.databinding.HistoryElementBinding
import com.squareup.picasso.Picasso

class Adapter(private val context: Context,private val historyInfoList: MutableList<CardsInfo>):RecyclerView.Adapter<Adapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = HistoryElementBinding.bind(itemView)

        val image: ImageView = binding.historyIcon
        val txt_firm: TextView = binding.firm
        val txt_date: TextView = binding.date
        val txt_price: TextView = binding.price

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.my_cards, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = historyInfoList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //Picasso.get().load(historyInfoList[position].transaction_history).into(holder.image)
        holder.txt_firm.text = historyInfoList[position].cardholder_name
        holder.txt_date.text = historyInfoList[position].cardholder_name
        holder.txt_price.text = historyInfoList[position].cardholder_name
    }

}