package com.lulu.mvi.base

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

abstract class BaseVBActivity<VB : ViewBinding>(val block: (LayoutInflater) -> VB) :
    BaseActivity() {
    private var _binding: VB? = null
    protected val binding: VB
        get() = requireNotNull(_binding) { "The binding has been destroyed" }

    override fun initView() {
        _binding = block(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
