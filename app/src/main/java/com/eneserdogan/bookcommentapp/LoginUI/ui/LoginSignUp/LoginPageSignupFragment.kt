package com.eneserdogan.bookcommentapp.LoginUI.ui.LoginSignUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.eneserdogan.bookcommentapp.LoginUI.model.User
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.loginpage_signup_fragment.*

class LoginPageSignupFragment : Fragment() {
    private lateinit var viewModel: LoginPageSignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpButtonsListener()

    }

    private fun signUpButtonsListener() {
        signup_btn.setOnClickListener {
            if (isEmpty(signup_edittext_email) || isEmpty(signup_edittext_name) ||
                isEmpty(signup_edittext_surname) || isEmpty(signup_edittext_email)
                || isEmpty(signup_edittext_passwordtwo)
            ) {
                signup_progressbar.visibility = View.VISIBLE
                Toast.makeText(context, "Lütfen Gerekli Alanları Doldurunuz", Toast.LENGTH_LONG)
                    .show()

            } else if (signup_edittext_password.text.toString() == signup_edittext_passwordtwo.text.toString()) {

                val user = User(
                    signup_edittext_name.text.toString() + signup_edittext_surname.text.toString(),
                    signup_edittext_email.text.toString(),
                    signup_edittext_password.text.toString(),
                    "degersiz",
                    "boş",
                    false,
                    "1997 doğumlu Bilgisayar Mühendisi"
                )
                viewModel.userSignUp(requireActivity(), user, requireContext())

            } else {
                Toast.makeText(
                    context,
                    "Şifreleriniz Eşleşmiyor, Lütfen Kontrol Ediniz",
                    Toast.LENGTH_LONG
                ).show()
            }

        }    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.loginpage_signup_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(LoginPageSignupViewModel::class.java)
        return root
    }


    fun isEmpty(editText: EditText): Boolean {
        return editText.text.toString() == ""
    }
}