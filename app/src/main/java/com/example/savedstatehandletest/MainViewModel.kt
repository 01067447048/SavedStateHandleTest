package com.example.savedstatehandletest

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

/**
 * Created by jaehyeon.
 * Date: 2023/07/04
 */
class MainViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val color = savedStateHandle.getStateFlow("color", 0xFFFFFFFF)
    val name = savedStateHandle.getStateFlow("name", "")

//    private val _name = MutableStateFlow("")
//    val name = _name.asStateFlow()

    fun updateName(_name: String) {
        savedStateHandle["name"] = _name
//        this._name.value = _name
    }

    fun updateBackgroundColor() {
        val newColor = Random.nextLong(0xFFFFFFFF)
        savedStateHandle["color"] = newColor
    }
}