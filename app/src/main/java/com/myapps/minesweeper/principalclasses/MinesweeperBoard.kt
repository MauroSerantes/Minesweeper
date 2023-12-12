package com.myapps.minesweeper.principalclasses

import com.myapps.minesweeper.auxiliarstructures.Matrix

class MinesweeperBoard(amountOfMines:Int,amountOfRows:Int,amountOfColumns:Int){
    private val cells: Matrix<MinesweeperCell> = Matrix(amountOfRows,amountOfColumns)
    private var amountOfMines:Int = amountOfMines

    init {
        var auxMinesCount = amountOfMines

        for(i in 0..<amountOfRows){
            for(j in 0..<amountOfColumns){
                val cell: MinesweeperCell = MinesweeperCell(cells,i,j)
            }
        }

        while(auxMinesCount!=0){
            val i = (0..<amountOfRows).random()
            val j = (0..<amountOfColumns).random()
            val msCell:MinesweeperCell? = cells.getElementByPosition(i,j)
            if(msCell?.isMine()!!.not()){
                msCell.setMine()
                auxMinesCount -=1
            }
        }

        for(i in 0..<amountOfRows){
            for(j in 0..<amountOfColumns){
                cells.getElementByPosition(i,j)?.updateAroundMinesCount()
            }
        }
    }

    fun getMineSweeperCell(rowId: Int,columnId: Int): MinesweeperCell {
        return cells.getElementByPosition(rowId,columnId)!!
    }

    fun allNonMinesCellsRevealed():Boolean{
        var i = 0
        var j = 0
        var allRevealed = true
        while (i<cells.getRows() && allRevealed){

            j = 0
            while(j<cells.getColumns() && allRevealed){
               val cell =  cells.getElementByPosition(i,j)

                if(!cell?.isRevealed()!! && !cell.isMine()){
                    allRevealed = false
                }

                j++
            }

            i++
        }
        return allRevealed
    }


    fun resetBoard(){
        for(i in 0..<cells.getRows()){
            for(j in 0..<cells.getColumns()){
                val cell = cells.getElementByPosition(i,j)
                cell?.resetCell()
            }
        }

        var auxMinesCount = amountOfMines

        while(auxMinesCount!=0){
            val i = (0..<cells.getRows()).random()
            val j = (0..<cells.getColumns()).random()
            val msCell = cells.getElementByPosition(i,j)
            if(msCell?.isMine()!!.not()){
                msCell.setMine()
                auxMinesCount -=1
            }
        }

        for(i in 0..<cells.getRows()){
            for(j in 0..<cells.getColumns()){
                val cell = cells.getElementByPosition(i,j)
                cell?.updateAroundMinesCount()
            }
        }
    }
}