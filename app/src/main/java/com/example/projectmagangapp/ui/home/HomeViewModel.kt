package com.example.projectmagangapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectmagangapp.R
import com.example.projectmagangapp.data.Berita
import com.example.projectmagangapp.data.Kegiatan
import com.example.projectmagangapp.data.YouTubeVideo

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _news = MutableLiveData<List<Berita>>()
    val news: LiveData<List<Berita>> = _news

    private val _kegiatan = MutableLiveData<List<Kegiatan>>()
    val kegiatan: LiveData<List<Kegiatan>> = _kegiatan

    private val _videos = MutableLiveData<List<YouTubeVideo>>()
    val videos: LiveData<List<YouTubeVideo>> = _videos

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        val context = getApplication<Application>().applicationContext

        _news.value = listOf(
            Berita(
                "Masih Sepi Pelamar, CPNS Pemkot Jambi Baru 50 Pendaftar",
                context.getString(R.string.berita1),
                "https://www.jambiupdate.co/foto_berita/2024/08/25/57ppk.jpg"
            ),
            Berita("Punya Bentuk Unik, Inilah Tugu Keris Jambi yang Jadi Destinasi Wisata Murah Meriah di Pusat Kota",
                context.getString(R.string.berita2),
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8b/Tugu_Keris_Siginjai.jpg/1200px-Tugu_Keris_Siginjai.jpg")
            // Tambahkan berita lainnya di sini
        )

        _kegiatan.value = listOf(
            Kegiatan(
                "Pemkot Jambi - Baznas salurkan Rp1,88 miliar beasiswa pendidikan",
                context.getString(R.string.kegiatan1),
                "https://img.antaranews.com/cache/1200x800/2024/08/22/1000200859.jpg"
            ),
            Kegiatan(
                " Bank Indonesia Jambi Bersama Pemkot Jambi Lakukan Gerakan Nasional Pengendalian Inflasi Pangan ",
                context.getString(R.string.kegiatan_2),
                "https://static.promediateknologi.id/crop/0x0:0x0/750x500/webp/photo/p1/661/2024/08/25/IMG-20240825-WA0026-2874051706.jpg"
            )
            // Tambahkan kegiatan lainnya di sini
        )

        _videos.value = listOf(
            YouTubeVideo(
                "https://i.ytimg.com/vi/6NPG7drxsM8/hqdefault.jpg",
                "Selamat Memperingati Hari Anti Narkotika Internasional (HANI 2024)",
                "6NPG7drxsM8"
            ),
            YouTubeVideo(
                "https://i.ytimg.com/vi/w_zH-nYNUGI/hqdefault.jpg",
                "RAPAT PARIPURNA HUT KE-78 PEMERINTAH KOTA JAMBI DAN HARI JADI KE-623 TANAH PILIH PUSAKO BATUAH",
                "w_zH-nYNUGI"
            ),
            YouTubeVideo(
                "https://i.ytimg.com/vi/wtjYWGBvVUM/hqdefault_live.jpg",
                "RAPAT PARIPURNA HARI JADI KE-623 TANAH PILIH PUSAKO BATUAH DAN HARI HUT KE-78 PEMERINTAH KOTA JAMBI",
                "wtjYWGBvVUM"
            ),
            YouTubeVideo(
                "https://i.ytimg.com/vi/BKUeSL0vCnU/hqdefault.jpg",
                "Selamat Hari Raya Idul Fitri 1 Syawal 1445 H",
                "BKUeSL0vCnU"
            ),
            YouTubeVideo(
                "https://i.ytimg.com/vi/a9NVraR0BsGKdYbMQi-ALZn5Iv4/hqdefault.jpg",
                "Selamat Memperingati Hari Anak Nasional 2024",
                "a9NVraR0BsGKdYbMQi-ALZn5Iv4"
            )
            // Tambahkan video lainnya di sini jika diperlukan
        )
    }

    fun refreshData() {
        loadInitialData()
    }
}
