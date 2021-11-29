package com.sirko.hottrader

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val onClickListener: (Product) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    var products: MutableList<Product> = ArrayList()
        set(value) {
            field.addAll(value)
            notifyDataSetChanged()
        }
    var current = -1
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        products[position].apply {
            holder.itemView.setOnClickListener {
                onClickListener.invoke(this)
            }
            holder.textView1.text = name
            holder.textView2.text = price
            Picasso.get().load(image).into(holder.imageView)
            if (id == current) holder.cardView.setBackgroundColor(Color.LTGRAY)
            else holder.cardView.setBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount() = products.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageView)
        var textView1: TextView = itemView.findViewById(R.id.textView1)
        var textView2: TextView = itemView.findViewById(R.id.textView2)
        var cardView: CardView = itemView.findViewById(R.id.card_view)
    }
}