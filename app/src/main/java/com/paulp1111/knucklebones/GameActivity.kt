package com.paulp1111.knucklebones
//imports
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

    private val playerColumnScores = IntArray(3) { 0 }
    private val computerColumnScores = IntArray(3) { 0 }

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
            diceResult.text = if (isPlayerTurn) "Spieler 1 ist dran" else "Spieler 2 ist dran"
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

        if (isPlayer) {
            playerColumnScores[column] = calculateColumnScore(column, playerCells)
        } else {
            computerColumnScores[column] = calculateColumnScore(column, computerCells)
        }
        updateScores()
    }
// Changes:
// Berechnet die Punktzahl für die angeklickte Spalte
// wegen dem problem mit der Rechenleistung anstatt alle Zellen neu zu berechnen,

    private fun updateScores() {
        val playerScore = playerColumnScores.sum()
        val computerScore = computerColumnScores.sum()
        playerScoreText.text = "Punkte: $playerScore"
        computerScoreText.text = "Punkte: $computerScore"
    }

    private fun calculateColumnScore(column: Int, cells: Array<Button>): Int {
        var columnScore = 0
        val countMap = mutableMapOf<Int, Int>()

        // Werte im aktuellen Spalte sammeln und zählen
        for (i in column until cells.size step 3) {
            val value = cells[i].text.toString().toIntOrNull()
            if (value != null) {
                countMap[value] = countMap.getOrDefault(value, 0) + 1
            }
        }

        // Punktzahl berechnen
        for ((num, count) in countMap) {
            columnScore += num * count * count
        }
        return columnScore
    }

    private fun switchTurn() {
        isPlayerTurn = !isPlayerTurn
        diceResult.text = if (isPlayerTurn) "Spieler 1 ist dran" else "Spieler 2 ist dran"
    }
}
