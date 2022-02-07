package com.example.androiddevelopertest.Retrofit.Currency

import com.example.androiddevelopertest.Retrofit.CURRENCY_URL
import com.example.androiddevelopertest.Retrofit.RetrofitClient
import com.example.androiddevelopertest.Retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.http.GET


object Common {
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(CURRENCY_URL)
            .create(RetrofitServices::class.java)
}