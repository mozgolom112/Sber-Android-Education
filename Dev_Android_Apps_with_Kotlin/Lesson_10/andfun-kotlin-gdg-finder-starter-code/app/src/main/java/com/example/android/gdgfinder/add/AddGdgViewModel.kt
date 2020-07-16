package com.example.android.gdgfinder.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;

class AddGdgViewModel : ViewModel() {

    var showSnackbarEvent = MutableLiveData<Boolean?>()

    fun doneShowingSnackbar() {
        showSnackbarEvent.value = null
    }

    fun onSubmitApplication() {
        showSnackbarEvent.value = true
    }
}
