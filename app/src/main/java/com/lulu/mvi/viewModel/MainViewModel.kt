package com.lulu.mvi.viewModel

import android.util.Log
import androidx.lifecycle.scopeNetLife
import com.drake.net.Get
import com.lulu.mvi.base.BaseViewModel
import com.lulu.mvi.base.GsonConvert
import com.lulu.mvi.base.GsonConvert2
import com.lulu.mvi.bean.PassWorkData
import com.lulu.mvi.inter.MainIntent
import com.lulu.mvi.inter.MainUiState


class MainViewModel : BaseViewModel<MainUiState, MainIntent>() {
    override fun initUIState() = MainUiState.Init

    override fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.GetOneText -> {
                getOneData()
            }
            is MainIntent.GetTwoText -> {
                getTwoData(intent.name)
            }
        }
    }

    private fun getOneData(){
        scopeNetLife {
            try {
                val data = Get<PassWorkData>("https://api.kuleu.com/api/suijimima?ss_xx=1&ss_dx=1&ss_sz=1"){
                    converter = GsonConvert()
                }.await()
                Log.d("TAG", "getListData: $data")
                sendUiState{
                    MainUiState.Success(data.password_box.toString())
                }
            }catch (e : Exception){
                sendUiState{
                    MainUiState.Fail(e.message)
                }
            }
        }
    }

    private fun getTwoData(name : String){
        scopeNetLife {
            try {
                val data = Get<String>("https://api.kuleu.com/api/MP4_xiaojiejie"){
                    converter = GsonConvert2()
                    param("type" , "json")
                }.await()
                Log.d("TAG", "getListData: $data")
                sendUiState{
                    MainUiState.TwoTextSuccess(data)
                }
            }catch (e : Exception){
                sendUiState{
                    MainUiState.Fail(e.message)
                }
            }
        }
    }

}

