package com.example.projectmagangapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.projectmagangapp.R
import com.example.projectmagangapp.data.Berita

class BeritaAdapter(private var listBerita: List<Berita>) : RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_berita, parent, false)
        return BeritaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listBerita.size
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val berita = listBerita[position]
        holder.bind(berita)
    }

    class BeritaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageViewNews)

        fun bind(news: Berita) {
            titleTextView.text = news.title
            descriptionTextView.text = news.description
            Glide.with(itemView.context)
                .load(R.drawable.bg_img_home)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .transform(CircleCrop())
                .error(R.drawable.ic_launcher_background) // Error image
                .into(imageView)
        }
    }

    fun updateData(newNewsList: List<Berita>) {
        listBerita = newNewsList
        notifyDataSetChanged()
    }
}
