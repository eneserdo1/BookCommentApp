package com.eneserdogan.bookcommentapp.LoginUI.ui.LoginSignin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.eneserdogan.bookcommentapp.LoginUI.ui.HomePageActivity
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.loginpage_signin_fragment.*

class LoginPageSigninFragment : Fragment() {


    private lateinit var viewModel: LoginSigninViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.loginpage_signin_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(LoginSigninViewModel::class.java)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInButtonsListener()
    }

    private fun signInButtonsListener() {

        // Giriş butonu dinleniyor //
        signin_btn.setOnClickListener {
            signin_progressbar.visibility = View.VISIBLE
            if (isEmpty(signin_edittext_email) || isEmpty(signin_edittext_password)) {
                Toast.makeText(context, "Lütfen Gerekli Alanları Doldurunuz", Toast.LENGTH_LONG)
                    .show()
                signin_progressbar.visibility = View.GONE
            } else {
                viewModel.signIn(
                    requireContext(),
                    signin_edittext_email.text.toString(),
                    signin_edittext_password.text.toString()
                )
            }
        }


        // Şifremi unuttum dinleniyor //
        signin_forgot_text.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_loginPageSigninFragment_to_loginPageForgotPassword)
        }
    }


    fun isEmpty(editText: EditText): Boolean {
        return editText.text.toString() == ""
    }


}