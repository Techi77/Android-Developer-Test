package com.example.androiddevelopertest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddevelopertest.databinding.MyCardsBinding

class MyCardsScreen: AppCompatActivity() {
    private lateinit var binding: MyCardsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MyCardsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}