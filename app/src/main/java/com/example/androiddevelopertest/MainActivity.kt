package com.example.androiddevelopertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androiddevelopertest.FragmentMain.Retrofit.Adapter
import com.example.androiddevelopertest.FragmentMain.Retrofit.Common
import com.example.androiddevelopertest.FragmentMain.Retrofit.RetrofitServices
import com.example.androiddevelopertest.FragmentMain.Retrofit.CardsInfo
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

        getAllMovieList()
    }

    private fun getAllMovieList() {
        //dialog.show()
        mService.getCardsData().enqueue(object : Callback<MutableList<CardsInfo>> {
            override fun onFailure(call: Call<MutableList<CardsInfo>>, t: Throwable) {

            }

            override fun onResponse(call: Call<MutableList<CardsInfo>>, response: Response<MutableList<CardsInfo>>) {
                adapter = Adapter(baseContext, response.body() as MutableList<CardsInfo>)
                adapter.notifyDataSetChanged()
                binding.recyclerView.adapter = adapter
            }
        })
    }
}
