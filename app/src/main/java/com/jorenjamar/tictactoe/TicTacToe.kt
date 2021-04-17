package com.jorenjamar.tictactoe

class TicTacToe(boardHeight: Int, boardWidth:Int, private val amountOfPlayers:Int, private val amountToWin:Int) : IGame{

    /*the playing field with:
      - -1 for no move made on this position
      - a positive number for a move which refers to a certain player
    *?
     */
    private var field : Array<IntArray> = Array(boardHeight)
    {
        IntArray(boardWidth) {-1}
    }

    //return the player (as an integer) who can make the next move
    override fun selectNextPlayer(): Int {
        var amountOfPlayedFields = 0
        field.forEach {
            amountOfPlayedFields += it.filter { it >= 0 }.size
        }
        return amountOfPlayedFields % amountOfPlayers
    }

    //puts a move on the field and returns false if this move is not possible
    override fun makeMove(player: Int, x: Int, y: Int) : Boolean {
        if(y < field.size && x < field[0].size && field[y][x] < 0){
            field[y][x] = player
            return true
        }
        return false
    }

    //returns the current state of certain position on the field
    override fun getStateOfPositon(x: Int, y: Int): Int {
        return field[y][x]
    }

    //returns the state of the game (WINNER, TIE or CONTINUE)
    override fun gameState(): GameState {
        for(y in 0 until field.size){
            for(x in 0 until field[y].size){
                //check if field is played and if the field is played, also check if there is a win
                if(field[y][x] >= 0 && (horizontalWin(x,y) || verticalWin(x,y) || diagonalRightWin(x,y) || diagonalLeftWin(x,y))) return GameState.WINNER
            }
        }
        var amountOfPlayedFields = 0
        field.forEach {
            amountOfPlayedFields += it.filter { it >= 0 }.size
        }

        //If all fields are played, it is a tie. Otherwise the game will continue.
        if(amountOfPlayedFields == (field.size * field[0].size)){
            return GameState.TIE
        }
        else{
            return GameState.CONTINUE
        }
    }

    //resets the field to all -1
    override fun reset() {
        field.forEach {
            it.fill(-1)
        }
    }

    //check if there are 3 of the same player moves in a row horizontally
    private fun horizontalWin(x: Int, y: Int) : Boolean{
        if(x + amountToWin > field[y].size) return false
        for(i in x until (x + amountToWin - 1)){
            if(field[y][i] != field[y][i+1]) return false
        }
        return true
    }

    //check if there are 3 of the same player moves in a row vertically
    private fun verticalWin(x: Int, y: Int) : Boolean{
        if(y + amountToWin > field.size) return false
        for(i in 0 until (y + amountToWin - 1)){
            if(field[i][x] != field[i+1][x]) return false
        }
        return true
    }

    //check if there are 3 of the same player moves in a row diagonal from top left to bottom right
    private fun diagonalRightWin(x: Int, y: Int) : Boolean{
        if(x + amountToWin > field[y].size || y + amountToWin > field.size) return false
        for(i in 0 until amountToWin-1){
            if(field[y+i][x+i] != field[y+i+1][x+i+1]) return false
        }
        return true
    }

    //check if there are 3 of the same player moves in a row diagonal from top right to bottom left
    private fun diagonalLeftWin(x: Int, y: Int) : Boolean{
        if(x - (amountToWin-1) < 0 || y + amountToWin > field.size) return false
        for(i in 0 until amountToWin-1){
            if(field[y+i][x-i] != field[y+i+1][x-i-1]) return false
        }
        return true
    }
}