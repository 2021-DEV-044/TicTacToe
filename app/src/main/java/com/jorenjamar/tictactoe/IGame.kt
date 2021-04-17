package com.jorenjamar.tictactoe

enum class GameState(){
    WINNER, TIE, CONTINUE
}

interface IGame {
    fun selectNextPlayer() : Int
    fun makeMove(player: Int, x:Int, y:Int)
    fun getStateOfPositon(x:Int, y:Int) : Int
    fun gameState(): GameState
    fun reset()
}