package com.eneserdogan.bookcommentapp.LoginUI.ui.profil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneserdogan.bookcommentapp.LoginUI.model.Book
import com.eneserdogan.bookcommentapp.LoginUI.model.User
import com.eneserdogan.bookcommentapp.LoginUI.ui.DBHelper.DbHelper
import com.google.firebase.auth.FacebookAuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfilViewModel : ViewModel() {
    val userData = MutableLiveData<User>()
    val userBook = MutableLiveData<List<Book>>()
    val loadingData =MutableLiveData<Boolean>()
    val errorMessage =MutableLiveData<Boolean>()

    private lateinit var databaseReference: DatabaseReference
    lateinit var dbHelper: DbHelper
    val id = FirebaseAuth.getInstance().uid

    fun getUser() {
        loadingData.value=true
        databaseReference = FirebaseDatabase.getInstance().reference
        val rootref = databaseReference.child("users").child(id.toString())
        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                throw p0.toException(); //Don't ignore errors
                loadingData.value=false
                errorMessage.value=true

            }

            override fun onDataChange(p0: DataSnapshot) {
                loadingData.value=false
                errorMessage.value=false
                val user = p0.getValue(User::class.java)
                println("userss $user")
                userData.value = user
            }
        }
        rootref.addListenerForSingleValueEvent(valueEventListener)

    }

    fun getUserBooks() {
        loadingData.value=true
        databaseReference = FirebaseDatabase.getInstance().reference
        val books: MutableList<Book> = ArrayList()
        val rootref = databaseReference.child("Books").child(id.toString())
        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                throw p0.toException(); //Don't ignore errors
                loadingData.value=false
                errorMessage.value=true
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (snapshot in p0.children) {

                    val book = snapshot.getValue(Book::class.java)
                    book?.let { books.add(it) }

                }
                loadingData.value=false
                errorMessage.value=false
                userBook.value = books
            }
        }
        rootref.addValueEventListener(valueEventListener)
        println("bookList $books")

    }
}