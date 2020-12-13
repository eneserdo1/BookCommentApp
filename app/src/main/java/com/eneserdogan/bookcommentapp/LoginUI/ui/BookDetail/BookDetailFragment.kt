package com.eneserdogan.bookcommentapp.LoginUI.ui.BookDetail

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.fragment_book_detail.*


class BookDetailFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expandableButtonListener()

    }

    private fun expandableButtonListener() {
        bookDetailFragment_btnExpandableDown.setOnClickListener {
            if(bookDetailFragment_autohordetailexpandable_layout.visibility==View.GONE){
                TransitionManager.beginDelayedTransition(bookDetailFragment_authorcardview, AutoTransition())
                bookDetailFragment_autohordetailexpandable_layout.visibility=View.VISIBLE
                bookDetailFragment_btnExpandableDown.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
            }else{
                TransitionManager.beginDelayedTransition(bookDetailFragment_authorcardview,AutoTransition())
                bookDetailFragment_autohordetailexpandable_layout.visibility=View.GONE
                bookDetailFragment_btnExpandableDown.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24)

            }
        }
    }


}