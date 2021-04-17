package com.jorenjamar.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.children
import androidx.core.view.forEach
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var ttt : TicTacToe
    var amountOfPlayers = 2;
    var amountToWin = 3;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var rows = 0;
        var columns = 0;
        var amountOfPositions = 0
        llField.children.forEach { row ->
            if (row is LinearLayout) {
                rows++;
                row.children.forEach { button ->
                    if (button is Button) {
                        amountOfPositions++;
                        button.setOnClickListener(listenerField)
                    }
                }
            }
        }
        columns = amountOfPositions/rows
        btnReset.setOnClickListener(listenerReset)
        ttt = TicTacToe(rows, columns, amountOfPlayers, amountToWin)
        tvGameState.text = "It's ${ttt.selectNextPlayer()}'s turn"
    }

    var listenerField = View.OnClickListener { view ->
        val regexRow = Regex("R(\\d+)")
        val regexCol = Regex("C(\\d+)")
        var row = (regexRow.find(view.getTag().toString()))!!.groups[1]!!.value.toInt()
        var col = (regexCol.find(view.getTag().toString()))!!.groups[1]!!.value.toInt()

        var player =  ttt.selectNextPlayer();
        if(ttt.makeMove(player, col, row)){
            (view as Button).text = player.toString()
            when(ttt.gameState()){
                GameState.WINNER -> tvGameState.text = "$player is winner"
                GameState.TIE -> tvGameState.text = "it is a tie"
                GameState.CONTINUE -> tvGameState.text = "It's ${ttt.selectNextPlayer()}'s turn"
            }
        }
    }

    var listenerReset = View.OnClickListener { view ->
        ttt.reset()
        llField.children.forEach { row ->
            if (row is LinearLayout) {
                row.children.forEach { button ->
                    if (button is Button) {
                        button.text = ""
                    }
                }
            }
        }
        tvGameState.text = "It's ${ttt.selectNextPlayer()}'s turn"
    }
}