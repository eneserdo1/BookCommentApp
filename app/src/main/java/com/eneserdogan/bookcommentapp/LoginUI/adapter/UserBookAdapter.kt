package com.eneserdogan.bookcommentapp.LoginUI.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eneserdogan.bookcommentapp.LoginUI.model.Book
import com.eneserdogan.bookcommentapp.LoginUI.ui.BookDetail.BookDetailFragment
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.userbook_recyclerview_item.view.*

class UserBookAdapter(val list:ArrayList<Book>) :RecyclerView.Adapter<UserBookAdapter.UserBookHolder>(){
    class UserBookHolder(view: View):RecyclerView.ViewHolder(view) {
        fun bind(data:Book)= with(itemView){
            userBook_recycler_bookname.text=data.name
            userBook_recycler_author.text=data.author
            userbookRecycler_comment.text="25"
            userbookRecycler_score.text="7.2"
            Glide.with(context).load(data.photoUri).centerCrop().into(userBook_recycler_image)
            itemView.setOnClickListener {
                val bundle=Bundle()
                bundle.putString("bookKey",data.bookId)
                val detail= BookDetailFragment()
                detail.arguments=bundle
                println("userbook adapter key ${data.bookId}")
                Navigation.findNavController(it).navigate(R.id.profilfragment_to_bookDetailFragment,bundle)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserBookAdapter.UserBookHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.userbook_recyclerview_item,parent,false)
        return UserBookHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserBookAdapter.UserBookHolder, position: Int) {
        holder.bind(list[position])

    }

    fun updateBookList(newList:List<Book>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

}