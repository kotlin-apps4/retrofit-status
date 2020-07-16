package com.shunya.myapplication.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel : ViewModel() {

    fun loadData() = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val api = ApiClient.createService(PersonApi::class.java)

        try {
            val response = api.getPerson()
            if(response.isSuccessful) {
                emit(Resource.success(response.body()?.data))
            }
        } catch (e: Exception){
            emit(Resource.error(e.toString()))
        }
    }
}
