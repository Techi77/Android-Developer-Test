package com.example.androiddevelopertest.HistoryRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevelopertest.PreferenceHelper.cardUserNumber
import com.example.androiddevelopertest.R
import com.example.androiddevelopertest.Retrofit.Currency.Date
import com.example.androiddevelopertest.Retrofit.Users
import com.example.androiddevelopertest.databinding.HistoryElementBinding
import com.squareup.picasso.Picasso

class Adapter(private val historyInfoList: Users, private val currencyInfoList: Date, cardUserNum: Int, currency: String):RecyclerView.Adapter<Adapter.MyViewHolder>() {
    val cardUserNumber = cardUserNum
    val currency = currency

    class MyViewHolder(binding: HistoryElementBinding): RecyclerView.ViewHolder(binding.root){

        val image: ImageView = binding.historyIcon
        val txt_firm: TextView = binding.historyFirm
        val txt_date: TextView = binding.historyDate
        val txt_price: TextView = binding.historyPrice
        val txt_currency: TextView = binding.historyCurrency
        val txt_price_in_currency: TextView = binding.historyPriceInCurrency

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(HistoryElementBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return historyInfoList.users[0].transaction_history.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Picasso.get().load(historyInfoList.users[cardUserNumber].transaction_history[position].icon_url).into(holder.image)
        holder.txt_firm.text = historyInfoList.users[cardUserNumber].transaction_history[position].title
        holder.txt_date.text = historyInfoList.users[cardUserNumber].transaction_history[position].date
        holder.txt_price.text = "$ " + historyInfoList.users[cardUserNumber].transaction_history[position].amount?.times(-1)
        holder.txt_currency.text = when(currency){
            "GBP" -> "- £"
            "EUR" -> "- €"
            "RUB" -> "- ₽"
            else -> "- £"
        }
        val balanceInUSD: Double? = historyInfoList.users[cardUserNumber].transaction_history[position].amount
        val USDCource: Double? = currencyInfoList.Valute.USD.Value
        val balanceInRUB = balanceInUSD?.times(USDCource!!)?.times(-1)
        val balanceInGBP = balanceInRUB?.div(currencyInfoList.Valute.GBP.Value!!)
        val balanceInEUR = balanceInRUB?.div(currencyInfoList.Valute.EUR.Value!!)
        holder.txt_price_in_currency.text = when(currency){
            "GBP" -> String.format("%.2f", balanceInGBP)
            "EUR" -> String.format("%.2f", balanceInEUR)
            "RUB" -> String.format("%.2f", balanceInRUB)
            else -> String.format("%.2f", balanceInGBP)
        }

    }

}