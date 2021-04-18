package com.jorenjamar.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
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

        //add listener to all play buttons
        llField.children.forEach { row ->
            if (row is LinearLayout) {
                rows++;
                row.children.forEach { button ->
                    if (button is ImageButton) {
                        amountOfPositions++;
                        button.setOnClickListener(listenerField)
                    }
                }
            }
        }
        columns = amountOfPositions/rows

        //add listener to reset button
        btnReset.setOnClickListener(listenerReset)

        //initialize game
        ttt = TicTacToe(rows, columns, amountOfPlayers, amountToWin)
        players = arrayOf(Player("X", R.drawable.player_x), Player("O", R.drawable.player_o))
        tvGameState.text = getString(R.string.turn, players[ttt.selectNextPlayer()].name)

        btn1.setImageResource(R.drawable.player_o)
        btn1.setImageResource(android.R.color.transparent)
    }

    //to work the tag of all play buttons must be of type RyCx with y the number of the row (starting from 0) and x the number of the column (starting from 0)
    var listenerField = View.OnClickListener { view ->
        val regexRow = Regex(getString(R.string.regex_row))
        val regexCol = Regex(getString(R.string.regex_column))
        var row = (regexRow.find(view.getTag().toString()))!!.groups[1]!!.value.toInt()
        var col = (regexCol.find(view.getTag().toString()))!!.groups[1]!!.value.toInt()

        var player =  ttt.selectNextPlayer();
        if(ttt.makeMove(player, col, row)){
            (view as ImageButton).setImageResource(players[player].image)
            (view as ImageButton).isEnabled = false
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

    //make all playbuttons empty and enabled
    fun resetAllPlayingButtons(){
        llField.children.forEach { row ->
            if (row is LinearLayout) {
                row.children.forEach { button ->
                    if (button is ImageButton) {
                        button.setImageResource(android.R.color.transparent)
                        button.isEnabled = true
                    }
                }
            }
        }
    }

    fun winner(player: String){
        tvGameState.text = getString(R.string.winner, player)
        
        //disable all play buttons
        llField.children.forEach { row ->
            if (row is LinearLayout) {
                row.children.forEach { button ->
                    if (button is ImageButton) {
                        button.isEnabled = false
                    }
                }
            }
        }
    }

    //save game state when acitivity state changes
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("ttt", ttt)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState.getParcelable<TicTacToe>("ttt") != null){
            ttt = savedInstanceState.getParcelable<TicTacToe>("ttt")!!
        }
    }
}