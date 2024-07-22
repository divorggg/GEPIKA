package com.example.projectmagangapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Berita(
    val title: String,
    val description: String,
    val image: String
) : Parcelable

@Parcelize
data class Kegiatan(
    val title: String,
    val description: String,
    val image: String
) : Parcelable
