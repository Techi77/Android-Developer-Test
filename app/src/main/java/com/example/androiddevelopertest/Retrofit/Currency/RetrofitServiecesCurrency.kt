package com.example.androiddevelopertest.Retrofit.Currency

import retrofit2.Call
import retrofit2.http.GET


interface RetrofitServicesCurrency {
    @GET("daily_json.js")
    fun getCurrencyData(): Call<Date>
}