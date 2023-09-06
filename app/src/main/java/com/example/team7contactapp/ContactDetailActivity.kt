package com.example.team7contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.widget.ImageView
import android.widget.TextView
import com.example.team7contactapp.databinding.ActivityContactDetailBinding
import com.example.team7contactapp.home.MyItem

class ContactDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val position = intent.getIntExtra("position", -1)
        val aIcon = intent.getIntExtra("aIcon", R.id.item_profile)
        val aName = intent.getStringExtra("aName")
        val aFavorite = intent.getIntExtra("aFavorite", R.id.item_favorite_yellow)

        val ImageView = findViewById<ImageView>(R.id.item_profile)
        ImageView?.setImageResource(aIcon)

        val name = findViewById<TextView>(R.id.item_user_name)
        name?.text = aName

        val favorite = findViewById<ImageView>(R.id.item_favorite_yellow)
        favorite?.setImageResource(aFavorite)
    }
}