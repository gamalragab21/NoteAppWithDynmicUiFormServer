package com.developers.todoappwithcompose.data.entites


import com.google.gson.annotations.SerializedName

data class DynmicTestItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("placeHolder")
    val placeHolder: String,
    @SerializedName("radius")
    val radius: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: String
)