package com.honeycomb.casestudy.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.honeycomb.casestudy.R
import com.honeycomb.casestudy.domain.service.entity.ServiceEntity
import com.honeycomb.casestudy.presentation.home.IClick
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.popular_service_rv_item.view.*

class PopularServiceAdapter (var serviceList: List<ServiceEntity>,
                             private val iClick: IClick):
    RecyclerView.Adapter<PopularServiceAdapter.DataViewHolder> () {
    class DataViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.popular_service_rv_item,parent,false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return serviceList.size
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        Picasso.get().load(serviceList[position].image_url).into(holder.view.popular_service_image)
        holder.view.popular_service_name.text = serviceList[position].name

        holder.itemView.setOnClickListener {
            iClick.itemClicked( serviceList[position].service_id.toString())
        }
    }
}