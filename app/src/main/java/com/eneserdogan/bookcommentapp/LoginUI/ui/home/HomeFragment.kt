package com.eneserdogan.bookcommentapp.LoginUI.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneserdogan.bookcommentapp.LoginUI.adapter.NewBooksRecyclerviewAdapter
import com.eneserdogan.bookcommentapp.LoginUI.adapter.SuggestedBookRecyclerAdapter
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val suggestedRecyclerviewAdapter=SuggestedBookRecyclerAdapter()
    private val newBooksRecyclerviewAdapter=NewBooksRecyclerviewAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val newBookManager=LinearLayoutManager(context)
//        newBookManager.orientation=LinearLayoutManager.HORIZONTAL

        initilializeRecyclerViews()

    }

    private fun initilializeRecyclerViews() {
        val layoutManager=LinearLayoutManager(context)
        layoutManager.orientation=LinearLayoutManager.HORIZONTAL

        val newBooksManager=GridLayoutManager(context,2)
        newBooksManager.orientation=GridLayoutManager.HORIZONTAL

        home_suggested_recyclerview.layoutManager=layoutManager
        home_suggested_recyclerview.adapter=suggestedRecyclerviewAdapter

        home_newbooks_recyclerview.layoutManager=newBooksManager
        home_newbooks_recyclerview.adapter=newBooksRecyclerviewAdapter    }
}