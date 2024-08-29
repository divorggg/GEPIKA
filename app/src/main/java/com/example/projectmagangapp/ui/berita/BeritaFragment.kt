package com.example.projectmagangapp.ui.berita

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmagangapp.R
import com.example.projectmagangapp.adapter.BeritaAdapter
import com.example.projectmagangapp.adapter.BeritaListAdapter
import com.example.projectmagangapp.data.Berita
import com.example.projectmagangapp.databinding.FragmentBeritaBinding
import com.example.projectmagangapp.ui.home.HomeViewModel

class BeritaFragment : Fragment() {

    private var _binding: FragmentBeritaBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var beritaListAdapter: BeritaListAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeritaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        beritaListAdapter = BeritaListAdapter(emptyList()) { berita ->
            onItemClick(berita)
        }


        binding.recyclerViewListBerita.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = beritaListAdapter

        }


    }

    private fun observeViewModel() {
        homeViewModel.news.observe(viewLifecycleOwner) { newsList ->
            beritaListAdapter.updateData(newsList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onItemClick(news: Berita) {
        val bundle = Bundle().apply {
            putParcelable("news", news)
        }
        findNavController().navigate(R.id.action_beritaFragment_to_detailBeritaFragment, bundle)
    }
}
