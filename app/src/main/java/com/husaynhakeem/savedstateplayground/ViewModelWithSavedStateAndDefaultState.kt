package com.husaynhakeem.savedstateplayground

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.husaynhakeem.savedstateplayground.BaseViewModel.Companion.LIVE_DATE_KEY
import com.husaynhakeem.savedstateplayground.BaseViewModel.Companion.REGULAR_KEY


class ViewModelWithSavedStateAndDefaultState(private val savedStateHandle: SavedStateHandle) : ViewModel(),
    BaseViewModel {

    override val regularText: String
        get() = savedStateHandle.get<String>(REGULAR_KEY).orEmpty()

    override val liveDataText: LiveData<String> = savedStateHandle.getLiveData(LIVE_DATE_KEY)

    override fun saveRegularText(text: String) {
        savedStateHandle.set(REGULAR_KEY, text)
    }

    override fun saveLiveDataText(text: String) {
        savedStateHandle.set(LIVE_DATE_KEY, text)
    }

    companion object {
        fun getDefaultState(): Bundle {
            return Bundle().apply {
                putString(REGULAR_KEY, "Default regular text")
                putString(LIVE_DATE_KEY, "Default live data text")
            }
        }
    }
}