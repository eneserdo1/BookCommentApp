package com.eneserdogan.bookcommentapp.LoginUI.ui.LoginSignUp

import android.app.Activity
import android.content.Context
import android.view.View

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.eneserdogan.bookcommentapp.LoginUI.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.loginpage_signup_fragment.*

class LoginPageSignupViewModel : ViewModel() {

    var auth = FirebaseAuth.getInstance()
    private lateinit var database: DatabaseReference


    fun userSignUp(activity: Activity, user: User, context: Context) {
        auth.createUserWithEmailAndPassword(user.mail, user.password)
            .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                override fun onComplete(p0: Task<AuthResult>) {
                    if (p0.isSuccessful) {
                        val newUser = auth.currentUser
                        Toast.makeText(
                            context,
                            "Kayıt Başarılı, Mail Adresinizi Kontrol Ediniz",
                            Toast.LENGTH_LONG
                        ).show()

                    } else {
                        Toast.makeText(context, "Kayıt Başarısız", Toast.LENGTH_LONG).show()
                    }
                }

            })
    }

}