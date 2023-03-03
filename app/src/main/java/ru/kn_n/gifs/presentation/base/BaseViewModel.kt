package ru.kn_n.gifs.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.kn_n.gifs.utils.Resource

abstract class BaseViewModel : ViewModel() {

    fun <T> requestWithLiveData(
        liveData: MutableLiveData<Resource<T>>,
        response: suspend () -> T
    ) {
        this.viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(Resource.loading(data = null))
            try {
                val data = response.invoke()
                data?.let { liveData.postValue(Resource.success(data)) }
                    ?: liveData.postValue(Resource.error(data = null, message = "Error Occurred!"))
            } catch (e: Exception) {
                liveData.postValue(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
            }
        }
    }

    inline fun <T> launchPagingAsync(
        crossinline execute: suspend () -> Flow<T>,
        crossinline onSuccess: (Flow<T>) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val result = execute()
                onSuccess(result)
            } catch (ex: Exception) {
                error(ex.message ?: "Error")
            }
        }
    }
}