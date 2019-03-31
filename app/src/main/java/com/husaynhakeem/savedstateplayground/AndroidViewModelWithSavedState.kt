package com.husaynhakeem.savedstateplayground

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.husaynhakeem.savedstateplayground.BaseViewModel.Companion.LIVE_DATE_KEY
import com.husaynhakeem.savedstateplayground.BaseViewModel.Companion.REGULAR_KEY


class AndroidViewModelWithSavedState(application: Application, private val savedStateHandle: SavedStateHandle) :
    AndroidViewModel(application),
    BaseViewModel {

    override val regularText: String
        get() = savedStateHandle.get<String>(REGULAR_KEY).orEmpty()

    override val liveDataText: LiveData<String> = savedStateHandle.getLiveData(LIVE_DATE_KEY)

    override fun saveRegularText(text: String) {
        savedStateHandle.set(REGULAR_KEY, text)
    }

    override fun saveLiveDataText(text: String) {
        savedStateHandle.set(LIVE_DATE_KEY, text)
        Log.d("Log", "AndroidViewModelWithSavedState application: ${getApplication<Application>()}")
    }
}