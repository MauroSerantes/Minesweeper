package com.myapps.minesweeper.principalClasses

import com.myapps.minesweeper.auxiliarStructures.Matrix


class MinesweeperCell(associatedMatrix: Matrix<MinesweeperCell>, rowId:Int, columnId:Int){
    private var minesCount:Int = 0
    private var isMarked:Boolean
    private var isMine:Boolean
    private var isRevealed:Boolean
    private var associatedMatrix: Matrix<MinesweeperCell>
    private var rowIndexLocation:Int
    private var columnIndexLocation:Int

    init {
        isMarked = false
        isMine = false
        isRevealed = false
        this.associatedMatrix = associatedMatrix
        rowIndexLocation = rowId
        columnIndexLocation = columnId
        associatedMatrix.insertElement(this,rowId,columnId)
    }

    fun setMarked(){
        isMarked = !isMarked
    }

    fun setRevealed(){
        if(isRevealed.not()){

            isRevealed = true

            if(minesCount == 0 && !isMine){
                revealedAroundCells()
            }
        }
    }

    fun setMine(){
        isMine = !isMine
    }

    fun amountOfAroundMines():Int?{return minesCount}

    fun isMarked():Boolean?{return isMarked}

    fun isMine():Boolean?{return isMine}

    fun isRevealed():Boolean?{return isRevealed}

    fun updateAroundMinesCount(){
        val fromRow = if(rowIndexLocation>0){ rowIndexLocation-1 }
        else 0

        val fromColumn = if(columnIndexLocation>0){ columnIndexLocation-1 }
        else 0

        val toRow = if(rowIndexLocation < associatedMatrix.getRows()-1){ rowIndexLocation+1 }
        else associatedMatrix.getRows()-1

        val toColumn = if(columnIndexLocation < associatedMatrix.getColumns()-1){ columnIndexLocation+1 }
        else associatedMatrix.getColumns()-1


        for(i in fromRow..toRow){
            for(j in fromColumn..toColumn){
                  if(associatedMatrix.getElementByPosition(i,j)?.isMine()!!){
                      minesCount += 1
                  }
            }
        }
    }


    fun revealedAroundCells(){
        val fromRow = if(rowIndexLocation>0){ rowIndexLocation-1 }
        else 0

        val fromColumn = if(columnIndexLocation>0){columnIndexLocation-1 }
        else 0

        val toRow = if(rowIndexLocation < associatedMatrix.getRows()-1){ rowIndexLocation+1 }
        else associatedMatrix.getRows()-1

        val toColumn = if(columnIndexLocation < associatedMatrix.getColumns()-1){ columnIndexLocation+1 }
        else associatedMatrix.getColumns()-1

        for(i in fromRow..toRow){
            for(j in fromColumn..toColumn){

                 if(i != rowIndexLocation || j != columnIndexLocation){
                     val cell: MinesweeperCell = associatedMatrix.getElementByPosition(i,j)!!
                     cell.setRevealed()
                 }
            }
        }
    }

    fun resetCell(){
        isMarked = false
        isMine = false
        isRevealed = false
        minesCount = 0
    }

}