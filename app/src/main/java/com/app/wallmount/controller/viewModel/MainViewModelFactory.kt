package com.app.wallmount.controller.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.wallmount.repository.AppRepository
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository: AppRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}