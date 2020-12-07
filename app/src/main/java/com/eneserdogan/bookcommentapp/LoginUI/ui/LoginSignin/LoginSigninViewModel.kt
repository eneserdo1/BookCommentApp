package com.eneserdogan.bookcommentapp.LoginUI.ui.LoginSignin

import android.app.Application
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.eneserdogan.bookcommentapp.LoginUI.ui.BaseViewModel
import com.eneserdogan.bookcommentapp.LoginUI.ui.HomePageActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginSigninViewModel(application: Application) : BaseViewModel(application) {
    var auth = FirebaseAuth.getInstance()


    fun signIn(context: Context, mail: String, password: String) {

        auth.signInWithEmailAndPassword(mail, password)
            .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                override fun onComplete(p0: Task<AuthResult>) {
                    if (p0.isSuccessful) {
                        val intent = Intent(
                            context,
                            HomePageActivity::class.java
                        )
                        startActivity(context, intent, null)
                    } else {
                        Toast.makeText(
                            context,
                            "Lütfen Mail veya Şifre Yanlış",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            })
    }
}