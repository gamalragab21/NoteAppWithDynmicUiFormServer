package com.developers.todoappwithcompose.data

import com.developers.todoappwithcompose.data.entites.DynmicTestItem
import retrofit2.http.GET

interface ApiNoteService {
    @GET("controls")
    suspend fun getMyControls():List<DynmicTestItem>
}
