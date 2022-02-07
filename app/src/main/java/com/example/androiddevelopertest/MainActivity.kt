package com.example.androiddevelopertest

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddevelopertest.HistoryRecycler.Adapter
import com.example.androiddevelopertest.PreferenceHelper.cardUserNumber
import com.example.androiddevelopertest.PreferenceHelper.customCurrency
import com.example.androiddevelopertest.Retrofit.*
import com.example.androiddevelopertest.Retrofit.Cards.CommonCards
import com.example.androiddevelopertest.Retrofit.Currency.CommonCurrency
import com.example.androiddevelopertest.Retrofit.Currency.Date
import com.example.androiddevelopertest.Retrofit.Currency.RetrofitServicesCurrency
import com.example.androiddevelopertest.databinding.ActivityMainBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val PREFS_NAME = "Saved_params"

    private lateinit var binding: ActivityMainBinding

    lateinit var mServiceCards: RetrofitServicesCards
    lateinit var mServiceCurrency: RetrofitServicesCurrency

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val prefs = PreferenceHelper.customPreference(this, PREFS_NAME)

        mServiceCards = CommonCards.retrofitServiceCards
        mServiceCurrency = CommonCurrency.retrofitServiceCurrency
        layoutManager = LinearLayoutManager(this)

        //History recycler
        binding.recyclerViewHistory.setHasFixedSize(true)
        binding.recyclerViewHistory.layoutManager = layoutManager

        getHistoryList(prefs)

        binding.clCardContainer.setOnClickListener {
            val intent = Intent(this@MainActivity, MyCardsScreen::class.java)
            startActivity(intent)
        }
        binding.btChangeCurrencyGbp.setOnClickListener {
            prefs.customCurrency = "GBP"
            getHistoryList(prefs)
        }
        binding.btChangeCurrencyEur.setOnClickListener {
            prefs.customCurrency = "EUR"
            getHistoryList(prefs)
        }
        binding.btChangeCurrencyRub.setOnClickListener {
            prefs.customCurrency = "RUB"
            getHistoryList(prefs)
        }
    }

    private fun getHistoryList(prefs: SharedPreferences) {
        mServiceCards.getCardsData().enqueue(object : Callback<Users> {
            override fun onFailure(call: Call<Users>, t: Throwable) {

            }

            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                val cardBase = response.body() as Users
                Log.e(TAG, "response")
                Log.e(TAG, response.toString())
                getCurrencyList(prefs, cardBase)
            }
        })
    }
    private fun getCurrencyList(prefs: SharedPreferences, cardBase: Users) {
        mServiceCurrency.getCurrencyData().enqueue(object : Callback<Date> {
            override fun onFailure(call: Call<Date>, t: Throwable) {
                Log.e(TAG, "getCurrencyList onFailure ")
                Log.e(TAG, t.toString())
            }

            override fun onResponse(call: Call<Date>, response: Response<Date>) {
                val currencyBase = response.body() as Date
                //val currencyValue = currencyBase.Valute.AUD.Value
                adapter = Adapter(cardBase, currencyBase, prefs.cardUserNumber, prefs.customCurrency.toString())
                binding.recyclerViewHistory.adapter = adapter
                changingActivityMain(prefs,cardBase,currencyBase)
            }
        })
    }
    private fun changingActivityMain (prefs: SharedPreferences, cardBase: Users, currencyBase: Date){
        binding.ivCustomCardIcon.setImageResource(
            when (cardBase.users[prefs.cardUserNumber].type) {
                "mastercard" -> R.drawable.ic_mastercard
                "visa" -> R.drawable.ic_visa
                "unionpay" -> R.drawable.ic_unionpay
                else -> R.drawable.ic_custom_card_system
            }
        )
        binding.cardNumber.text = cardBase.users[prefs.cardUserNumber].card_number
        binding.tvCustomUser.text = cardBase.users[prefs.cardUserNumber].cardholder_name
        binding.tvValidThruNum.text = cardBase.users[prefs.cardUserNumber].valid
        binding.yourBalanceUsd.text = "$" + cardBase.users[prefs.cardUserNumber].balance
        var balanceInUSD: Double? = cardBase.users[prefs.cardUserNumber].balance
        var USDCource: Double? = currencyBase.Valute.USD.Value
        val balanceInRUB = balanceInUSD?.times(USDCource!!)
        val balanceInGBP = balanceInRUB?.div(currencyBase.Valute.GBP.Value!!)
        val balanceInEUR = balanceInRUB?.div(currencyBase.Valute.EUR.Value!!)
        Log.e(TAG, balanceInRUB.toString())
        binding.customBalanceInCurrency.text = when(prefs.customCurrency.toString()){
            "GBP" -> "£ " + String.format("%.2f", balanceInGBP)
            "EUR" -> "€ " + String.format("%.2f", balanceInEUR)
            "RUB" -> "₽ " + String.format("%.2f", balanceInRUB)
            else -> "£ " + String.format("%.2f", balanceInGBP)
        }
    }
}
