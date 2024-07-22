package com.example.projectmagangapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmagangapp.adapter.BeritaAdapter
import com.example.projectmagangapp.adapter.KegiatanAdapter
import com.example.projectmagangapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var beritaAdapter: BeritaAdapter
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var kegiatanAdapter : KegiatanAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Inisialisasi ViewModel
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Inisialisasi RecyclerView dan Adapter
        beritaAdapter = BeritaAdapter(emptyList())
        val rvNews = binding.recyclerViewBerita
        rvNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvNews.adapter = beritaAdapter

        kegiatanAdapter = KegiatanAdapter(emptyList())
        val rvKegiatan = binding.RvKegiatan
        rvKegiatan.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvKegiatan.adapter = kegiatanAdapter

        // Mengamati data berita dari ViewModel
        homeViewModel.news.observe(viewLifecycleOwner, Observer { newsList ->
            beritaAdapter.updateData(newsList)
        })

        homeViewModel.kegiatan.observe(viewLifecycleOwner, Observer { kegiatanList ->
            kegiatanAdapter.updateData(kegiatanList)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
