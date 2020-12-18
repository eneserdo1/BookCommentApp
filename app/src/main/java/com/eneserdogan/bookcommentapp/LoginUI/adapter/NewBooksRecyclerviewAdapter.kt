package com.eneserdogan.bookcommentapp.LoginUI.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eneserdogan.bookcommentapp.LoginUI.model.Book
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.home_newbooks_recyclerview_item.view.*

class NewBooksRecyclerviewAdapter:RecyclerView.Adapter<NewBooksRecyclerviewAdapter.MyHolder>() {

    var originalList = listOf<Book>()

    fun setApp(bookList:List<Book>){
        this.originalList=bookList
        println("Adapter ${this.originalList}")
        notifyDataSetChanged()
    }

    class MyHolder(view:View):RecyclerView.ViewHolder(view) {
        fun bind(data:Book) = with(itemView){
            newBooksRecycler_authorName.text=data.author
            newBooksRecycler_bookName.text=data.name
            Glide.with(itemView.context).load(data.photoUri).into(newBooksRecycler_image)
            itemView.setOnClickListener {
                val bundle=Bundle()
                bundle.putString("bookKey",data.bookId)
                Navigation.findNavController(it).navigate(R.id.action_navigation_home_to_bookDetailFragment,bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.home_newbooks_recyclerview_item,parent,false)
        return MyHolder(view);
    }

    override fun getItemCount(): Int {
        return this.originalList.size;
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(this.originalList[position])

    }
}