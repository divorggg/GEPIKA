package com.example.projectmagangapp.ui.buku_panduan

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.projectmagangapp.R

class BukuPanduanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buku_panduan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val Btn_bukpad_download = view.findViewById<TextView>(R.id.Btn_bukpad_download)

        Btn_bukpad_download.setOnClickListener {
            // Ganti dengan URL file Google Drive Anda
            val googleDriveUrl = "https://drive.google.com/uc?id=<FILE_ID>&export=download"
            downloadFromGoogleDrive(googleDriveUrl)
        }
    }

    private fun downloadFromGoogleDrive(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
