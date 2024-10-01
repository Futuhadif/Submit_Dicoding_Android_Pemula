package com.dicoding.presiden

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListCalonPresidenAdapter(private val listPresiden: ArrayList<Presiden>):RecyclerView.Adapter<ListCalonPresidenAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_calon_presiden, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPresiden.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listPresiden[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listPresiden[holder.adapterPosition]) }

    }

    class ListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imgPhoto : ImageView = itemView.findViewById(R.id.img_photo)
        val tvName : TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription : TextView = itemView.findViewById(R.id.tv_item_deskripsi)
    }

    interface OnItemClickCallback{
        fun onItemClicked(data:Presiden)
    }

}