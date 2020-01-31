package com.husaynhakeem.savedstateplayground

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.husaynhakeem.savedstateplayground.BaseViewModel.Companion.LIVE_DATE_KEY
import com.husaynhakeem.savedstateplayground.BaseViewModel.Companion.REGULAR_KEY


class ViewModelWithSavedStateAndConstructorWithParameters(
    private val savedStateHandle: SavedStateHandle,
    private val classAInstance: ClassA,
    private val classBInstance: ClassB
) : ViewModel(), BaseViewModel {

    override val regularText: String
        get() = savedStateHandle.get<String>(REGULAR_KEY).orEmpty()

    override val liveDataText: LiveData<String> = savedStateHandle.getLiveData(LIVE_DATE_KEY)

    override fun saveRegularText(text: String) {
        savedStateHandle.set(REGULAR_KEY, text)
    }

    override fun saveLiveDataText(text: String) {
        savedStateHandle.set(LIVE_DATE_KEY, text)
    }
}

class ClassA

class ClassB

class ViewModelWithSavedStateAndConstructorWithParametersFactory(
    owner: SavedStateRegistryOwner,
    defaultState: Bundle?,
    private val classAInstance: ClassA,
    private val classBInstance: ClassB
) : AbstractSavedStateViewModelFactory(owner, defaultState) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return ViewModelWithSavedStateAndConstructorWithParameters(
            handle,
            classAInstance,
            classBInstance
        ) as T
    }
}