package com.husaynhakeem.savedstateplayground

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ViewModelWithoutSavedState : ViewModel(), BaseViewModel {

    private var _regularText = ""

    override val regularText: String
        get() = _regularText

    override val liveDataText = MutableLiveData<String>().apply { value = "" }

    override fun saveRegularText(text: String) {
        _regularText = text
    }

    override fun saveLiveDataText(text: String) {
        liveDataText.postValue(text)
    }
}