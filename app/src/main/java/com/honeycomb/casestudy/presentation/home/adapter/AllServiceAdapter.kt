package com.honeycomb.casestudy.presentation.home.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.honeycomb.casestudy.R
import com.honeycomb.casestudy.domain.service.entity.ServiceEntity
import com.honeycomb.casestudy.presentation.home.IClick
import kotlinx.android.synthetic.main.service_rv_item.view.*

class AllServiceAdapter (var activity: Activity,
                         var serviceList: List<ServiceEntity>,
                         private val iClick: IClick):
    RecyclerView.Adapter<AllServiceAdapter.DataViewHolder> () {
    class DataViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.service_rv_item,parent,false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return serviceList.size
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        when(position)
        {
            0 -> holder.view.service_image.setImageDrawable(activity.getDrawable(R.drawable.ic_tadilat))
            1 -> holder.view.service_image.setImageDrawable(activity.getDrawable(R.drawable.ic_temizlik))
            2 -> holder.view.service_image.setImageDrawable(activity.getDrawable(R.drawable.ic_nakliyat))
            3 -> holder.view.service_image.setImageDrawable(activity.getDrawable(R.drawable.ic_tamir))
            4 -> holder.view.service_image.setImageDrawable(activity.getDrawable(R.drawable.ic_ozel_ders))
            5 -> holder.view.service_image.setImageDrawable(activity.getDrawable(R.drawable.ic_saglik))
            6 -> holder.view.service_image.setImageDrawable(activity.getDrawable(R.drawable.ic_dugun))
            7 -> holder.view.service_image.setImageDrawable(activity.getDrawable(R.drawable.ic_diger))
        }

        holder.view.service_name.text = serviceList[position].name

        holder.itemView.setOnClickListener {
            iClick.itemClicked( serviceList[position].service_id.toString())
        }
    }
}
