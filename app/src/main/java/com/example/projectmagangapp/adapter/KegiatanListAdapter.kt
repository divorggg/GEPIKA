package com.example.projectmagangapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.projectmagangapp.R
import com.example.projectmagangapp.data.Kegiatan

class KegiatanListAdapter (private var kegiatanList2: List<Kegiatan>,
                           private val onItemClickKegiatan: (Kegiatan) -> Unit
): RecyclerView.Adapter<KegiatanListAdapter.KegiatanViewHolder>() {


    class KegiatanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.ImageView_berita)
        private val cardViewBerita: androidx.cardview.widget.CardView = itemView.findViewById(R.id.cardView_Berita_list)

        fun bind(kegiatan: Kegiatan, onItemClickKegiatan: (Kegiatan) -> Unit) {
            titleTextView.text = kegiatan.title
            descriptionTextView.text = kegiatan.description
            Glide.with(itemView.context)
                .load(kegiatan.image) // Ganti dengan properti yang sesuai dari objek Berita
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(CenterCrop(), RoundedCorners(15))
                .override(400, 300) // Mengatur ukuran gambar menjadi 400x300 piksel
                .error(R.drawable.ic_launcher_background)
                .into(imageView)

            cardViewBerita.setOnClickListener {
                onItemClickKegiatan(kegiatan)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KegiatanViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_berita_list, parent, false)
        return KegiatanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return kegiatanList2.size
    }

    override fun onBindViewHolder(holder: KegiatanViewHolder, position: Int) {
        val kegiatan2 = kegiatanList2[position]
        holder.bind(kegiatan2, onItemClickKegiatan)
    }

    fun updateData(newKegiatanList: List<Kegiatan>) {
        kegiatanList2 = newKegiatanList
        notifyDataSetChanged()

    }
}