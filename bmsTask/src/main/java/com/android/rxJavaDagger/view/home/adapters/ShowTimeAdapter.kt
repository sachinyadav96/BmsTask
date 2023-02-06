package com.android.rxJavaDagger.view.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.rxJavaDagger.R
import com.android.rxJavaDagger.model.MovieShowTimeListData

class ShowTimeAdapter(
    private val mContext: Context, private val list: ArrayList<MovieShowTimeListData>
) : RecyclerView.Adapter<ShowTimeAdapter.ShowTimeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowTimeHolder {
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_show_time, parent, false)
        return ShowTimeHolder(view)
    }

    override fun onBindViewHolder(holder: ShowTimeHolder, position: Int) {
        val data: MovieShowTimeListData = list[holder.position]
        holder.txtVenueTitle.text = data.showTime
     }

    override fun getItemCount(): Int {
        return list.size
    }

    class ShowTimeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtVenueTitle: TextView

        init {
            txtVenueTitle = itemView.findViewById(R.id.txtVenueTitle)
         }
    }
}