package com.example.demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class DiceGameViewModel : ViewModel() {
    private val _player1Score = MatuableState(0)
    val player1Score: LiveData<Int> = _player1Score

    private val _player2Score = MutableLiveData(0)
    val player2Score: LiveData<Int> = _player2Score

    private val _currentDiceRoll = MutableLiveData(1)
    val currentDiceRoll: LiveData<Int> = _currentDiceRoll

    private val _player1Lancer = MutableLiveData(0)
    val player1lancer: LiveData<Int> = _player1Lancer

    private val _player2Lancer = MutableLiveData(0)
    val player2Lancer: LiveData<Int> = _player2Lancer

    fun rollDice(player: Int) {
        val diceRoll = Random.nextInt(1, 7)
        _currentDiceRoll.value = diceRoll
        if (player == 1) {
            _player1Score.value = (_player1Score.value ?: 0) + diceRoll
            _player1Lancer.value = (_player1Lancer.value ?: 0) + 1

        } else {
            _player2Score.value = (_player2Score.value ?: 0) + diceRoll
            _player2Lancer.value = (_player2Lancer.value ?: 0) + 1
        }
    }

    fun resetGame() {
        _player1Score.value = 0
        _player1Lancer.value = 0
        _player2Score.value = 0
        _player2Score.value = 0
        _currentDiceRoll.value = 1
    }
}