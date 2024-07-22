package com.example.projectmagangapp.ui.visi_misi

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.projectmagangapp.R

class VisiMisiFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_visi_misi, container, false)

        val htmlText = "<ol><li>Mewujudkan Misi Jaringan Komunikasi dan Informatika yang terintegrasi</li><li>Mewujudkan Sarana Komunikasi dan Diseminasi Informasi yang Efektif</li><li>Melaksanakan Pengelolaan Data dan Produksi Secara Elektronik</li><li>Melaksanakan Urusan Tangga untuk Rumah Mendukung Peningkatan Kapasitas Organisasi dan Pengembangan Sumber Daya Manusia</li></ol>"
        val textView = view.findViewById<TextView>(R.id.misi_text_view) // specify the ID of the TextView
        textView.text = Html.fromHtml(htmlText)

        return view
    }
}