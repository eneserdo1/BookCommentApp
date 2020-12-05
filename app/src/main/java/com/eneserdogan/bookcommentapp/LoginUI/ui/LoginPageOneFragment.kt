package com.eneserdogan.bookcommentapp.LoginUI.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.loginpage_one_fragment.*

class LoginPageOneFragment : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.loginpage_one_fragment,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_btn_start.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginPageOneFragment_to_loginPageTwoFragment)
        }
    }
}