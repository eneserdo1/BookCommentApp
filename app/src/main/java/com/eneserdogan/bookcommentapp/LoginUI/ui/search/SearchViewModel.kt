package com.eneserdogan.bookcommentapp.LoginUI.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneserdogan.bookcommentapp.LoginUI.adapter.SearchRecyclerAdapter
import com.eneserdogan.bookcommentapp.LoginUI.model.Book
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class SearchViewModel : ViewModel() {
    val loadingData =MutableLiveData<Boolean>()
    val errorMessage =MutableLiveData<Boolean>()
    private val mutableBookList: MutableLiveData<List<Book>> = MutableLiveData()
    private lateinit var databaseReference: DatabaseReference

    fun getAllData() :LiveData<List<Book>>{
        val books: MutableList<Book> = ArrayList()
        loadingData.value=true
        databaseReference = FirebaseDatabase.getInstance().reference

        val rootref = databaseReference.child("Books")
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
                /* suggestedBookList.value=books
                 newBookList.value=books
                 println("homeViewmodel book $suggestedBookList")*/
                loadingData.value=false
                errorMessage.value=false
            }
        }
        rootref.addValueEventListener(valueEventListener)
        return mutableBookList
    }

    fun filterList(term: String, adapter: SearchRecyclerAdapter) {
        if (term != "") {
            println("term  $term")
            val list = adapter.originalList.filter {
                it.name!!.contains(term, true) || it.author!!.contains(term, true) || it.category!!.contains(term,true)
            }
            adapter.filterList = list
            adapter.notifyDataSetChanged()
            Log.d("filterList : ", list.toString())

        } else {
            adapter.filterList = adapter.originalList
            adapter.notifyDataSetChanged()
        }

    }
}