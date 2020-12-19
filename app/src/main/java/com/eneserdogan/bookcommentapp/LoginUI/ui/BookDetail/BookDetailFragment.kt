package com.eneserdogan.bookcommentapp.LoginUI.ui.BookDetail

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.eneserdogan.bookcommentapp.LoginUI.model.Book
import com.eneserdogan.bookcommentapp.LoginUI.model.User
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.fragment_book_detail.*


class BookDetailFragment : Fragment() {
    private lateinit var viewmodel: BookDetailViewmodel
    lateinit var bookId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = requireArguments().getString("bookKey")
        this.bookId = id.toString()
        println("this id detail $bookId")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_book_detail, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProviders.of(this).get(BookDetailViewmodel::class.java)
        expandableButtonListener()

        observeLiveData()
    }

    private fun observeLiveData() {
        viewmodel.getData(bookId).observe(viewLifecycleOwner, Observer<Book> { book ->
            book?.let {
                println("detail frag data $it")
                bindElement(it)
            }
        })

        viewmodel.progressData.observe(viewLifecycleOwner, Observer { value ->
            value?.let {
                if (it) {
                    bookDetailFragment_progressbar.visibility = View.VISIBLE
                } else {
                    bookDetailFragment_progressbar.visibility = View.GONE
                }
            }
        })

        viewmodel.userData.observe(viewLifecycleOwner, Observer { data->
            data?.let {
                bindUserElement(it)
            }
        })
    }

    private fun bindUserElement(user: User) {
        bookDetail_authorBiography.text=user.biography
        Glide.with(requireView()).load(user.imgUrl).into(bookDetailFragment_authorImage)
        Glide.with(requireView()).load(user.imgUrl).into(bookDetailFragment_authorDetailImage)

    }

    private fun bindElement(book: Book) {
        bookDetailFragment_authorDetailName.text = book.author
        bookDetailFragment_authorName.text = book.author
        bookDetailFragment_bookName.text = book.name
        Glide.with(requireView()).load(book.photoUri).into(bookDetailFragment_bookImage)
    }

    private fun expandableButtonListener() {
        bookDetailFragment_btnExpandableDown.setOnClickListener {
            if (bookDetailFragment_autohordetailexpandable_layout.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(
                    bookDetailFragment_authorcardview,
                    AutoTransition()
                )
                bookDetailFragment_autohordetailexpandable_layout.visibility = View.VISIBLE
                bookDetailFragment_btnExpandableDown.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
            } else {
                TransitionManager.beginDelayedTransition(
                    bookDetailFragment_authorcardview,
                    AutoTransition()
                )
                bookDetailFragment_autohordetailexpandable_layout.visibility = View.GONE
                bookDetailFragment_btnExpandableDown.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24)

            }
        }
    }


}