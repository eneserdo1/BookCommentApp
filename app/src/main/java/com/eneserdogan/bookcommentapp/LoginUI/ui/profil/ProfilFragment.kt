package com.eneserdogan.bookcommentapp.LoginUI.ui.profil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.eneserdogan.bookcommentapp.LoginUI.adapter.UserBookAdapter
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.fragment_normal_user_setting.*
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.android.synthetic.main.fragment_profil.profilFragment_imageview

class ProfilFragment : Fragment() {

    private lateinit var profilViewModel: ProfilViewModel
    private var userBookRecyclerAdapter=UserBookAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("profil fragment oncreate")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        profilViewModel = ViewModelProviders.of(this).get(ProfilViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profil, container, false)
        println("profil fragment oncreateview")

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("profil fragment onviewcreated")

        profilViewModel.getUser()
        profilViewModel.getUserBooks()
        initializeRecycler()
        buttonsListener()
        observeData()

    }

    private fun initializeRecycler() {
        profilFragment_userbook_recyclerview.layoutManager=LinearLayoutManager(context)
        profilFragment_userbook_recyclerview.adapter=userBookRecyclerAdapter
    }

    private fun observeData() {
        profilViewModel.loadingData.observe(viewLifecycleOwner, Observer {
            if (it){
                profilFragment_progressbar.visibility=View.VISIBLE
                profilFragment_errorMessage.visibility=View.GONE
                profilFragment_userbook_recyclerview.visibility=View.GONE

            }else{
                profilFragment_progressbar.visibility=View.GONE
            }
        })

        profilViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            if (it){
                profilFragment_userbook_recyclerview.visibility=View.GONE
                profilFragment_errorMessage.visibility=View.VISIBLE
            }else{
                profilFragment_errorMessage.visibility=View.GONE

            }
        })
        profilViewModel.userBook.observe(viewLifecycleOwner, Observer {books ->
            books?.let {
                userBookRecyclerAdapter.updateBookList(books)
                profilFragment_userbook_recyclerview.visibility=View.VISIBLE
                println("observer book $it")
            }
        })

        profilViewModel.userData.observe(viewLifecycleOwner, Observer {user ->
            user?.let {
                profilFragment_name.text=user.name
                profilFragment_username.text=user.mail
                Glide.with(requireActivity()).load(user.imgUrl).into(profilFragment_imageview)
                println("observer user $it")
            }
        })

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