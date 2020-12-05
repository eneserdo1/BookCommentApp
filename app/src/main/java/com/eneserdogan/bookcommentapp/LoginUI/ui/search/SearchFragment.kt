package com.eneserdogan.bookcommentapp.LoginUI.ui.search

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneserdogan.bookcommentapp.LoginUI.adapter.SearchRecyclerAdapter
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private val recycleradapter=SearchRecyclerAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initilializeRecyclerViews()

    }

    private fun initilializeRecyclerViews() {
        searchFragment_recyclerview.layoutManager=LinearLayoutManager(context)
        searchFragment_recyclerview.adapter=recycleradapter

        searchFragment_btn_down.setOnClickListener {
            if (searchFragment_expandablecard.visibility==View.GONE){
                TransitionManager.beginDelayedTransition(searchFragment_cardview,AutoTransition())
                searchFragment_expandablecard.visibility=View.VISIBLE
                searchFragment_btn_down.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
            }else{
                TransitionManager.beginDelayedTransition(searchFragment_cardview,AutoTransition())
                searchFragment_expandablecard.visibility=View.GONE
                searchFragment_btn_down.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
            }
        }
    }


}