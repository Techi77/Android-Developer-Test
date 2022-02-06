package com.example.androiddevelopertest.FragmentMain.Retrofit


object Common {
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL)
            .create(RetrofitServices::class.java)
}