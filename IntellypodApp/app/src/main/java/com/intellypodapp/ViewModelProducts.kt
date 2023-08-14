package com.intellypodapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.intellypodapp.datamodel.DataModelProductsList
import com.intellypodapp.networking.RepositoryProducts

class ViewModelProducts : ViewModel() {

    fun getProductsData(): LiveData<DataModelProductsList>? {
        return RepositoryProducts.downloadData()
    }
}