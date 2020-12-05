package com.eneserdogan.bookcommentapp.LoginUI.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.home_newbooks_recyclerview_item.view.*

class NewBooksRecyclerviewAdapter:RecyclerView.Adapter<NewBooksRecyclerviewAdapter.MyHolder>() {
    class MyHolder(view:View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.home_newbooks_recyclerview_item,parent,false)
        return MyHolder(view);
    }

    override fun getItemCount(): Int {
        return 8;
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.itemView.newBooksRecycler_authorName.text="J.K Rowling"
        holder.itemView.newBooksRecycler_bookName.text="Harry Potter"
        holder.itemView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_navigation_home_to_bookDetailFragment)
        }

    }
}