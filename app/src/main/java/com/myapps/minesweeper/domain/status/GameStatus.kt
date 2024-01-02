package com.myapps.minesweeper.domain.status

class GameStatus(amountOfMines:Int){
    // true =  digMode, false = flagMode
    private var digFlagMode:Boolean = true
    private var maxAmountOfFlags:Int = amountOfMines
    private var amountOfFlags:Int
    private var gameWin:Boolean
    private var gameLose:Boolean

    init {
        this.amountOfFlags = amountOfMines
        gameWin = false
        gameLose = false
    }

    fun getDigFlagMode() = digFlagMode
    fun getAmountOfFlags() = amountOfFlags
    fun isWin() = gameWin
    fun isLose() = gameLose

    fun setDigMode(){digFlagMode = true}
    fun setFlagMode(){digFlagMode = false}
    fun setGameLose(){gameLose = true}
    fun setGameWin(){gameWin = true}
    fun increaseNumberOfFlags(){
       if(amountOfFlags<maxAmountOfFlags){
           amountOfFlags += 1
       }
    }
    fun decreaseNumberOfFlags(){
       if(amountOfFlags>0){
           amountOfFlags -= 1
       }
    }
    fun reset(){
        digFlagMode = true
        gameWin = false
        gameLose = false
        amountOfFlags = maxAmountOfFlags
    }
}