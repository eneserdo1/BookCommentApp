package com.eneserdogan.bookcommentapp.LoginUI.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eneserdogan.bookcommentapp.R
import kotlinx.android.synthetic.main.home_suggested_recyclerview_item.view.*

class SuggestedBookRecyclerAdapter:RecyclerView.Adapter<SuggestedBookRecyclerAdapter.MyHolder>() {
    class MyHolder(view:View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestedBookRecyclerAdapter.MyHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.home_suggested_recyclerview_item,parent,false)
        return SuggestedBookRecyclerAdapter.MyHolder(view)
    }

    override fun getItemCount(): Int {
        return 4;
    }

    override fun onBindViewHolder(holder: SuggestedBookRecyclerAdapter.MyHolder, position: Int) {
        holder.itemView.suggestedRecycler_author_name.text="Muhammed Enes Erdoğan"
        holder.itemView.suggestedRecycler_bookName.text="Harry Potter Felsefe Taşı"
        holder.itemView.suggestedRecycler_score.text="9.4"
    }
}