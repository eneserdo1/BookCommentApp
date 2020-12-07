package com.eneserdogan.bookcommentapp.LoginUI.ui.DBHelper

import android.renderscript.Sampler
import com.eneserdogan.bookcommentapp.LoginUI.model.Book
import com.eneserdogan.bookcommentapp.LoginUI.model.User
import com.google.firebase.database.*
import java.sql.SQLOutput

class DbHelper {
    private lateinit var databaseReference: DatabaseReference
    internal var userData: User? = null
    //internal var userBooks: ArrayList<Book>? = null
    lateinit var userBooks:ArrayList<Book>


    /*fun getUserData(userId: String): User? {
        println("dbHelper getuser data")
        databaseReference = FirebaseDatabase.getInstance().reference
        val rootref = databaseReference.child("users").child(userId.toString())
        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                throw p0.toException(); //Don't ignore errors
            }
            override fun onDataChange(p0: DataSnapshot) {
                val user = p0.getValue(User::class.java)
                println("userss $user")
                userData=user
            }
        }
        rootref.addListenerForSingleValueEvent(valueEventListener)
        return userData
    }


    fun getUserBook(userId: String): MutableList<Book> {
        databaseReference = FirebaseDatabase.getInstance().reference
        val books: MutableList<Book> = ArrayList()
        val rootref = databaseReference.child("Books").child(userId.toString())
        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                throw p0.toException(); //Don't ignore errors
            }
            override fun onDataChange(p0: DataSnapshot) {
                for (snapshot in p0.children) {
                    val book = snapshot.getValue(Book::class.java)
                    println("Books  $book")
                    book?.let { books.add(it) }
                }
            }
        }
        rootref.addValueEventListener(valueEventListener)
        println("bookList $books")
        return books
    }*/
}