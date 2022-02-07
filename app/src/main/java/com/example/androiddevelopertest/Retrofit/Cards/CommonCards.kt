package com.example.androiddevelopertest.Retrofit.Cards

import com.example.androiddevelopertest.Retrofit.CARDS_URL
import com.example.androiddevelopertest.Retrofit.RetrofitServicesCards

object CommonCards {
    val retrofitServiceCards: RetrofitServicesCards
        get() = RetrofitClientCards.getClient(CARDS_URL)
            .create(RetrofitServicesCards::class.java)
}