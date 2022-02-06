package com.example.androiddevelopertest.FragmentMain.Retrofit

import retrofit2.Call
import retrofit2.http.GET


interface RetrofitServices {
    @GET("users.json")
    fun getCardsData(): Call<Users>
}