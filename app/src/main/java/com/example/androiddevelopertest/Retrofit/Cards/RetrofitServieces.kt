package com.example.androiddevelopertest.Retrofit

import retrofit2.Call
import retrofit2.http.GET


interface RetrofitServices {
    @GET("users.json")
    fun getCardsData(): Call<Users>
}