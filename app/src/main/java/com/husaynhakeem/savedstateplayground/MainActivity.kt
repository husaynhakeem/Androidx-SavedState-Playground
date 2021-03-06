package com.husaynhakeem.savedstateplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModelWithSavedState()
        setupView()
    }

    private fun setupView() {
        viewModel.liveDataText.observe(this, Observer { text ->
            mainSavedLiveDataTextTextView.text = text
        })
        mainSavedRegularTextTextView.text = viewModel.regularText
        mainRegularTextButton.setOnClickListener {
            viewModel.saveRegularText(mainEditText.text.toString())
            mainEditText.text = null
            mainSavedRegularTextTextView.text = viewModel.regularText
        }
        mainLiveDataTextButton.setOnClickListener {
            viewModel.saveLiveDataText(mainEditText.text.toString())
            mainEditText.text = null
        }
    }

    private fun setupViewModelWithSavedState() {
        val factory = SavedStateViewModelFactory(application, this)
        viewModel = ViewModelProviders.of(this, factory).get(ViewModelWithSavedState::class.java)
    }

    private fun setupViewModelWithoutSavedState() {
        viewModel = ViewModelProviders.of(this).get(ViewModelWithoutSavedState::class.java)
    }

    private fun setupAndroidViewModelWithSavedState() {
        val factory = SavedStateViewModelFactory(application, this, null)
        viewModel = ViewModelProvider(this, factory).get(AndroidViewModelWithSavedState::class.java)
    }

    private fun setupViewModelWithSavedStateAndDefaultState() {
        val defaultState = ViewModelWithSavedStateAndDefaultState.getDefaultState()
        val factory = SavedStateViewModelFactory(application, this, defaultState)
        viewModel =
            ViewModelProvider(this, factory).get(ViewModelWithSavedStateAndDefaultState::class.java)
    }

    private fun setupViewModelWithSavedStateAndConstructorWithParameters() {
        val classAInstance = ClassA()
        val classBInstance = ClassB()
        val factory =
            ViewModelWithSavedStateAndConstructorWithParametersFactory(
                this,
                null,
                classAInstance,
                classBInstance
            )
        viewModel =
            ViewModelProviders.of(this, factory)
                .get(ViewModelWithSavedStateAndConstructorWithParameters::class.java)
    }
}
