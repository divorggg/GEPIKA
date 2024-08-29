package com.example.projectmagangapp.ui.kegiatan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmagangapp.R
import com.example.projectmagangapp.adapter.KegiatanListAdapter
import com.example.projectmagangapp.data.Kegiatan
import com.example.projectmagangapp.databinding.FragmentBeritaBinding
import com.example.projectmagangapp.databinding.FragmentKegiatanBinding
import com.example.projectmagangapp.ui.home.HomeViewModel


class KegiatanFragment : Fragment() {

    private var _binding: FragmentKegiatanBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var kegiatanListAdapter: KegiatanListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKegiatanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        homeViewModel.kegiatan.observe(viewLifecycleOwner) { kegiatanList ->
            kegiatanListAdapter.updateData(kegiatanList)
        }
    }

    private fun setupRecyclerView() {
        kegiatanListAdapter = KegiatanListAdapter(emptyList()) { kegiatan ->
            onItemClickKegiatan(kegiatan)
        }

        binding.recyclerViewListKegiatan.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = kegiatanListAdapter
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun onItemClickKegiatan(kegiatan: Kegiatan) {
        val bundle = Bundle().apply {
            putParcelable("kegiatan", kegiatan)
        }
        findNavController().navigate(R.id.action_kegiatanFragment_to_detailKegiatanFragment, bundle)
    }
}