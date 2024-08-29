package com.example.projectmagangapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagangapp.R
import com.example.projectmagangapp.data.Berita
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class BeritaListAdapter(private var listBerita2: List<Berita>, private val onItemClickBerita: (Berita) -> Unit)
    : RecyclerView.Adapter<BeritaListAdapter.BeritaViewHolder>() {

    class BeritaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.ImageView_berita)
        private val cardViewBerita: androidx.cardview.widget.CardView = itemView.findViewById(R.id.cardView_Berita_list)

        fun bind(news: Berita, onItemClickBerita: (Berita) -> Unit) {
            titleTextView.text = news.title
            descriptionTextView.text = news.description
            Glide.with(itemView.context)
                .load(news.image) // Ganti dengan properti yang sesuai dari objek Berita
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(CenterCrop(), RoundedCorners(15))
                .override(400, 300) // Mengatur ukuran gambar menjadi 400x300 piksel
                .error(R.drawable.ic_launcher_background)
                .into(imageView)

            cardViewBerita.setOnClickListener {
                onItemClickBerita(news)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_berita_list, parent, false)
        return BeritaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listBerita2.size
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val berita2 = listBerita2[position]
        holder.bind(berita2, onItemClickBerita)
    }

    fun updateData(newNewsList: List<Berita>) {
        listBerita2 = newNewsList
        notifyDataSetChanged()
    }


}
