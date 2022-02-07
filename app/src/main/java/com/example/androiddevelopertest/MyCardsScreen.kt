package com.example.androiddevelopertest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddevelopertest.databinding.ActivityMainBinding
import com.example.androiddevelopertest.databinding.MyCardsElementBinding

class MyCardsScreen: AppCompatActivity() {
    private lateinit var binding: MyCardsElementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MyCardsElementBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}