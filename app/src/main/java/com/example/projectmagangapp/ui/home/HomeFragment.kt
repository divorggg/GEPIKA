package com.example.projectmagangapp.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.projectmagangapp.R
import com.example.projectmagangapp.adapter.BeritaAdapter
import com.example.projectmagangapp.adapter.KegiatanAdapter
import com.example.projectmagangapp.adapter.YouTubeVideoAdapter
import com.example.projectmagangapp.bold
import com.example.projectmagangapp.data.Berita
import com.example.projectmagangapp.data.Kegiatan
import com.example.projectmagangapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var beritaAdapter: BeritaAdapter
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var kegiatanAdapter: KegiatanAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Inisialisasi ViewModel
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        // Inisialisasi ProgressBar
        progressBar = binding.progressBar


        // Inisialisasi RecyclerView dan Adapter Berita
        beritaAdapter = BeritaAdapter(emptyList()) { berita ->
            onItemClickBerita(berita)
        }
        val rvNews = binding.recyclerViewBerita
        rvNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvNews.adapter = beritaAdapter

        // Inisialisasi RecyclerView dan Adapter Kegiatan
        kegiatanAdapter = KegiatanAdapter(emptyList()) { kegiatan ->
            onItemClickKegiatan(kegiatan)
        }
        val rvKegiatan = binding.RvKegiatan
        rvKegiatan.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvKegiatan.adapter = kegiatanAdapter

        // Inisialisasi ViewPager2 dan Adapter YouTubeVideo
        val viewPager: ViewPager2 = binding.viewPager
        val adapter = YouTubeVideoAdapter(emptyList(), viewPager)
        viewPager.adapter = adapter

        // Tampilkan ProgressBar saat mulai memuat data
        showLoading(true)


        // Mengamati data berita dari ViewModel
        homeViewModel.news.observe(viewLifecycleOwner, Observer { newsList ->
            beritaAdapter.updateData(newsList)
            showLoading(false)
        })
        // Mengamati data Kegiatan dari ViewModel
        homeViewModel.kegiatan.observe(viewLifecycleOwner, Observer { kegiatanList ->
            kegiatanAdapter.updateData(kegiatanList)
            showLoading(false)
        })
        // Mengamati data Video Youtube dari ViewModel
        homeViewModel.videos.observe(viewLifecycleOwner, Observer { videoList ->
            adapter.updateData(videoList)
            showLoading(false)
        })

        loadRoundedImage()
        setupSwipeRefreshLayout()
        setupButtonListeners()
        setupWelcomeText()
        return root
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            // Panggil fungsi untuk menyegarkan data Anda di sini
            refreshData()
        }
    }

    private fun refreshData() {
        // Tampilkan loading saat data disegarkan
        showLoading(true)

        // Contoh menyegarkan data (misalnya, mengambil data dari server atau database)
        homeViewModel.refreshData()

        // Setelah data diperbarui, hentikan animasi penyegaran
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun setupButtonListeners() {
        binding.btnCallCenter.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:112")
            startActivity(intent)
        }

        binding.btnWhatsapp.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("tel:0741444953")
            startActivity(intent)
        }

        binding.btnLocation.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps?q=-1.628901,103.608378")
            )
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
        binding.tvOpenBerita.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_beritaFragment)
        }
        binding.tvOpenKegiatan.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_kegiatanFragment)
        }
    }

    private fun setupWelcomeText() {
        val welcomeText = "Selamat Datang di GEPIKA,"
        val descriptionText = "\ndi aplikasi Gerbang Profile Instansi Kota Jambi\n" +
                "Temukan informasi dan berita terkini tentang Instansi atau" +
                "sejarah lebih banyak dari suatu instansi"

        val s = SpannableStringBuilder()
            .bold { append(welcomeText) }
            .append(descriptionText)

        binding.tvHomeTitle.apply {
            text = s
            setTextColor(resources.getColor(R.color.white, null))
            textSize = 14f
            letterSpacing = 0.01f
            gravity = Gravity.START

            background = ContextCompat.getDrawable(requireContext(), R.drawable.background_gradient)
        }
    }

    private fun onItemClickBerita(news: Berita) {
        val bundle = Bundle().apply {
            putParcelable("news", news)
        }
        findNavController().navigate(R.id.action_nav_home_to_detailBeritaFragment, bundle)
    }

    private fun onItemClickKegiatan(kegiatan: Kegiatan) {
        val bundle = Bundle().apply {
            putParcelable("kegiatan", kegiatan)
        }
        findNavController().navigate(R.id.action_nav_home_to_detailKegiatanFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadRoundedImage() {
        val cornerRadius = resources.getDimensionPixelSize(R.dimen.corner_radius)

        val requestOptions = RequestOptions()
            .transform(RoundedCorners(cornerRadius.toInt()))
            .placeholder(R.drawable.icon_x)
            .error(R.drawable.ic_launcher_background)

        // Memuat gambar ke ImageView pertama
        Glide.with(this)
            .load(R.drawable.pj_walikota)
            .apply(requestOptions)
            .into(binding.ivPerson1)

        // Memuat gambar ke ImageView kedua
        Glide.with(this)
            .load(R.drawable.sekda)
            .apply(requestOptions)
            .into(binding.ivPerson2)
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Simpan posisi scroll untuk RecyclerView Berita
        val newsPosition = (binding.recyclerViewBerita.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        outState.putInt("news_position", newsPosition)

        // Simpan posisi scroll untuk RecyclerView Kegiatan
        val kegiatanPosition = (binding.RvKegiatan.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        outState.putInt("kegiatan_position", kegiatanPosition)

        // Simpan posisi scroll untuk ViewPager
        val viewPagerPosition = binding.viewPager.currentItem
        outState.putInt("viewpager_position", viewPagerPosition)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            // Kembalikan posisi scroll untuk RecyclerView Berita
            val newsPosition = it.getInt("news_position")
            binding.recyclerViewBerita.layoutManager?.scrollToPosition(newsPosition)

            // Kembalikan posisi scroll untuk RecyclerView Kegiatan
            val kegiatanPosition = it.getInt("kegiatan_position")
            binding.RvKegiatan.layoutManager?.scrollToPosition(kegiatanPosition)

            // Kembalikan posisi scroll untuk ViewPager
            val viewPagerPosition = it.getInt("viewpager_position")
            binding.viewPager.currentItem = viewPagerPosition
        }
    }
}