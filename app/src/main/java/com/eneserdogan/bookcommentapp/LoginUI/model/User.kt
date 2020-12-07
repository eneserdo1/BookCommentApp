package com.eneserdogan.bookcommentapp.LoginUI.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var name:String?="",
    var mail:String?="",
    var password:String?="",
    var userId:String?="",
    var imgUrl:String?="",
    var premiumState:Boolean?=null,
    var biography:String?=""
){
    @Exclude
    fun toMap():Map<String,Any?>{
        return mapOf(
            "name" to name,
            "mail" to mail,
            "password" to password,
            "userId" to userId,
            "imgUrl" to imgUrl,
            "premiumState" to premiumState,
            "biography" to biography
        )
    }
}