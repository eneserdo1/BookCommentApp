package com.eneserdogan.bookcommentapp.LoginUI.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Book(
    var name:String?="",
    var author:String?="",
    var year:Int?=0,
    var description:String?="",
    var category:String?="",
    var authorId:String?="",
    var photoUri:String?="",
    var bookId:String?=""

)