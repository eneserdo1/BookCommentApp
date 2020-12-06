package com.eneserdogan.bookcommentapp.LoginUI.ui.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.fragment_profil.*

class ProfilFragment : Fragment() {

    private lateinit var profilViewModel: ProfilViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        profilViewModel = ViewModelProviders.of(this).get(ProfilViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profil, container, false)




        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonsListener()

    }

    private fun buttonsListener() {
        profilFragment_btn_setting.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.profileFragment_to_normalUser_setting)
        }

        profilFragment_floatBtn_add.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.profilFragment_to_addBookFragment)
        }
    }

}