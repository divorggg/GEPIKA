package com.example.projectmagangapp.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.projectmagangapp.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val imageList = ArrayList<SlideModel>() // Create image list

        // Adding images to the list
        val imageUrl1 = "https://bit.ly/2YoJ77H"
        val imageUrl2 = "https://bit.ly/2BteuF2"
        val imageUrl3 = "https://bit.ly/3fLJf72"

        if (imageUrl1.isNotEmpty()) {
            imageList.add(SlideModel(imageUrl1, "The animal population decreased by 58 percent in 42 years."))
        }
        if (imageUrl2.isNotEmpty()) {
            imageList.add(SlideModel(imageUrl2, "Elephants and tigers may become extinct."))
        }
        if (imageUrl3.isNotEmpty()) {
            imageList.add(SlideModel(imageUrl3, "And people do that."))
        }

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
