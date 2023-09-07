package com.example.team7contactapp.data

import android.os.Parcelable
import com.example.team7contactapp.R
import kotlinx.parcelize.Parcelize

object User {
    val dataList = mutableListOf<MyItem>(
        MyItem(R.drawable.donghyun, "윤동현", "", R.drawable.img_bookmarkon,"", "", ""),
        MyItem(R.drawable.jinjoo, "황진주", "", R.drawable.img_bookmarkoff, "", "", ""),
        MyItem(R.drawable.profiles, "서수현", "", R.drawable.img_bookmarkon, "", "", ""),
        MyItem(R.drawable.profiles, "이종민", "010-3342-5943", R.drawable.img_bookmarkon, "인천시 부평구", "12월 32일", "프로젝트 화이팅!!!!"),
        MyItem(R.drawable.profiles, "이성진", "", R.drawable.img_bookmarkoff, "", "", ""),
        MyItem(R.drawable.profiles, "최다현", "", R.drawable.img_bookmarkon, "", "", ""),
        MyItem(R.drawable.profiles, "정용현", "", R.drawable.img_bookmarkoff, "", "", ""),
        MyItem(R.drawable.profiles, "김에디", "", R.drawable.img_bookmarkon, "", "", ""),
        MyItem(R.drawable.profiles, "박에디", "", R.drawable.img_bookmarkon, "", "", ""),
        MyItem(R.drawable.profiles, "심에디", "", R.drawable.img_bookmarkon, "", "", ""),
        MyItem(R.drawable.profiles, "임에디", "", R.drawable.img_bookmarkon, "", "", ""),
        MyItem(R.drawable.profiles, "정에디", "", R.drawable.img_bookmarkon, "", "", ""),
        MyItem(R.drawable.profiles, "정에디", "", R.drawable.img_bookmarkon, "", "", ""),
        MyItem(R.drawable.profiles, "정에디", "", R.drawable.img_bookmarkon, "", "", "")
    )
}


@Parcelize
data class MyItem(
    val icon: Int,
    val name: String,
    val contact: String,
    val favorite: Int,
    val address: String,
    val birth: String,
    val memo: String
) : Parcelable
