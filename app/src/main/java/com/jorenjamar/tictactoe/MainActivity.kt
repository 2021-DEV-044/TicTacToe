package com.jorenjamar.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
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
        field.children.forEach { row ->
            if(row is LinearLayout){
                rows++;
                row.children.forEach { button ->
                    if(button is Button){
                        columns++;
                        button.setOnClickListener(listenerField)
                    }
                }
            }
        }
        ttt = TicTacToe(rows, columns, amountOfPlayers, amountToWin)
    }

    var listenerField = View.OnClickListener { view ->
        val regexRow = Regex("R(\\d+)")
        val regexCol = Regex("C(\\d+)")
        var row = (regexRow.find(view.getTag().toString()))!!.groups[1]!!.value.toInt()
        var col = (regexCol.find(view.getTag().toString()))!!.groups[1]!!.value.toInt()

        var player =  ttt.selectNextPlayer();
        if(ttt.makeMove(player, col, row)) (view as Button).text = player.toString()
    }


}