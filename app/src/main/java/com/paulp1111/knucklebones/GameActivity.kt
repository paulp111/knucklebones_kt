package com.paulp1111.knucklebones

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var diceResult: TextView
    private lateinit var rollDiceButton: Button
    private var rolledValue: Int = 0
    private var isPlayerTurn = true

    private lateinit var playerCells: Array<Button>
    private lateinit var computerCells: Array<Button>
    private lateinit var playerScoreText: TextView
    private lateinit var computerScoreText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        diceResult = findViewById(R.id.diceResult)
        rollDiceButton = findViewById(R.id.rollDiceButton)
        playerScoreText = findViewById(R.id.playerScore)
        computerScoreText = findViewById(R.id.computerScore)

        playerCells = arrayOf(
            findViewById(R.id.playerCell_0_0), findViewById(R.id.playerCell_0_1), findViewById(R.id.playerCell_0_2),
            findViewById(R.id.playerCell_1_0), findViewById(R.id.playerCell_1_1), findViewById(R.id.playerCell_1_2),
            findViewById(R.id.playerCell_2_0), findViewById(R.id.playerCell_2_1), findViewById(R.id.playerCell_2_2)
        )
        computerCells = arrayOf(
            findViewById(R.id.computerCell_0_0), findViewById(R.id.computerCell_0_1), findViewById(R.id.computerCell_0_2),
            findViewById(R.id.computerCell_1_0), findViewById(R.id.computerCell_1_1), findViewById(R.id.computerCell_1_2),
            findViewById(R.id.computerCell_2_0), findViewById(R.id.computerCell_2_1), findViewById(R.id.computerCell_2_2)
        )

        rollDiceButton.setOnClickListener {
            if (rolledValue == 0) {
                rolledValue = Random.nextInt(1, 7)
                diceResult.text = "Würfelwert: $rolledValue"
            } else {
                diceResult.text = "Bitte platziere den aktuellen Wert zuerst!"
            }
        }

        for (cell in playerCells) {
            cell.setOnClickListener { onCellClicked(cell, isPlayer = true) }
        }
        for (cell in computerCells) {
            cell.setOnClickListener { onCellClicked(cell, isPlayer = false) }
        }
    }

    private fun onCellClicked(cell: Button, isPlayer: Boolean) {
        if ((isPlayer && isPlayerTurn) || (!isPlayer && !isPlayerTurn)) {
            if (cell.text.isEmpty() && rolledValue > 0) {
                cell.text = rolledValue.toString()
                handleColumnLogic(cell, isPlayer)
                rolledValue = 0
                updateScores()
                switchTurn()
            } else {
                diceResult.text = "Bitte wähle eine leere Zelle aus!"
            }
        } else {
            diceResult.text = if (isPlayerTurn) "Spieler ist dran" else "Computer ist dran"
        }
    }

    private fun handleColumnLogic(cell: Button, isPlayer: Boolean) {
        val index = if (isPlayer) playerCells.indexOf(cell) else computerCells.indexOf(cell)
        val column = index % 3
        val opponentCells = if (isPlayer) computerCells else playerCells

        for (i in column until opponentCells.size step 3) {
            if (opponentCells[i].text == rolledValue.toString()) {
                opponentCells[i].text = ""
                diceResult.text = "Gegnerischer Würfel entfernt!"
            }
        }
    }

    private fun updateScores() {
        val playerScore = calculateScore(playerCells)
        val computerScore = calculateScore(computerCells)
        playerScoreText.text = "Punkte: $playerScore"
        computerScoreText.text = "Punkte: $computerScore"
    }

    private fun calculateScore(cells: Array<Button>): Int {
        var score = 0
        for (col in 0..2) {
            var columnScore = 0
            var countMap = mutableMapOf<Int, Int>()

            for (i in col until cells.size step 3) {
                val value = cells[i].text.toString().toIntOrNull()
                if (value != null) {
                    countMap[value] = countMap.getOrDefault(value, 0) + 1
                }
            }

            for ((num, count) in countMap) {
                columnScore += num * count * count // Multiplikation
            }
            score += columnScore
        }
        return score
    }

    private fun switchTurn() {
        isPlayerTurn = !isPlayerTurn
        diceResult.text = if (isPlayerTurn) "Spieler ist dran" else "Computer ist dran"
    }

}
