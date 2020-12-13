package com.eneserdogan.bookcommentapp.LoginUI.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eneserdogan.bookcommentapp.LoginUI.model.Book
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.home_suggested_recyclerview_item.view.*

class SuggestedBookRecyclerAdapter:RecyclerView.Adapter<SuggestedBookRecyclerAdapter.MyHolder>() {
    var originalList= listOf<Book>()

    fun setApp(bookList : List<Book>){
        this.originalList = bookList
        notifyDataSetChanged()
    }
    class MyHolder(view:View):RecyclerView.ViewHolder(view) {
        fun bind(data:Book)= with(itemView){
            suggestedRecycler_author_name.text=data.author
            suggestedRecycler_bookName.text=data.name
            suggestedRecycler_score.text="8.4"
            Glide.with(itemView.context).load(data.photoUri).into(suggestedRecycler_image)
            Glide.with(itemView.context).load(data.photoUri).into(suggestedRecycler_author_ımage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestedBookRecyclerAdapter.MyHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.home_suggested_recyclerview_item,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return this.originalList.size;
    }

    override fun onBindViewHolder(holder: SuggestedBookRecyclerAdapter.MyHolder, position: Int) {
        holder.bind(this.originalList[position])
    }
}