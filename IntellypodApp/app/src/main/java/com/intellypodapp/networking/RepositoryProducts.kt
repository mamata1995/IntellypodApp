package com.intellypodapp.networking

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.intellypodapp.datamodel.DataModelProductsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RepositoryProducts {

    val dataModelProductsList = MutableLiveData<DataModelProductsList>()
    val TAG: String = "RepositoryProducts"

    fun downloadData(): MutableLiveData<DataModelProductsList> {
        Log.v(TAG, "downloadData()")

        val ApiInterface = ApiBuilder.buildService(ApiInterface::class.java)
        val call = ApiInterface.getProductsData()

        call.enqueue(object : Callback<DataModelProductsList> {
            override fun onResponse(
                call: Call<DataModelProductsList>,
                response: Response<DataModelProductsList>
            ) {

                Log.v(
                    TAG,
                    "downloadData() :: onResponse() response.isSuccessful = " + response.isSuccessful
                )
                if (response.isSuccessful)
                    dataModelProductsList.value = response.body()
            }

            override fun onFailure(call: Call<DataModelProductsList>, t: Throwable) {
                Log.v(TAG, "downloadData() :: onFailure()")
            }

        })
        return dataModelProductsList
    }
}