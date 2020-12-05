package com.eneserdogan.bookcommentapp.LoginUI.ui.LoginForgotPassword

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginForgotPasswordViewModel:ViewModel() {


    fun sendEmail(mail:String,context: Context){

        FirebaseAuth.getInstance().sendPasswordResetEmail(mail).addOnCompleteListener{task ->
            if (task.isSuccessful){
                Toast.makeText(context,"Şifre Sıfırlama Linki Mailinize Gönderildi",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"Hata Oluştu",Toast.LENGTH_LONG).show()
            }
        }

    }

}