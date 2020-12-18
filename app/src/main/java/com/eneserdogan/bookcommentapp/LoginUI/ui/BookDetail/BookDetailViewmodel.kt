package com.eneserdogan.bookcommentapp.LoginUI.ui.BookDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneserdogan.bookcommentapp.LoginUI.model.Book
import com.google.firebase.database.*

class BookDetailViewmodel :ViewModel() {
    private var bookLiveData:MutableLiveData<Book> = MutableLiveData()
    var progressData=MutableLiveData<Boolean>()
    var errorMessage=MutableLiveData<Boolean>()

    private lateinit var databaseReference: DatabaseReference

    fun getData(id:String): LiveData<Book>{
        progressData.value=true
        databaseReference= FirebaseDatabase.getInstance().reference
        val ref=databaseReference.child("Books")

        val valueeventListener= object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                throw p0.toException()
                errorMessage.value=true
                progressData.value=false
            }

            override fun onDataChange(p0: DataSnapshot) {
                val data=p0.child(id)
                val book=data.getValue(Book::class.java)
                bookLiveData.postValue(book)
            }
        }
        progressData.value=false
        errorMessage.value=false

        return bookLiveData

    }

}