package com.husaynhakeem.savedstateplayground

import androidx.lifecycle.LiveData


interface BaseViewModel {


    val regularText: String

    val liveDataText: LiveData<String>

    fun saveRegularText(text: String)

    fun saveLiveDataText(text: String)

    companion object {
        val REGULAR_KEY = "regular_key"
        val LIVE_DATE_KEY = "live_data_key"
    }
}