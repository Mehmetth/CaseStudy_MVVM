package com.honeycomb.casestudy.presentation.blog

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.honeycomb.casestudy.R
import com.honeycomb.casestudy.databinding.FragmentBlogBinding
import com.honeycomb.casestudy.databinding.FragmentDetailServiceBinding
import com.honeycomb.casestudy.presentation.detail.DetailServiceFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlogFragment : Fragment(R.layout.fragment_blog)  {

    private var _binding: FragmentBlogBinding? = null
    private val binding get() = _binding!!

    val args : BlogFragmentArgs by navArgs()
    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBlogBinding.bind(view)

        binding.blogWebview.settings.javaScriptEnabled = true
        binding.blogWebview.loadUrl(args.blogUrl!!)
    }
}