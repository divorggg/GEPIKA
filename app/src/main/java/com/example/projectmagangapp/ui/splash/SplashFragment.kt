package com.example.projectmagangapp.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectmagangapp.R
import com.example.projectmagangapp.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    private var title: String? = null
    private var description: String? = null
    private var imageResource = 0
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            requireArguments().apply {
                title = getString(ARG_TITLE)
                description = getString(ARG_DESCRIPTION)
                imageResource = getInt(ARG_IMAGE_RES)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        binding.apply {
            textOnboardingTitle.text = title
            textOnboardingDescription.text = description
            imageOnboarding.setAnimation(imageResource)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"
        private const val ARG_IMAGE_RES = "imageRes"

        fun newInstance(
            title: String?,
            description: String?,
            imageResource: Int
        ): SplashFragment {
            val fragment = SplashFragment()
            fragment.arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_DESCRIPTION, description)
                putInt(ARG_IMAGE_RES, imageResource)
            }

            return fragment
        }
    }
}