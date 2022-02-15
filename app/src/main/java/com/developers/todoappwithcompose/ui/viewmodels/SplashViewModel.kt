package com.developers.todoappwithcompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developers.todoappwithcompose.helpers.Event
import com.developers.todoappwithcompose.helpers.Resource
import com.developers.todoappwithcompose.qualifiers.MainThread
import com.developers.todoappwithcompose.repositories.DefaultHomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: DefaultHomeRepository,
    @MainThread
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _splashStatus =
        MutableStateFlow<Event<Resource<Int>>>(Event(Resource.Empty()))
    val splashStatus = _splashStatus.asStateFlow()

    fun startSplashScreen() {
        viewModelScope.launch(dispatcher) {
            _splashStatus.emit(Event(Resource.Loading()))
            delay(4000)
            _splashStatus.emit(Event(Resource.Success(1)))
        }
    }


}
