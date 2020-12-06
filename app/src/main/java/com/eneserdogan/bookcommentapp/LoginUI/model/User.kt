package com.eneserdogan.bookcommentapp.LoginUI.model

data class User(
    var name:String,
    var mail:String,
    var password:String,
    var userId:String,
    var imgUrl:String,
    var premiumState:Boolean,
    var biography:String,
    var books:List<Book>?
)