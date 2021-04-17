package com.jorenjamar.tictactoe

import org.junit.Assert.*
import org.junit.Test

class TicTacToeTest{
    //Test if all positions on the board start as -1
    @Test
    fun testInitialStateOfBoard(){
        println("Test if all positions on the boards start as -1:")
        var ttt : IGame = TicTacToe(3,3,2,3)
        for(i in 0..2){
            for(j in 0..2){
                print("- Test if ($i, $j) is -1: ")
                assertEquals(ttt.getStateOfPositon(i,j), -1)
                println("OK")
            }
        }
        println()
    }

    //Test if the function makeMove() works
    @Test
    fun testMakeMove(){
        println("Test if you can make a move:")
        var ttt : IGame = TicTacToe(3,3,2,3)
        ttt.makeMove(1,0,0)
        ttt.makeMove(1,2,0)
        ttt.makeMove(0,1,1)
        ttt.makeMove(0,2,2)

        print("- Test if (0,0) has become 1: ")
        assertEquals(ttt.getStateOfPositon(0,0), 1)
        println("OK")
        print("- Test if (2,0) has become 1: ")
        assertEquals(ttt.getStateOfPositon(2,0), 1)
        println("OK")
        print("- Test if (1,1) has become 0: ")
        assertEquals(ttt.getStateOfPositon(1,1), 0)
        println("OK")
        print("- Test if (2,2) has become 0: ")
        assertEquals(ttt.getStateOfPositon(2,2), 0)
        println("OK")
        println()
    }

    //Test if a move on a already played field does not change it state
    @Test
    fun testMakeMoveAlreadyPlayed(){
        println("Test if a move on a already played field does not change it state:")
        var ttt : IGame = TicTacToe(3,3,2,3)
        ttt.makeMove(1,0,0)
        ttt.makeMove(1,2,0)
        ttt.makeMove(0,1,1)
        ttt.makeMove(0,2,2)

        ttt.makeMove(0,0,0)
        ttt.makeMove(0,2,0)
        ttt.makeMove(1,1,1)
        ttt.makeMove(1,2,2)

        print("- Test if (0,0) is still 1: ")
        assertEquals(ttt.getStateOfPositon(0,0), 1)
        println("OK")
        print("- Test if (2,0) is still 1: ")
        assertEquals(ttt.getStateOfPositon(2,0), 1)
        println("OK")
        print("- Test if (1,1) is still 0: ")
        assertEquals(ttt.getStateOfPositon(1,1), 0)
        println("OK")
        print("- Test if (2,2) is still 0: ")
        assertEquals(ttt.getStateOfPositon(2,2), 0)
        println("OK")
        println()
    }

    //Test if valid a move returns true
    @Test
    fun testMakeMoveReturnsTrue(){
        println("Test if a valid move return true:")
        var ttt : IGame = TicTacToe(3,3,2,3)

        print("- Test if move on (0,0) returns true: ")
        assertTrue(ttt.makeMove(1,0,0))
        println("OK")
        print("- Test if move on (2,0) returns true: ")
        assertTrue(ttt.makeMove(1,2,0))
        println("OK")
        print("- Test if move on (1,1) returns true: ")
        assertTrue(ttt.makeMove(0,1,1))
        println("OK")
        print("- Test if move on (2,2) returns true: ")
        assertTrue(ttt.makeMove(0,2,2))
        println("OK")
        println()
    }

    //Test if a move on a already played field makes makeMove return false
    @Test
    fun testMakeMoveAlreadyPlayedReturnsFalse(){
        println("Test if a move on a already played field makes makeMove return false:")
        var ttt : IGame = TicTacToe(3,3,2,3)
        ttt.makeMove(1,0,0)
        ttt.makeMove(1,2,0)
        ttt.makeMove(0,1,1)
        ttt.makeMove(0,2,2)

        print("- Test if move on (0,0) returns false: ")
        assertFalse(ttt.makeMove(0,0,0))
        println("OK")
        print("- Test if move on (2,0) returns false: ")
        assertFalse(ttt.makeMove(1,2,0))
        println("OK")
        print("- Test if move on (1,1) returns false: ")
        assertFalse(ttt.makeMove(0,1,1))
        println("OK")
        print("- Test if move on (2,2) returns false: ")
        assertFalse(ttt.makeMove(1,2,2))
        println("OK")
        println()
    }

    //Test if a move outside of the field returns false
    @Test
    fun testMakeMoveOutsideOfFieldReturnsFalse(){
        println("Test if a move outside of the field makes makeMove return false:")
        var ttt : IGame = TicTacToe(3,2,2,3)
        ttt.makeMove(1,0,0)
        ttt.makeMove(1,2,0)
        ttt.makeMove(0,1,1)
        ttt.makeMove(0,2,2)

        print("- Test if move on (2,0) returns false: ")
        assertFalse(ttt.makeMove(1,2,0))
        println("OK")
        print("- Test if move on (1,3) returns false: ")
        assertFalse(ttt.makeMove(1,1,3))
        println("OK")
        print("- Test if move on (3,3) returns false: ")
        assertFalse(ttt.makeMove(1,3,3))
        println("OK")
        println()
    }

