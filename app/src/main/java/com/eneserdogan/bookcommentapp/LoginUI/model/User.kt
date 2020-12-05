package com.eneserdogan.bookcommentapp.LoginUI.model

data class User(
    val name:String,
    val mail:String,
    val password:String,
    val userId:String,
    val imgUrl:String,
    val premiumState:Boolean,
    val biography:String
)