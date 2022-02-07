package com.example.androiddevelopertest.Retrofit.Currency

import retrofit2.Call
import retrofit2.http.GET


interface RetrofitServices {
    @GET("daily_json.js")
    fun getCardsData(): Call<Date>
}