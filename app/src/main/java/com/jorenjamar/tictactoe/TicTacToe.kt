package com.jorenjamar.tictactoe

class TicTacToe(boardHeight: Int, boardWidth:Int, val amountOfPlayers:Int) : IGame{
    private var field : Array<IntArray> = Array(boardHeight)
    {
        IntArray(boardWidth) {-1}
    }

    override fun selectNextPlayer(): Int {
        TODO("Not yet implemented")
    }

    override fun makeMove(player: Int, x: Int, y: Int) : Boolean {
        if(y < field.size && x < field[0].size && field[y][x] < 0){
            field[y][x] = player
            return true
        }
        return false
    }

    override fun getStateOfPositon(x: Int, y: Int): Int {
        return field[y][x]
    }

    override fun gameState(): GameState {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }
}