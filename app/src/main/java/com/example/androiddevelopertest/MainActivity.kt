package com.example.androiddevelopertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddevelopertest.FragmentMain.Retrofit.*
import com.example.androiddevelopertest.HistoryRecycler.Adapter
import com.example.androiddevelopertest.databinding.ActivityMainBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mService = Common.retrofitService

        binding.recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        getHistoryList()
    }

    private fun getHistoryList() {
        mService.getCardsData().enqueue(object : Callback<Users> {
            override fun onFailure(call: Call<Users>, t: Throwable) {

            }

            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                val cardBase = response.body() as Users
                adapter = Adapter(response.body() as Users)
                binding.recyclerView.adapter = adapter
                binding.cardNumber.text = cardBase.users[0].card_number
                binding.tvCustomUser.text = cardBase.users[0].cardholder_name
                binding.tvValidThruNum.text = cardBase.users[0].valid
                binding.yourBalanceUsd.text = cardBase.users[0].balance
            }
        })
    }
}
