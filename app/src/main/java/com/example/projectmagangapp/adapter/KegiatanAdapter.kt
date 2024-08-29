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
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.projectmagangapp.R
import com.example.projectmagangapp.data.Kegiatan

class KegiatanAdapter (private var kegiatanList: List<Kegiatan>,
                       private val onItemClickKegiatan: (Kegiatan) -> Unit
): RecyclerView.Adapter<KegiatanAdapter.KegiatanViewHolder>() {


    class KegiatanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.title_Text_View_berita)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageViewNews)
        private val buttonOpenKegiatan : Button = itemView.findViewById(R.id.button_open_berita)
        private val cardViewBerita: androidx.cardview.widget.CardView = itemView.findViewById(R.id.cardView_Berita)

        fun bind(kegiatan: Kegiatan,onItemClickKegiatan: (Kegiatan) -> Unit) {
            titleTextView.text = kegiatan.title
            descriptionTextView.text = kegiatan.description


            buttonOpenKegiatan.setOnClickListener {
                onItemClickKegiatan(kegiatan)
            }

            cardViewBerita.setOnClickListener {
                onItemClickKegiatan(kegiatan)
            }

            Glide.with(itemView.context)
                .load(kegiatan.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .transform(CircleCrop())
                .error(R.drawable.ic_launcher_background) // Error image
                .into(imageView)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KegiatanViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_berita, parent, false)
        return KegiatanViewHolder(view)
    }

    override fun onBindViewHolder(holder: KegiatanViewHolder, position: Int) {
        val kegiatan = kegiatanList[position]
        holder.bind(kegiatan, onItemClickKegiatan)
    }

    override fun getItemCount(): Int {
        return kegiatanList.size
    }

    fun updateData(newKegiatanList: List<Kegiatan>) {
        kegiatanList = newKegiatanList
        notifyDataSetChanged()
    }
}