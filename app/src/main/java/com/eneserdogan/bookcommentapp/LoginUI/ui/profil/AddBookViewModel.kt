package com.eneserdogan.bookcommentapp.LoginUI.ui.profil

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eneserdogan.bookcommentapp.LoginUI.model.Book
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.util.*

class AddBookViewModel : ViewModel() {
    val loadingData = MutableLiveData<Boolean>()
    internal var storage: FirebaseStorage? = null
    internal var storageReference: StorageReference? = null

    fun addBook(book: Book, filePath: Uri, context: Context) {

        lateinit var database: DatabaseReference
        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference
        database = FirebaseDatabase.getInstance().reference
        val usersId = database.push().key
        val randomId = UUID.randomUUID().toString()
        loadingData.value=false

        val imageRef = storageReference!!.child("BookPhotos").child(book.authorId.toString())
            .child("images/" + randomId)
        imageRef.putFile(filePath!!).addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener {

                loadingData.value = true
                var downloadUri = it
                book.photoUri = downloadUri.toString()
                book.bookId=usersId
                saveBookRealtime(book, context)

                println("downUri " + downloadUri.toString())

            }
        }
    }

    private fun saveBookRealtime(book: Book, context: Context) {
        lateinit var database: DatabaseReference
        database = FirebaseDatabase.getInstance().reference

        database.child("Books").child(book.bookId.toString()).setValue(book)
            .addOnCompleteListener {
                loadingData.value = false
                Toast.makeText(context, "Yükleme Tamamlandı", Toast.LENGTH_LONG).show()
            }
    }
}