package com.example.team7contactapp.data

import android.os.Parcelable
import com.example.team7contactapp.R
import kotlinx.parcelize.Parcelize

object User {
    val dataList = mutableListOf<MyItem>(
        MyItem(

            R.drawable.donghyun,
            "윤동현",
            "010-4883-2929",
            "donghyun@gmail.com",
            false,
            "이천시 창전동",
            "2020/7/5",
            "잘부탁드립니다 INTJ입니다"
        ),
        MyItem(
            R.drawable.jinjoo,
            "황진주",
            "010-9091-4358",
            "jinjoo@gmail.com",
            false,
            "서울시 강남구",
            "2020/9/8",
            "안녕하세요 파워 E입니다!!"
        ),
        MyItem(
            R.drawable.img_profile_soohyun,
            "서수현",
            "010-4565-2156",
            "suhyun@gmail.com",
            false,
            "서울시 강북구",
            "2020/11/25",
            "잘지내보아용!! 플젝화이팅!!"
        ),
        MyItem(
            R.drawable.ljm,
            "이종민",
            "010-9117-1065",
            "jongmin@gmail.com",
            false,
            "인천시 부평구",
            "2020/12/32",
            "프로젝트 화이팅!!!!"
        ),
        MyItem(
            R.drawable.sungjin,
            "이성진",
            "010-4659-8951",
            "sungjin@gmail.com",
            false,
            "서울시 은평구",
            "2020/8/9",
            "안녕하세요 이성진입니다!!"
        ),
        MyItem(
            R.drawable.kwonsangwoo,
            "권상우",
            "010-5678-9012",
            "star@naver.com",
            false,
            "강남구 압구정동",
            "2020/3/3",
            "옥땅으로 따라와"
        ),
        MyItem(
            R.drawable.jpark,
            "박재범",
            "010-6789-0123",
            "",
            false,
            "강북구 한남동",
            "2020/4/1",
            "봉준호 손흥민 bts jpark"
        ),
        MyItem(
            R.drawable.jungwoosung,
            "정우성",
            "010-7890-1234",
            "star@gmail.com",
            false,
            "목포시 하당동",
            "2020/5/4",
            "잘생긴게 짱이에요"
        ),
        MyItem(
            R.drawable.jangwonyoung,
            "장원영",
            "010-8901-2345",
            "ive@gmail.com",
            false,
            "강남구 압구정동",
            "2020/6/6",
            "숨참고 love dive"
        ),
        MyItem(
            R.drawable.kangwonki,
            "강원기",
            "010-9012-3456",
            "cube@nexon.com",
            false,
            "판교 넥슨동",
            "2020/7/7",
            "용사님들 반갑습니다!"
        ),
        MyItem(
            R.drawable.kimchangsub,
            "김창섭",
            "010-0123-4567",
            "balance@nexon.com",
            false,
            "판교 넥슨동",
            "2020/8/8",
            "신규 디렉터 김창섭입니다"
        ),
        MyItem(
            R.drawable.jyp,
            "박진영",
            "010-2345-5678",
            "music@gmail.com",
            false,
            "강남구 jyp동",
            "2020/9/9",
            "난 여자가 있는데~"
        )
    )
}

@Parcelize
data class MyItem(
    val icon: Int?,
    val name: String,
    val contact: String,
    val emaill: String,
    var favorite: Boolean,
    val address: String?,
    val birth: String?,
    val memo: String?,
) : Parcelable {
}