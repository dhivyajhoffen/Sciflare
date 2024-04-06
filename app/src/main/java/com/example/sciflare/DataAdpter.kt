package com.example.sciflare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DataAdpter(private var dataList: List<UserModel>, private val context: Details) :
    RecyclerView.Adapter<DataAdpter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdpter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DataAdpter.ViewHolder, position: Int) {
        val dataModel = dataList.get(position)

        holder.name_tv.text = "Name:  " + dataModel.name
        holder.email_tv.text = "Email:  " + dataModel.email
        holder.mobile_tv.text = "Mobile:  " + dataModel.mobile
        holder.gender_tv.text = "Gender:  " + dataModel.gender

    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var name_tv: TextView
        var email_tv: TextView
        var mobile_tv: TextView
        var gender_tv: TextView


        init {
            name_tv = itemLayoutView.findViewById(R.id.name_tv)
            email_tv = itemLayoutView.findViewById(R.id.email_tv)
            mobile_tv = itemLayoutView.findViewById(R.id.mobile_tv)
            gender_tv = itemLayoutView.findViewById(R.id.gender_tv)

        }

    }

}