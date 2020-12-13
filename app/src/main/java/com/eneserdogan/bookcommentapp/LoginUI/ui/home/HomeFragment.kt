package com.eneserdogan.bookcommentapp.LoginUI.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneserdogan.bookcommentapp.LoginUI.adapter.NewBooksRecyclerviewAdapter
import com.eneserdogan.bookcommentapp.LoginUI.adapter.SuggestedBookRecyclerAdapter
import com.eneserdogan.bookcommentapp.LoginUI.model.Book
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var suggestedRecyclerviewAdapter:SuggestedBookRecyclerAdapter
    private lateinit var newBooksRecyclerviewAdapter:NewBooksRecyclerviewAdapter
    var dataList:ArrayList<Book> = ArrayList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //homeViewModel.getAllData()
        initilializeRecyclerViews(dataList)
        observeData()

    }

    private fun observeData() {
        homeViewModel.getAllData().observe(viewLifecycleOwner, Observer<List<Book>> {
            if (it != null){
                newBooksRecyclerviewAdapter.setApp(it)
                suggestedRecyclerviewAdapter.setApp(it)
                home_suggested_recyclerview.visibility=View.VISIBLE
                home_newbooks_recyclerview.visibility=View.VISIBLE
            }
        })
       /* homeViewModel.newBookList.observe(viewLifecycleOwner, Observer {bookList->
            bookList?.let {
                newBookAdapter.setApp(it)
                home_newbooks_recyclerview.visibility=View.VISIBLE
                println("home observe $it")
            }
        })

        homeViewModel.suggestedBookList.observe(viewLifecycleOwner, Observer { books->
            books?.let {

                suggestedBookAdapter.setApp(it)
                home_suggested_recyclerview.visibility=View.VISIBLE
            }
        })*/
        homeViewModel.loadingData.observe(viewLifecycleOwner, Observer {
            if (it){
                home_suggested_recyclerview.visibility=View.GONE
                home_newbooks_recyclerview.visibility=View.GONE
                homeFragment_progressbar.visibility=View.VISIBLE
                homeFragment_errorMessage.visibility=View.GONE

            }else{
                homeFragment_progressbar.visibility=View.GONE

            }
        })

        homeViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            if (it){
                homeFragment_errorMessage.visibility=View.VISIBLE
                home_newbooks_recyclerview.visibility=View.GONE
                home_suggested_recyclerview.visibility=View.GONE
            }else{
                homeFragment_errorMessage.visibility=View.GONE
            }
        })
    }

    private fun initilializeRecyclerViews(data:ArrayList<Book>) {
        newBooksRecyclerviewAdapter= NewBooksRecyclerviewAdapter()
        suggestedRecyclerviewAdapter=SuggestedBookRecyclerAdapter()

        val layoutManager=LinearLayoutManager(context)
        layoutManager.orientation=LinearLayoutManager.HORIZONTAL

        val newBooksManager=GridLayoutManager(context,2)
        newBooksManager.orientation=GridLayoutManager.HORIZONTAL

        home_suggested_recyclerview.layoutManager=layoutManager
        suggestedRecyclerviewAdapter.setApp(data)
        home_suggested_recyclerview.adapter=suggestedRecyclerviewAdapter

        home_newbooks_recyclerview.layoutManager=newBooksManager
        newBooksRecyclerviewAdapter.setApp(data)
        home_newbooks_recyclerview.adapter=newBooksRecyclerviewAdapter   }
}