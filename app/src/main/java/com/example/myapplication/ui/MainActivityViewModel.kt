package com.example.myapplication.ui

import androidx.lifecycle.*
import com.example.myapplication.data.model.MainResponse
import com.example.myapplication.data.network.Resource
import com.example.myapplication.data.repository.MainRepository
import com.example.myapplication.utiliity.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: MainRepository) : ViewModel(),
    DefaultLifecycleObserver {


    private val _loginResponse = SingleLiveEvent<Resource<MainResponse>>()
    val loginResponse: LiveData<Resource<MainResponse>>
        get() = _loginResponse

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        callApiLogin()
    }

    fun callApiLogin(pageN: String="1") {
        _loginResponse.value = Resource.loading()
        viewModelScope.launch {
            _loginResponse.value = repository.callApiLogin(pageN)
        }
    }
}