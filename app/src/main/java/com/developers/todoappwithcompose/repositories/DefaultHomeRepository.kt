package com.developers.todoappwithcompose.repositories


import com.developers.todoappwithcompose.data.ApiNoteService
import com.developers.todoappwithcompose.data.entites.DynmicTestItem
import com.developers.todoappwithcompose.helpers.Resource
import com.developers.todoappwithcompose.helpers.safeCall
import com.developers.todoappwithcompose.qualifiers.IOThread
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultHomeRepository @Inject constructor(
    private val apiNoteService: ApiNoteService,
    @IOThread
    private val dispatcher: CoroutineDispatcher
) {



    suspend fun getMyControls() : Resource<List<DynmicTestItem>> = withContext(dispatcher){
        safeCall {
            val result= apiNoteService.getMyControls()
            Resource.Success(result)
        }
    }

}