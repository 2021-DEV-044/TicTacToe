package com.jorenjamar.tictactoe

import org.junit.Assert.*
import org.junit.Test

class TicTacToeTest{

    //Test if all positions on the board start as 0
    @Test
    fun testInitialStateOfBoard(){
        println("Test if all positions on the boards start as 0:")
        var ttt : IGame = TicTacToe(3,3,2)
        for(i in 0..2){
            for(j in 0..2){
                print("- Test if ($i, $j) is 0: ")
                assertEquals(ttt.getStateOfPositon(i,j), 0)
                println("OK")
            }
        }
    }
}