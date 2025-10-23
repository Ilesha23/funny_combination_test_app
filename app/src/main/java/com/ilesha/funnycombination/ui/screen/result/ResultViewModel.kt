package com.ilesha.funnycombination.ui.screen.result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.ilesha.funnycombination.ui.navigation.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val score = savedStateHandle.toRoute<Result>().result
    val isNewRecord = savedStateHandle.toRoute<Result>().isNewRecord

}