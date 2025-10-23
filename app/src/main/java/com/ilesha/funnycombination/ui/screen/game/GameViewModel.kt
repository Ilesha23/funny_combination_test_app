package com.ilesha.funnycombination.ui.screen.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilesha.funnycombination.data.model.GameEmoji
import com.ilesha.funnycombination.data.model.GameState
import com.ilesha.funnycombination.domain.model.GameResult
import com.ilesha.funnycombination.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var demonstratingJob: Job? = null

    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()

    private val _navigateToResults = MutableStateFlow(false)
    val navigateToResults = _navigateToResults.asStateFlow()

    private val currentTopScore = repository.getTopResult()
        .map { it?.score ?: -1 }
        .stateIn(viewModelScope, SharingStarted.Eagerly, -1)

    val isNewRecord = MutableStateFlow(false)

    init {
        startNewLevel()
    }

    fun startNewLevel() {
        demonstratingJob?.cancel()
        _state.update { currentState ->
            currentState.copy(
                level = currentState.level + 1,
                sequence = generateSequence(currentState.level + 1),
                isDemonstrating = true,
                playerInput = emptyList(),
                isGameOver = false
            )
        }
        demonstratingJob = viewModelScope.launch {
            delay(DEMO_DELAY_MS) // pause before start
            _state.value.sequence.forEachIndexed { index, emoji ->
                _state.update { it.copy(currentDemonstratingItem = emoji) }
                delay(ITEM_VISIBLE_MS)
                _state.update { it.copy(currentDemonstratingItem = null) }
                if (index != _state.value.sequence.lastIndex) delay(DEMO_DELAY_MS)
            }
            _state.update { it.copy(isDemonstrating = false) }
        }
    }

    fun handleInput(clickedEmoji: GameEmoji) {
        val currentState = _state.value
        if (currentState.isDemonstrating or currentState.isGameOver) return

        val newPlayerInput = currentState.playerInput + clickedEmoji
        val nextIndex = newPlayerInput.lastIndex

        _state.update {
            it.copy(
                playerInput = newPlayerInput,
                currentDemonstratingItem = clickedEmoji
            )
        }

        handleIncorrectInput(
            clickedEmoji = clickedEmoji,
            currentState = currentState,
            nextIndex = nextIndex
        )

        handleCorrectInput(
            newPlayerInput = newPlayerInput,
            currentState = currentState
        )

    }

    fun handleIncorrectInput(
        clickedEmoji: GameEmoji,
        currentState: GameState,
        nextIndex: Int
    ) {
        if (nextIndex > currentState.sequence.lastIndex) return
        if (clickedEmoji != currentState.sequence[nextIndex]) {
            viewModelScope.launch {
                val finalScore = currentState.level - 1
                isNewRecord.update { finalScore > currentTopScore.value }
                saveResult(currentState.level)
                delay(ITEM_VISIBLE_MS)
                _state.update {
                    it.copy(
                        isGameOver = true,
                        currentDemonstratingItem = null
                    )
                }
                delay(GAME_OVER_DELAY)
                _navigateToResults.update { true }
            }
            return
        }
    }

    fun handleCorrectInput(
        newPlayerInput: List<GameEmoji>,
        currentState: GameState
    ) {
        if (newPlayerInput.size == currentState.sequence.size) {
            if (newPlayerInput.last() == currentState.sequence.last()) {
                viewModelScope.launch {
                    delay(DEMO_DELAY_MS)
                    _state.update { it.copy(currentDemonstratingItem = null) }
                    startNewLevel()
                }
            }
        }
    }

    suspend fun saveResult(level: Int) {
        repository.saveResult(
            GameResult(
                score = level - 1,
                date = LocalDate.now()
            )
        )
    }

    fun generateSequence(level: Int): List<GameEmoji> {
        return List(level) {
            GameEmoji.entries.random()
        }
    }

    companion object {
        private const val DEMO_DELAY_MS = 1000L
        private const val ITEM_VISIBLE_MS = 1000L
        private const val GAME_OVER_DELAY = 2000L
    }

}