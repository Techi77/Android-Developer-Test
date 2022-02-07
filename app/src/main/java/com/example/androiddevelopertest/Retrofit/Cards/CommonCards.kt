package com.example.androiddevelopertest.Retrofit.Cards

import com.example.androiddevelopertest.Retrofit.CARDS_URL
import com.example.androiddevelopertest.Retrofit.RetrofitClient
import com.example.androiddevelopertest.Retrofit.RetrofitServicesCards

object CommonCards {
    val retrofitServiceCards: RetrofitServicesCards
        get() = RetrofitClient.getClient(CARDS_URL)
            .create(RetrofitServicesCards::class.java)
}