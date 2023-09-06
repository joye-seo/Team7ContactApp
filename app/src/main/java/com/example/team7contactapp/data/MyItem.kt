package com.example.team7contactapp.data

import android.os.Parcelable
import com.example.team7contactapp.R
import kotlinx.parcelize.Parcelize

object User {
    val dataList = mutableListOf<MyItem>(
        MyItem(R.drawable.donghyun, "윤동현", false, "", "", ""),
        MyItem(R.drawable.jinjoo, "황진주", false, "", "", ""),
        MyItem(R.drawable.profiles, "서수현", false, "", "", ""),
        MyItem(R.drawable.profiles, "이종민", false, "", "", ""),
        MyItem(R.drawable.profiles, "이성진", false, "", "", ""),
        MyItem(R.drawable.profiles, "최다현", false, "", "", ""),
        MyItem(R.drawable.profiles, "정용현", false, "", "", ""),
        MyItem(R.drawable.profiles, "김에디", false, "", "", ""),
        MyItem(R.drawable.profiles, "박에디", false, "", "", ""),
        MyItem(R.drawable.profiles, "심에디", false, "", "", ""),
        MyItem(R.drawable.profiles, "임에디", false, "", "", ""),
        MyItem(R.drawable.profiles, "정에디", false, "", "", ""),
        MyItem(R.drawable.profiles, "정에디", false, "", "", ""),
        MyItem(R.drawable.profiles, "정에디", false, "", "", ""),
        MyItem(R.drawable.profiles, "정에디", false, "", "", ""),
        MyItem(R.drawable.profiles, "정에디", false, "", "", "")
    )
}


@Parcelize
data class MyItem(
    val icon: Int,
    val name: String,
    val favorite: Boolean,
    val address: String,
    val birth: String,
    val memo: String
) : Parcelable
