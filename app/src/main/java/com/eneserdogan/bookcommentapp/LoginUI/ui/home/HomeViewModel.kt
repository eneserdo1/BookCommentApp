package com.eneserdogan.bookcommentapp.LoginUI.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneserdogan.bookcommentapp.LoginUI.adapter.SearchRecyclerAdapter
import com.eneserdogan.bookcommentapp.LoginUI.model.Book
import com.eneserdogan.bookcommentapp.LoginUI.ui.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class HomeViewModel(application: Application) : BaseViewModel(application) {
    /*val suggestedBookList=MutableLiveData<List<Book>>()
    val newBookList=MutableLiveData<List<Book>>()*/
    val loadingData =MutableLiveData<Boolean>()
    val errorMessage =MutableLiveData<Boolean>()
    private val mutableBookList: MutableLiveData<List<Book>> = MutableLiveData()
    private lateinit var databaseReference: DatabaseReference


    fun getAllData() :LiveData<List<Book>>{
        val books: MutableList<Book> = ArrayList()
        loadingData.value=true
        databaseReference = FirebaseDatabase.getInstance().reference

        val rootref = databaseReference.child("Books").child(FirebaseAuth.getInstance().uid.toString())
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
                mutableBookList.postValue(books)
                loadingData.value=false
                errorMessage.value=false
            }
        }
        rootref.addValueEventListener(valueEventListener)
        return mutableBookList
    }





}