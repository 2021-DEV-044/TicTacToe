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
    lateinit var players : Array<Player>
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
        players = arrayOf(Player("X"), Player("O"))
        //tvGameState.text = "It's ${players[ttt.selectNextPlayer()].name}'s turn"
        tvGameState.text = getString(R.string.turn, players[ttt.selectNextPlayer()].name)
    }

    var listenerField = View.OnClickListener { view ->
        val regexRow = Regex(getString(R.string.regex_row))
        val regexCol = Regex(getString(R.string.regex_column))
        var row = (regexRow.find(view.getTag().toString()))!!.groups[1]!!.value.toInt()
        var col = (regexCol.find(view.getTag().toString()))!!.groups[1]!!.value.toInt()

        var player =  ttt.selectNextPlayer();
        if(ttt.makeMove(player, col, row)){
            (view as Button).text = players[player].name
            (view as Button).isEnabled = false
            when(ttt.gameState()){
                GameState.WINNER -> winner(players[player].name)
                GameState.TIE -> tvGameState.text = getString(R.string.tie)
                GameState.CONTINUE -> tvGameState.text = getString(R.string.turn, players[ttt.selectNextPlayer()].name)
            }
        }
    }

    var listenerReset = View.OnClickListener { view ->
        ttt.reset()
        resetAllPlayingButtons()
        tvGameState.text = getString(R.string.turn, players[ttt.selectNextPlayer()].name)
    }

    fun resetAllPlayingButtons(){
        llField.children.forEach { row ->
            if (row is LinearLayout) {
                row.children.forEach { button ->
                    if (button is Button) {
                        button.text = ""
                        button.isEnabled = true
                    }
                }
            }
        }
    }

    fun winner(player: String){
        tvGameState.text = getString(R.string.winner, player)
        llField.children.forEach { row ->
            if (row is LinearLayout) {
                row.children.forEach { button ->
                    if (button is Button) {
                        button.isEnabled = false
                    }
                }
            }
        }
    }
}