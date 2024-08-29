package com.example.projectmagangapp.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.projectmagangapp.R
import com.example.projectmagangapp.data.YouTubeVideo

class YouTubeVideoAdapter(
    private var videoList: List<YouTubeVideo>,
    private val viewPager: ViewPager2
) : RecyclerView.Adapter<YouTubeVideoAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val thumbnail: ImageView = itemView.findViewById(R.id.imageView_youtube)
        private val title: TextView = itemView.findViewById(R.id.Tv_youtube_title)
        private val buttonLeft: ImageButton = itemView.findViewById(R.id.button_youtube_left)
        private val buttonRight: ImageButton = itemView.findViewById(R.id.button_youtube_right)

        fun bind(video: YouTubeVideo) {
            title.text = video.title
            Glide.with(itemView.context)
                .load(video.thumbnailUrl)
                .apply(RequestOptions().error(R.drawable.ic_launcher_background))
                .into(thumbnail)

            thumbnail.setOnClickListener {
                val context = itemView.context
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=${video.videoId}"))
                context.startActivity(intent)
            }

            buttonLeft.setOnClickListener {
                if (viewPager.currentItem > 0) {
                    viewPager.currentItem -= 1
                }
            }

            buttonRight.setOnClickListener {
                viewPager.adapter?.let {
                    if (viewPager.currentItem < it.itemCount - 1) {
                        viewPager.currentItem += 1
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_youtube, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videoList[position])
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    fun updateData(newVideoList: List<YouTubeVideo>) {
        videoList = newVideoList
        notifyDataSetChanged()
    }
}
