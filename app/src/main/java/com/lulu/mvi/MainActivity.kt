package com.lulu.mvi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.lulu.mvi.base.BaseVBActivity
import com.lulu.mvi.databinding.ActivityMainBinding
import com.lulu.mvi.inter.MainIntent
import com.lulu.mvi.inter.MainUiState
import com.lulu.mvi.viewModel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : BaseVBActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val mViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }

    override fun initData() {
        binding.btnOne.setOnClickListener{
            mViewModel.sendUiIntent(MainIntent.GetOneText)
        }

        binding.btnTwo.setOnClickListener{
            mViewModel.sendUiIntent(MainIntent.GetTwoText(""))
        }


        lifecycleScope.launch {
            mViewModel.uiStateFlow.collect{
                when(it){
                    is MainUiState.Success -> {
                        binding.btnOne.text = it.resp.toString()
                    }

                    is MainUiState.TwoTextSuccess -> {
                        binding.btnTwo.text = it.data
                    }

                    else -> { }
                }
            }


        }

    }
}