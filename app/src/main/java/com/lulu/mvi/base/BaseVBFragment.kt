package com.lulu.mvi.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class BaseVBFragment<VB : ViewBinding>(val block: (LayoutInflater) -> VB) :
    BaseFragment() {
    private var _binding: VB? = null
    protected val binding: VB
        get() = requireNotNull(_binding) { "The binding has been destroyed" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = block(layoutInflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
