package com.husaynhakeem.savedstateplayground

import androidx.lifecycle.LiveData


interface BaseViewModel {


    open val regularText: String

    open val liveDataText: LiveData<String>

    open fun saveRegularText(text: String)

    open fun saveLiveDataText(text: String)

    companion object {
        val REGULAR_KEY = "regular_key"
        val LIVE_DATE_KEY = "live_data_key"
    }
}