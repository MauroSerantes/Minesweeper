package com.myapps.minesweeper.utils

class GameDetails(private val amountOfRows:Int,private val amountOfColumns:Int,private val amountOfMines:Int){
    fun getAmountOfRows():Int = amountOfRows
    fun getAmountOfColumns():Int = amountOfColumns
    fun getAmountOfMines():Int = amountOfMines
}