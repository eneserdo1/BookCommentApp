package com.eneserdogan.bookcommentapp.LoginUI.ui.LoginSignin

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.eneserdogan.bookcommentapp.LoginUI.ui.HomePageActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginSigninViewModel : ViewModel() {
    var auth = FirebaseAuth.getInstance()


    fun signIn(fragmentActivity: FragmentActivity, mail: String, password: String) {

        auth.signInWithEmailAndPassword(mail, password)
            .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                override fun onComplete(p0: Task<AuthResult>) {
                    if (p0.isSuccessful) {
                        val intent = Intent(
                            fragmentActivity.applicationContext,
                            HomePageActivity::class.java
                        )
                        startActivity(fragmentActivity.applicationContext, intent, null)
                    } else {
                        Toast.makeText(
                            fragmentActivity.applicationContext,
                            "Lütfen Mail veya Şifre Yanlış",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            })
    }
}