package com.jorenjamar.tictactoe

import org.junit.Assert.*
import org.junit.Test

class TicTacToeTest{

    //Test if all positions on the board start as -1
    @Test
    fun testInitialStateOfBoard(){
        println("Test if all positions on the boards start as -1:")
        var ttt : IGame = TicTacToe(3,3,2)
        for(i in 0..2){
            for(j in 0..2){
                print("- Test if ($i, $j) is -1: ")
                assertEquals(ttt.getStateOfPositon(i,j), -1)
                println("OK")
            }
        }
    }
}