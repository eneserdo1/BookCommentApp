package com.eneserdogan.bookcommentapp.LoginUI.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.search_recyclerview_item.view.*

class SearchRecyclerAdapter :RecyclerView.Adapter<SearchRecyclerAdapter.MyViewHolder>() {
    class MyViewHolder(view:View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.search_recyclerview_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 8
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.searchRecycler_title.text="Harry Potter ve Felsefe Taşı"
        holder.itemView.searchRecycler_author.text="J.K.Rowling"
        holder.itemView.searchRecycler_rating.text="3000+ Değerlendirme"
        holder.itemView.searchRecycler_score.text="9.4"
    }
}