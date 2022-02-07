package com.example.androiddevelopertest.CardsRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevelopertest.FragmentMain.Retrofit.Users
import com.example.androiddevelopertest.R
import com.example.androiddevelopertest.databinding.MyCardsElementBinding

class Adapter(private val historyInfoList: Users):RecyclerView.Adapter<Adapter.MyViewHolder>() {

    class MyViewHolder(binding: MyCardsElementBinding): RecyclerView.ViewHolder(binding.root){

        val cardNumber: TextView = binding.tvCustomCardNumber
        val cardIcon: ImageView = binding.ivCustomCardIcon
        val cardActiveStatus: ImageView = binding.customCardActiveStatus

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MyCardsElementBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return historyInfoList.users.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.cardNumber.text = historyInfoList.users[position].card_number
        holder.cardIcon.setImageResource(
            when(historyInfoList.users[position].type){
                "mastercard" -> R.drawable.ic_mastercard
                "visa" -> R.drawable.ic_visa
                "unionpay" -> R.drawable.ic_unionpay
                else -> R.drawable.ic_custom_card_system
            }
        )
        holder.cardActiveStatus.isVisible = position==0
    }

}
