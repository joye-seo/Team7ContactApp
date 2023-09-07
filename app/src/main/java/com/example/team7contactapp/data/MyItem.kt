package com.example.team7contactapp.data

import android.os.Parcelable
import com.example.team7contactapp.R
import kotlinx.parcelize.Parcelize

object User {
    val dataList = mutableListOf<MyItem>(
        MyItem(R.drawable.donghyun, "윤동현", false, "", "", "",1),
        MyItem(R.drawable.jinjoo, "황진주", false, "", "", "",2),
        MyItem(R.drawable.profiles, "서수현", false, "", "", "",3),
        MyItem(R.drawable.profiles, "이종민", false, "", "", "",4),
        MyItem(R.drawable.profiles, "이성진", false, "", "", "",5),
        MyItem(R.drawable.profiles, "최다현", false, "", "", "",6),
        MyItem(R.drawable.profiles, "정용현", false, "", "", "",7),
        MyItem(R.drawable.profiles, "김에디", false, "", "", "",8),
        MyItem(R.drawable.profiles, "박에디", false, "", "", "",9),
        MyItem(R.drawable.profiles, "심에디", false, "", "", "",10),
        MyItem(R.drawable.profiles, "임에디", false, "", "", "",11),
        MyItem(R.drawable.profiles, "정에디", false, "", "", "",12),
        MyItem(R.drawable.profiles, "정에디", false, "", "", "",13),
        MyItem(R.drawable.profiles, "정에디", false, "", "", "",14),
        MyItem(R.drawable.profiles, "정에디", false, "", "", "",15),
        MyItem(R.drawable.profiles, "정에디", false, "", "", "",16)
    )
}


@Parcelize
data class MyItem(
    val icon: Int,
    val name: String,
    val favorite: Boolean,
    val address: String,
    val birth: String,
    val memo: String,
    val num : Int
) : Parcelable
