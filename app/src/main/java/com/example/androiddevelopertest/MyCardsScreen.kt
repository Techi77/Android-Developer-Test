package com.example.androiddevelopertest

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddevelopertest.Retrofit.Cards.Common
import com.example.androiddevelopertest.Retrofit.RetrofitServices
import com.example.androiddevelopertest.Retrofit.Users
import com.example.androiddevelopertest.CardsRecycler.Adapter
import com.example.androiddevelopertest.databinding.MyCardsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyCardsScreen: AppCompatActivity() {
    private lateinit var binding: MyCardsBinding

    private val PREFS_NAME = "Saved_params"

    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MyCardsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val prefs = PreferenceHelper.customPreference(this, PREFS_NAME)

        mService = Common.retrofitService
        layoutManager = LinearLayoutManager(this)

        //History recycler
        binding.recyclerViewHistory.setHasFixedSize(true)
        binding.recyclerViewHistory.layoutManager = layoutManager

        getHistoryList(prefs)

        binding.btBack.setOnClickListener {
            val intent = Intent(this@MyCardsScreen, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun getHistoryList(prefs: SharedPreferences) {
        mService.getCardsData().enqueue(object : Callback<Users> {
            override fun onFailure(call: Call<Users>, t: Throwable) {

            }

            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                val cardBase = response.body() as Users
                adapter = Adapter(cardBase, prefs)
                binding.recyclerViewHistory.adapter = adapter
            }
        })
    }
}