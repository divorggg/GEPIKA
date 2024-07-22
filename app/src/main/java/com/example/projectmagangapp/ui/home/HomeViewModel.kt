package com.example.projectmagangapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectmagangapp.data.Berita
import com.example.projectmagangapp.data.Kegiatan

class HomeViewModel : ViewModel() {

    private val _news = MutableLiveData<List<Berita>>()
    val news: LiveData<List<Berita>> = _news

    private val _kegiatan = MutableLiveData<List<Kegiatan>>()
    val kegiatan : LiveData<List<Kegiatan>> = _kegiatan

    init {
        // Simulasikan data berita
        _news.value = listOf(
            Berita("Title 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "https://example.com/image1.jpg"),
            Berita("Title 2", "Description 2", "https://example.com/image1.jpg"),
            // Tambahkan berita lain di sini
        )
        _kegiatan.value = listOf(
            Kegiatan("Title 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.", "https://example.com/image1.jpg"),
            Kegiatan("Title 2", "Description 2", "https://example.com/image1.jpg"),
            )
    }
}
