package com.eneserdogan.bookcommentapp.LoginUI.ui.search

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneserdogan.bookcommentapp.LoginUI.adapter.SearchRecyclerAdapter
import com.eneserdogan.bookcommentapp.LoginUI.model.Book
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var recycleradapter:SearchRecyclerAdapter
    private var dataList:ArrayList<Book> = ArrayList()
    lateinit var searchView: SearchView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView = view.findViewById(R.id.searchFragment_searchview)

        initilializeRecyclerViews(dataList)
        observeError()
        observeList()
        searchViewListener()

    }
    private fun searchViewListener() {
        //Ana sayfa searchview dinleniyor
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    // Girilen string değeri viewmodelda bulunan fonksiyona gönderiliyor
                    searchViewModel.filterList(p0, recycleradapter)

                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0 != null) {
                    // Girilen string değeri viewmodelda bulunan fonksiyona gönderiliyor
                    searchViewModel.filterList(p0, recycleradapter)
                }
                return false
            }
        })
    }

    private fun observeError() {
        searchViewModel.errorMessage.observe(viewLifecycleOwner, Observer { value->
            value?.let {
                if (it){
                    searchFragment_errorMessage.visibility=View.VISIBLE
                    searchFragment_recyclerview.visibility=View.GONE
                }else{
                    searchFragment_errorMessage.visibility=View.GONE
                }
            }
        })

        searchViewModel.loadingData.observe(viewLifecycleOwner, Observer { value->
            value?.let {
                if (it){
                    searchFragment_progressbar.visibility=View.VISIBLE
                    searchFragment_recyclerview.visibility=View.GONE
                    searchFragment_errorMessage.visibility=View.GONE
                }else{
                    searchFragment_progressbar.visibility=View.GONE
                }
            }
        })
    }

    private fun observeList() {
        searchViewModel.getAllData().observe(viewLifecycleOwner, Observer<List<Book>> {book->
            book?.let {
                recycleradapter.setApp(it)
                searchFragment_recyclerview.visibility=View.VISIBLE
            }
        })
    }

    private fun initilializeRecyclerViews(dataList:ArrayList<Book>) {
        searchFragment_recyclerview.layoutManager=LinearLayoutManager(context)
        recycleradapter= SearchRecyclerAdapter()
        recycleradapter.setApp(dataList)
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