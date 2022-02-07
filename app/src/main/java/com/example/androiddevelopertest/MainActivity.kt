package com.example.androiddevelopertest

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddevelopertest.Retrofit.*
import com.example.androiddevelopertest.HistoryRecycler.Adapter
import com.example.androiddevelopertest.PreferenceHelper.cardUserNumber
import com.example.androiddevelopertest.Retrofit.Cards.Common
import com.example.androiddevelopertest.databinding.ActivityMainBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val PREFS_NAME = "Saved_params"

    private lateinit var binding: ActivityMainBinding

    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val prefs = PreferenceHelper.customPreference(this, PREFS_NAME)

        mService = Common.retrofitService
        layoutManager = LinearLayoutManager(this)

        //History recycler
        binding.recyclerViewHistory.setHasFixedSize(true)
        binding.recyclerViewHistory.layoutManager = layoutManager

        getHistoryList(prefs)

        binding.clCardContainer.setOnClickListener {
            val intent = Intent(this@MainActivity, MyCardsScreen::class.java)
            startActivity(intent)
        }
    }

    private fun getHistoryList(prefs: SharedPreferences) {
        mService.getCardsData().enqueue(object : Callback<Users> {
            override fun onFailure(call: Call<Users>, t: Throwable) {

            }

            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                val cardBase = response.body() as Users
                adapter = Adapter(response.body() as Users, prefs.cardUserNumber)
                binding.recyclerViewHistory.adapter = adapter
                binding.ivCustomCardIcon.setImageResource(
                    when(cardBase.users[prefs.cardUserNumber].type){
                        "mastercard" -> R.drawable.ic_mastercard
                        "visa" -> R.drawable.ic_visa
                        "unionpay" -> R.drawable.ic_unionpay
                        else -> R.drawable.ic_custom_card_system
                    }
                )
                binding.cardNumber.text = cardBase.users[prefs.cardUserNumber].card_number
                binding.tvCustomUser.text = cardBase.users[prefs.cardUserNumber].cardholder_name
                binding.tvValidThruNum.text = cardBase.users[prefs.cardUserNumber].valid
                binding.yourBalanceUsd.text = cardBase.users[prefs.cardUserNumber].balance
            }
        })
    }
}