    //Test if next player alternates after a move is made
    @Test
    fun testSelectNextPlayer(){
        println("Test if select player works")
        var ttt : IGame = TicTacToe(3,3,2,3)
        print("- player 0: ")
        assertEquals(ttt.selectNextPlayer(), 0)
        println("OK")
        ttt.makeMove(ttt.selectNextPlayer(), 0,0)
        print("- player 1: ")
        assertEquals(ttt.selectNextPlayer(), 1)
        println("OK")
        ttt.makeMove(ttt.selectNextPlayer(), 1,0)
        print("- player 0: ")
        assertEquals(ttt.selectNextPlayer(), 0)
        println("OK")
        ttt.makeMove(ttt.selectNextPlayer(), 1,1)
        print("- player 1: ")
        assertEquals(ttt.selectNextPlayer(), 1)
        println("OK")
        ttt.makeMove(ttt.selectNextPlayer(), 1,2)
        print("- player 0: ")
        assertEquals(ttt.selectNextPlayer(), 0)
        println("OK")
        print("- no move, still player 0: ")
        assertEquals(ttt.selectNextPlayer(), 0)
        println("OK")
    }

    //Test if reset works
    @Test
    fun testReset(){
        println("Test reset:")
        var ttt : IGame = TicTacToe(3,3,2,3)
        ttt.makeMove(1,0,0)
        ttt.makeMove(1,2,0)
        ttt.makeMove(0,1,1)
        ttt.makeMove(0,2,2)

        ttt.reset();

        for(i in 0..2){
            for(j in 0..2){
                print("- Test if ($i, $j) is -1: ")
                assertEquals(ttt.getStateOfPositon(i,j), -1)
                println("OK")
            }
        }
    }

    //Test if gameState() returns continue
    @Test
    fun testGameStateContinue(){
        println("Test if gameState returns continue:")
        var ttt : IGame = TicTacToe(3,3,2,3)
        ttt.makeMove(0,0,0)
        ttt.makeMove(1,1,0)
        ttt.makeMove(0,2,0)
        ttt.makeMove(1,1,2)
        println("XOX")
        println("...")
        println(".O.")
        assertEquals(ttt.gameState(), GameState.CONTINUE);
        println("OK")
    }

    //Test if gameState() returns tie
    @Test
    fun testGameStateTie(){
        println("Test if gameState returns tie:")
        var ttt : IGame = TicTacToe(3,3,2,3)
        ttt.makeMove(1,0,0)
        ttt.makeMove(1,1,0)
        ttt.makeMove(0,2,0)
        ttt.makeMove(0,0,1)
        ttt.makeMove(1,1,1)
        ttt.makeMove(1,2,1)
        ttt.makeMove(1,0,2)
        ttt.makeMove(0,1,2)
        ttt.makeMove(0,2,2)
        println("OOX")
        println("XOO")
        println("OXX")
        assertEquals(ttt.gameState(), GameState.TIE);
        println("OK")
    }

    //Test if gameState() returns win
    @Test
    fun testGameStateWin(){
        println("Test if gameState returns win:")
        println("- Horizontal:")
        var ttt : IGame = TicTacToe(3,3,2,3)
        ttt.makeMove(0,0,0)
        ttt.makeMove(0,1,0)
        ttt.makeMove(0,2,0)
        ttt.makeMove(1,0,1)
        ttt.makeMove(1,1,2)
        println("  XXX")
        println("  O..")
        println("  .O.")
        assertEquals(ttt.gameState(), GameState.WINNER);
        println("  OK")


        println("- Vertical:")
        ttt.reset();
        ttt.makeMove(0,1,0)
        ttt.makeMove(0,1,1)
        ttt.makeMove(0,1,2)
        ttt.makeMove(1,0,0)
        ttt.makeMove(1,0,0)
        println("  OX.")
        println("  OX.")
        println("  .X.")
        assertEquals(ttt.gameState(), GameState.WINNER);
        println("  OK")

        println("- Diagonal right:")
        ttt.reset();
        ttt.makeMove(0,0,0)
        ttt.makeMove(0,1,1)
        ttt.makeMove(0,2,2)
        ttt.makeMove(1,3,0)
        ttt.makeMove(1,0,1)
        println("  X.O")
        println("  OX.")
        println("  ..X")
        assertEquals(ttt.gameState(), GameState.WINNER);
        println("  OK")

        println("- Diagonal left:")
        ttt.reset();
        ttt.makeMove(0,2,0)
        ttt.makeMove(0,1,1)
        ttt.makeMove(0,0,2)
        ttt.makeMove(1,3,0)
        ttt.makeMove(1,0,1)
        println("  O.X")
        println("  OX.")
        println("  X..")
        assertEquals(ttt.gameState(), GameState.WINNER);
        println("  OK")

        println("- Full board:")
        ttt.reset();
        ttt.makeMove(0,0,0)
        ttt.makeMove(1,1,0)
        ttt.makeMove(0,2,0)
        ttt.makeMove(0,0,1)
        ttt.makeMove(0,1,1)
        ttt.makeMove(1,2,1)
        ttt.makeMove(1,0,2)
        ttt.makeMove(1,1,2)
        ttt.makeMove(0,2,2)
        println("  XOX")
        println("  XXO")
        println("  OOX")
        assertEquals(ttt.gameState(), GameState.WINNER);
        println("  OK")
    }
}