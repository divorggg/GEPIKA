package com.example.projectmagangapp.ui.kegiatan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.projectmagangapp.R
import com.example.projectmagangapp.data.Berita
import com.example.projectmagangapp.data.Kegiatan
import com.example.projectmagangapp.databinding.FragmentDetailBeritaBinding
import com.example.projectmagangapp.databinding.FragmentDetailKegiatanBinding


class DetailKegiatanFragment : Fragment() {

    private var _binding: FragmentDetailKegiatanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailKegiatanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menerima argumen news
        val kegiatan = arguments?.getParcelable<Kegiatan>("kegiatan")

        // Mengisi data ke dalam view
        kegiatan?.let {
            binding.titleTextViewKegiatan.text = it.title
            binding.descriptionTextViewKegiatan.text = it.description

            Glide.with(this)
                .load(kegiatan.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(CenterCrop(), RoundedCorners(15))
                .override(400, 300) // Mengatur ukuran gambar menjadi 300x200 piksel
                .error(R.drawable.ic_launcher_background)
                .into(binding.imageViewKegiatanDetail)
        }
    }
}