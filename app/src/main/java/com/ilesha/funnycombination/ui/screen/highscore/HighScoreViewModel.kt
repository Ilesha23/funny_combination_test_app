package com.ilesha.funnycombination.ui.screen.highscore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilesha.funnycombination.domain.model.GameResult
import com.ilesha.funnycombination.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HighScoreViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val scores = MutableStateFlow(emptyList<GameResult>())

    init {
        viewModelScope.launch {
            scores.update {
                repository.getAllResults()
            }
        }
    }

}