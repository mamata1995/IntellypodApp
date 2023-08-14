package com.intellypodapp.networking

import com.intellypodapp.datamodel.DataModelProductsList
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")
    fun getProductsData(): Call<DataModelProductsList>
}
