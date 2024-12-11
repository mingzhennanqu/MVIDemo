package com.lulu.mvi.inter

import com.lulu.mvi.base.IUIState
import com.lulu.mvi.base.IUiIntent

sealed class MainIntent : IUiIntent {
    data object GetOneText : MainIntent()
    data class GetTwoText(var name: String) : MainIntent()
}

sealed class MainUiState : IUIState {
    data object Init : MainUiState()
    data class Success(val resp: String?): MainUiState()
    data class Fail(val msg: String?): MainUiState()

    data class TwoTextSuccess(val data: String?) : MainUiState()
}
