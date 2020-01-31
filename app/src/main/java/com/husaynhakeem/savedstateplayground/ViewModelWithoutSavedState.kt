package com.husaynhakeem.savedstateplayground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ViewModelWithoutSavedState : ViewModel(), BaseViewModel {

    private var _regularText = ""

    override val regularText: String
        get() = _regularText

    private val _liveDataText = MutableLiveData<String>().apply { value = "" }

    override val liveDataText: LiveData<String>
        get() = _liveDataText

    override fun saveRegularText(text: String) {
        _regularText = text
    }

    override fun saveLiveDataText(text: String) {
        _liveDataText.postValue(text)
    }
}