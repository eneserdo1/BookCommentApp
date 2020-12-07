package com.eneserdogan.bookcommentapp.LoginUI.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eneserdogan.bookcommentapp.LoginUI.model.Book
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.userbook_recyclerview_item.view.*

class UserBookAdapter(val list:ArrayList<Book>) :RecyclerView.Adapter<UserBookAdapter.UserBookHolder>(){
    class UserBookHolder(view: View):RecyclerView.ViewHolder(view) {

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
        val book=list[position]
        holder.itemView.userBook_recycler_bookname.text=book.name
        holder.itemView.userBook_recycler_author.text=book.author
        holder.itemView.userbookRecycler_comment.text="25"
        holder.itemView.userbookRecycler_score.text="7.2"
        val image:ImageView=holder.itemView.findViewById(R.id.userBook_recycler_image)
        //Glide.with(context).load(book.photoUri).centerCrop().into(image)


    }

    fun updateBookList(newList:List<Book>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

}