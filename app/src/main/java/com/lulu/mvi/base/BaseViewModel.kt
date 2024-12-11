package com.lulu.mvi.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState : IUIState, UiIntent : IUiIntent> : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(initUIState())
    val uiStateFlow: StateFlow<UiState> = _uiStateFlow

    private val intentChannel: Channel<UiIntent> = Channel()

    protected abstract fun initUIState(): UiState
    protected abstract fun handleIntent(intent: UiIntent)

    init {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect {
                handleIntent(it)
            }
        }
    }

    fun sendUiIntent(uiIntent: UiIntent) {
        viewModelScope.launch {
            intentChannel.send(uiIntent)
        }
    }

    protected fun sendUiState(copy: UiState.() -> UiState) {
        _uiStateFlow.update { copy(_uiStateFlow.value) }
    }

}
