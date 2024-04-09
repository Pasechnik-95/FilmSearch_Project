package com.example.project_moviesearch.fragments

import AnimationHelper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project_moviesearch.databinding.FragmentCollectionsBinding


class CollectionsFragment : Fragment() {
    private var _binding: FragmentCollectionsBinding? = null
    private val binding: FragmentCollectionsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AnimationHelper.performFragmentCircularRevealAnimation(binding.homeFragmentRoot, requireActivity(), 3)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}