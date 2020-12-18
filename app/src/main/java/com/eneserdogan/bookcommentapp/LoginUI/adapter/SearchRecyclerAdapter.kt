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
import kotlinx.android.synthetic.main.search_recyclerview_item.view.*

class SearchRecyclerAdapter :RecyclerView.Adapter<SearchRecyclerAdapter.MyViewHolder>() {
    var originalList= listOf<Book>()
    var filterList= listOf<Book>()

    fun setApp(dataList:List<Book>){
        this.originalList=dataList
        this.filterList=dataList
        notifyDataSetChanged()
    }

    class MyViewHolder(view:View):RecyclerView.ViewHolder(view) {
        fun bind(data:Book)= with(itemView){
            searchRecycler_title.text=data.name
            searchRecycler_author.text=data.author
            searchRecycler_rating.text="3000+ Değerlendirme"
            searchRecycler_score.text="9.4"
            Glide.with(itemView.context).load(data.photoUri).into(searchRecycler_ımage)
            itemView.setOnClickListener {
                val bundle=Bundle()
                bundle.putString("bookKey",data.bookId)
                Navigation.findNavController(it).navigate(R.id.searchfragment_to_bookDetailFragment,bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.search_recyclerview_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.filterList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(this.filterList[position])

    }
}