package com.honeycomb.casestudy.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.honeycomb.casestudy.R
import com.honeycomb.casestudy.domain.service.entity.PostEntity
import com.honeycomb.casestudy.presentation.home.IPostClick
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_rv_item.view.*

class PostAdapter (var serviceList: List<PostEntity>,val iPostClick: IPostClick):
    RecyclerView.Adapter<PostAdapter.DataViewHolder> () {
    class DataViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.post_rv_item,parent,false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return serviceList.size
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        Picasso.get().load(serviceList[position].image_url).into(holder.view.post_image)
        holder.view.post_title_name.text = serviceList[position].category
        holder.view.post_detail_name.text = serviceList[position].title

        holder.itemView.setOnClickListener {
            iPostClick.postClicked( serviceList[position].link)
        }
    }
}