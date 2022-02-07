package com.example.androiddevelopertest.Retrofit.Currency

import com.example.androiddevelopertest.Retrofit.CURRENCY_URL
import com.example.androiddevelopertest.Retrofit.RetrofitClient


object CommonCurrency {
    val retrofitServiceCurrency: RetrofitServicesCurrency
        get() = RetrofitClient.getClient(CURRENCY_URL)
            .create(RetrofitServicesCurrency::class.java)
}