package com.example.androiddevelopertest.FragmentMain.Retrofit

import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("marvel")
    fun getCardsData(): Call<MutableList<CardsInfo>>
}