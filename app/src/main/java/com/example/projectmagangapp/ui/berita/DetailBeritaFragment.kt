package com.example.projectmagangapp.ui.berita

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.projectmagangapp.R
import com.example.projectmagangapp.data.Berita
import com.example.projectmagangapp.databinding.FragmentDetailBeritaBinding

class DetailBeritaFragment : Fragment() {

    private var _binding: FragmentDetailBeritaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBeritaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menerima argumen news
        val news = arguments?.getParcelable<Berita>("news")

        // Mengisi data ke dalam view
        news?.let {
            binding.titleTextViewBerita.text = it.title
            binding.descriptionTextViewBerita.text = it.description

            // Memuat gambar menggunakan Glide
            Glide.with(this)
                .load(news.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(CenterCrop(), RoundedCorners(15))
                .override(400, 300) // Mengatur ukuran gambar menjadi 300x200 piksel
                .error(R.drawable.ic_launcher_background)
                .into(binding.imageViewberitaDetail)

        }



    }
}