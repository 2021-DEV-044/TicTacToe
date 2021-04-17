package com.jorenjamar.tictactoe

class TicTacToe(boardHeight: Int, boardWidth:Int, private val amountOfPlayers:Int, private val amountToWin:Int) : IGame{
    private var field : Array<IntArray> = Array(boardHeight)
    {
        IntArray(boardWidth) {-1}
    }

    override fun selectNextPlayer(): Int {
        var amountOfPlayedFields = 0
        field.forEach {
            amountOfPlayedFields += it.filter { it >= 0 }.size
        }
        return amountOfPlayedFields % amountOfPlayers
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
        for(y in 0 until field.size){
            for(x in 0 until field[y].size){
                if(field[y][x] >= 0 && (horizontalWin(x,y) || verticalWin(x,y) || diagonalRightWin(x,y) || diagonalLeftWin(x,y))) return GameState.WINNER
            }
        }
        var amountOfPlayedFields = 0
        field.forEach {
            amountOfPlayedFields += it.filter { it >= 0 }.size
        }
        if(amountOfPlayedFields == (field.size * field[0].size)){
            return GameState.TIE
        }
        else{
            return GameState.CONTINUE
        }
    }

    override fun reset() {
        field.forEach {
            it.fill(-1)
        }
    }

    private fun horizontalWin(x: Int, y: Int) : Boolean{
        if(x + amountToWin > field[y].size) return false
        for(i in x until (x + amountToWin - 1)){
            if(field[y][i] != field[y][i+1]) return false
        }
        return true
    }

    private fun verticalWin(x: Int, y: Int) : Boolean{
        if(y + amountToWin > field.size) return false
        for(i in 0 until (y + amountToWin - 1)){
            if(field[i][x] != field[i+1][x]) return false
        }
        return true
    }

    private fun diagonalRightWin(x: Int, y: Int) : Boolean{
        if(x + amountToWin > field[y].size || y + amountToWin > field.size) return false
        for(i in 0 until amountToWin-1){
            if(field[y+i][x+i] != field[y+i+1][x+i+1]) return false
        }
        return true
    }

    private fun diagonalLeftWin(x: Int, y: Int) : Boolean{
        if(x - (amountToWin-1) < 0 || y + amountToWin > field.size) return false
        for(i in 0 until amountToWin-1){
            if(field[y+i][x-i] != field[y+i+1][x-i-1]) return false
        }
        return true
    }
}