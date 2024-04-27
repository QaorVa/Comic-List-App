package com.example.submission_andsederhana_abdan

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListComicAdapter(private val listComic: ArrayList<Comic>): RecyclerView.Adapter<ListComicAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Comic)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_comic, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listComic.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val(title, author, longDescription, genre, photo, url) = listComic[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvTitle.text = title
        holder.tvAuthor.text = author
        holder.tvGenre.text = genre

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listComic[holder.adapterPosition]) }
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvAuthor: TextView = itemView.findViewById(R.id.tv_item_author)
        val tvGenre: TextView = itemView.findViewById(R.id.tv_item_genre)
    }

}