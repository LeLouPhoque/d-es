package com.example.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.demo.ui.theme.DemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiceGameScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DiceGameScreen(modifier: Modifier = Modifier) {
    val viewModel: DiceGameViewModel = viewModel() // Appel correct de viewModel()

    val player1Score by viewModel.player1Score.observeAsState(0)
    val player2Score by viewModel.player2Score.observeAsState(0)
    val player1Lancer by viewModel.player1lancer.observeAsState(0)
    val player2Lancer by viewModel.player2Lancer.observeAsState(0)

    val currentDiceRoll by viewModel.currentDiceRoll.observeAsState(1)

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = getDiceImageResource(currentDiceRoll)),
            contentDescription = "Dice Roll",
            modifier = Modifier.size(100.dp)
        )

        Text(text = "Lancé(s) total : ${player1Lancer + player2Lancer}")
        Text(text = "Score Joueur 1 : $player1Score / $player1Lancer lancé(s)")
        Text(text = "Score Joueur 2 : $player2Score / $player2Lancer lancé(s)")

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = { viewModel.rollDice(player = 1) }) {
                Text(text = "Joueur 1 Lance")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { viewModel.rollDice(player = 2) }) {
                Text(text = "Joueur 2 Lance")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.resetGame() }) {
            Text(text = "Remettre à zéro")
        }
    }
}


fun getDiceImageResource(diceRoll: Int): Int {
    return when (diceRoll) {
        1 -> R.drawable.d1
        2 -> R.drawable.d2
        3 -> R.drawable.d3
        4 -> R.drawable.d4
        5 -> R.drawable.d5
        6 -> R.drawable.d6
        else -> R.drawable.d1
    }
}