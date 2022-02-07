package com.example.androiddevelopertest.Retrofit.Cards

import com.example.androiddevelopertest.Retrofit.CARDS_URL
import com.example.androiddevelopertest.Retrofit.RetrofitClient
import com.example.androiddevelopertest.Retrofit.RetrofitServices

object Common {
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(CARDS_URL)
            .create(RetrofitServices::class.java)
}