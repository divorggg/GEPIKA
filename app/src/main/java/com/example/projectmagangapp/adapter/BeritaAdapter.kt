package com.example.projectmagangapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.projectmagangapp.R
import com.example.projectmagangapp.data.Berita
import com.bumptech.glide.request.target.Target

class BeritaAdapter( private var listBerita: List<Berita>,
                     private val onItemClickBerita: (Berita) -> Unit
) : RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_berita, parent, false)
        return BeritaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listBerita.size
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val berita = listBerita[position]
        holder.bind(berita, onItemClickBerita)
    }

    class BeritaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.title_Text_View_berita)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageViewNews)
        private val buttonOpenBerita: Button = itemView.findViewById(R.id.button_open_berita)
        private val cardViewBerita: androidx.cardview.widget.CardView = itemView.findViewById(R.id.cardView_Berita)

        fun bind(news: Berita, onItemClickBerita: (Berita) -> Unit) {
            titleTextView.text = news.title
            descriptionTextView.text = news.description

            buttonOpenBerita.setOnClickListener {
                onItemClickBerita(news)
            }

            cardViewBerita.setOnClickListener {
                onItemClickBerita(news)
            }


            Glide.with(itemView.context)
                .load(news.image)
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
