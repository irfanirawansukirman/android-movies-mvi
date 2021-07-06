package com.example.learningmviarchitecture.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learningmviarchitecture.util.ui.UIEffect
import com.example.learningmviarchitecture.util.ui.UiEvent
import com.example.learningmviarchitecture.util.ui.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event : UiEvent, State : UiState, Effect : UIEffect> :
    ViewModel() {

    // create initial state of view
    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent: MutableSharedFlow<Event> = MutableSharedFlow()
    private val uiEvent = _uiEvent.asSharedFlow()

    private val _uiEffect: Channel<Effect> = Channel()
    val uiEffect = _uiEffect.receiveAsFlow()

    init {
        subscribeEvents()
    }

    // start listening to event
    private fun subscribeEvents() {
        viewModelScope.launch { uiEvent.collect { handleEvent(it) } }
    }

    // handle each event
    abstract fun handleEvent(event: Event)

    // set new event
    fun setEvent(event: Event) {
        viewModelScope.launch { _uiEvent.emit(event) }
    }

    // set new ui state
    protected fun setState(reduce: State.() -> State) {
        val newState = uiState.value.reduce()
        _uiState.value = newState
    }

    // set new effect
    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _uiEffect.send(effectValue) }
    }
}
