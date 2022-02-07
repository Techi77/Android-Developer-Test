package com.example.androiddevelopertest.Retrofit.Currency

import com.example.androiddevelopertest.Retrofit.CURRENCY_URL


object CommonCurrency {
    val retrofitServiceCurrency: RetrofitServicesCurrency
        get() = RetrofitClientCurrency.getCurrency(CURRENCY_URL)
            .create(RetrofitServicesCurrency::class.java)
}