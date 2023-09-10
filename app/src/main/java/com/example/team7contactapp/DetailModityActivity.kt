package com.example.team7contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.team7contactapp.data.MyItem
import com.example.team7contactapp.databinding.ActivityDetailModityBinding

class DetailModityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailModityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailModityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}