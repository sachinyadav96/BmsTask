package com.android.rxJavaDagger.view.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.rxJavaDagger.R
import com.android.rxJavaDagger.model.VenueShowFeedItem
import com.android.rxJavaDagger.view.home.adapters.HomeImageAdapter.AccountHolder
import kotlinx.android.synthetic.main.item_venue_name.view.*

class HomeImageAdapter(
    private val mContext: Context, private val list: ArrayList<VenueShowFeedItem>
) : RecyclerView.Adapter<AccountHolder>() {

    var onShowTimeClicked: ((VenueShowFeedItem?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountHolder {
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_venue_name, parent, false)
        return AccountHolder(view)
    }

    override fun onBindViewHolder(holder: AccountHolder, position: Int) {
        val data: VenueShowFeedItem = list[holder.adapterPosition]

        holder.userName.text = data.title
        holder.imageTags.text = data.showDate
        holder.showTime.setOnClickListener {
            onShowTimeClicked?.invoke(data)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class AccountHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var userName: TextView
        var imageTags: TextView
        var showTime: TextView

        init {
            userName = itemView.findViewById(R.id.txtVenueTitle)
            imageTags = itemView.findViewById(R.id.txtShowDate)
            showTime = itemView.findViewById(R.id.showTime)
        }
    }
}