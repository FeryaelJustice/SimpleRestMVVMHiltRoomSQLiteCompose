package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val settingsRepository: SettingsRepository) :
    ViewModel() {
    private val _isLogged: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val isLogged: LiveData<Boolean> get() = _isLogged

    fun setIsLogged(isLogged: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLogged.postValue(isLogged)
        }
    }
}