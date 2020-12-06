package com.eneserdogan.bookcommentapp.LoginUI.ui.LoginSignUp

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.View

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.eneserdogan.bookcommentapp.LoginUI.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.loginpage_signup_fragment.*
import java.util.*

class LoginPageSignupViewModel : ViewModel() {

    var auth = FirebaseAuth.getInstance()


    internal var storage: FirebaseStorage? = null
    internal var storageReference: StorageReference? = null

    fun userSignUp(activity: Activity, user: User, context: Context, filePath: Uri?) {

        auth.createUserWithEmailAndPassword(user.mail, user.password)
            .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                override fun onComplete(p0: Task<AuthResult>) {
                    if (p0.isSuccessful) {
                        val newUser = auth.currentUser
                        Toast.makeText(
                            context,
                            "Kayıt Başarılı, Giriş Yapınız",
                            Toast.LENGTH_LONG
                        ).show()

                        val id = FirebaseAuth.getInstance().uid
                        user.userId = id!!
                        println("idd " + user.userId)
                        saveUserData(user, filePath, context)

                    } else {
                        Toast.makeText(context, "Kayıt Başarısız", Toast.LENGTH_LONG).show()
                    }
                }

            })
    }

    private fun saveUserData(user: User, filePath: Uri?, context: Context) {

        lateinit var database: DatabaseReference
        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference

        database = FirebaseDatabase.getInstance().reference
        val usersId = database.push().key
        println("userskeyyy "+usersId)

        if (filePath != null) {
            val imageRef = storageReference!!.child("ProfilPhotos").child(user.userId).child("images/" + UUID.randomUUID().toString())
            imageRef.putFile(filePath!!).addOnSuccessListener {
                println("Foto Yükleme Başarılı")
            }

            user.imgUrl = filePath.toString()!!
            usersId?.let {
                database.child("users").child(user.userId).setValue(user).addOnCanceledListener {
                    Toast.makeText(context, "Yükleme Tamamlandı", Toast.LENGTH_LONG).show()
                }
            }

        } else {
            Toast.makeText(context, "Yükleme Başarısız", Toast.LENGTH_LONG).show()
        }

    }


}