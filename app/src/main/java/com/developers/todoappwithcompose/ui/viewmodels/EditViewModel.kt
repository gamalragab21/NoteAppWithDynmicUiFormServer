package com.developers.todoappwithcompose.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developers.todoappwithcompose.data.entites.DynmicTestItem
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
class EditViewModel @Inject constructor(
    private val repository: DefaultHomeRepository,
    @MainThread
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    var isRefreshing by mutableStateOf(false)
    private val _controlsStatus =
        MutableStateFlow<Event<Resource<List<DynmicTestItem>>>>(Event(Resource.Empty()))
    val controlsStatus = _controlsStatus.asStateFlow()

    fun getMyControls() {
        viewModelScope.launch(dispatcher) {
            isRefreshing=true
            _controlsStatus.emit(Event(Resource.Loading()))
           val result=repository.getMyControls()
            _controlsStatus.emit(Event(result))
            isRefreshing=false

        }
    }

    init {

        getMyControls()
    }

}
