package com.eneserdogan.bookcommentapp.LoginUI.ui.profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.fragment_normal_user_setting.*


class NormalUserSettingFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("NormalUserSettingFragment İçerisi")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_normal_user_setting, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonClickListener()

    }


    private fun buttonClickListener() {
        normalUserFragment_edit_button.setOnClickListener {
            normalUserFragment_email_edittext.isEnabled=true
            normalUserFragment_name_edittext.isEnabled=true
            normalUserFragment_surname_edittext.isEnabled=true
            normalUserFragment_save_button.visibility=View.VISIBLE
            normalUserFragment_edit_button.visibility=View.GONE
        }

        normalUserFragment_save_button.setOnClickListener {
            normalUserFragment_email_edittext.isEnabled=false
            normalUserFragment_name_edittext.isEnabled=false
            normalUserFragment_surname_edittext.isEnabled=false
            normalUserFragment_save_button.visibility=View.GONE
            normalUserFragment_edit_button.visibility=View.VISIBLE

        }
    }

}