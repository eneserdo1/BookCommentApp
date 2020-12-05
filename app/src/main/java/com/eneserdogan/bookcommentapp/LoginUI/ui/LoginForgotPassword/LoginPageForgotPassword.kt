package com.eneserdogan.bookcommentapp.LoginUI.ui.LoginForgotPassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.fragment_login_page_forgot_password.*


class LoginPageForgotPassword : Fragment() {
    private lateinit var viewmodel:LoginForgotPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root=inflater.inflate(R.layout.fragment_login_page_forgot_password, container, false)
        viewmodel=ViewModelProviders.of(this).get(LoginForgotPasswordViewModel::class.java)

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       forgotPassword_btn.setOnClickListener {
           val mail=forgotPasswprd_edittext_mail.text.toString()
           viewmodel.sendEmail(mail,requireContext())
           forgotPasswprd_edittext_mail.setText("")
       }

    }

}