package de.amirrocker.testdagger2modules.base.presentation.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class KViewModelUtil @Inject constructor() {

    fun <T : ViewModel?> createFor(viewModel: T): ViewModelProvider.Factory? {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(viewModel as Class<*>)) {
                    return viewModel as T
                }
                throw IllegalArgumentException("unexpected viewModel Class ")
            }
        }
    }

}