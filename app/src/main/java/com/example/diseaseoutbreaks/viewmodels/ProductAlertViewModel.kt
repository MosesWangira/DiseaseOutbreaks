package com.example.diseaseoutbreaks.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.diseaseoutbreaks.data.Model.productalert.ProductAlertDataClass
import com.example.diseaseoutbreaks.data.Model.productalert.ProductItem
import com.example.diseaseoutbreaks.data.adapter.ProductAlertAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.await
import java.io.IOException

class ProductAlertViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    /**
     *  LiveData gives us updated words when they change.
     * */
    var allMedicalProduct: MutableLiveData<ProductAlertDataClass>

    private var adapter: ProductAlertAdapter

    init {
        Log.d("Disease", "Disease ViewModel created")
        allMedicalProduct = MutableLiveData()
        adapter = ProductAlertAdapter()
    }

    fun getMyAdapter(): ProductAlertAdapter {
        return adapter
    }

    fun setAdapterData(data: List<ProductItem>) {
        adapter.setDataList(data)
        adapter.notifyDataSetChanged()
    }

    fun getAllMedicalProducts(): MutableLiveData<ProductAlertDataClass> {
        return allMedicalProduct
    }

    fun fetchProductInCoroutine() = viewModelScope.launch {
        try{
            val fetchingProduct = RetrofitBuilder.apiService.getMedicalProductNews().await()
            allMedicalProduct.postValue(fetchingProduct)
        }catch (networkError: IOException){
            //show infinite loading spinner
            allMedicalProduct.postValue(null)
        }
    }
}