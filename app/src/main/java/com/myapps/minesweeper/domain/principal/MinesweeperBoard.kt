package com.myapps.minesweeper.domain.principal

import com.myapps.minesweeper.domain.auxiliar.Matrix


class MinesweeperBoard(private val amountOfMines:Int,amountOfRows:Int,amountOfColumns:Int){

    private val cells: Matrix<MinesweeperCell> = Matrix(amountOfRows,amountOfColumns)

    init {

        for(i in 0..<amountOfRows){
            for(j in 0..<amountOfColumns){
                MinesweeperCell(cells,i,j)
            }
        }
       /*
        cells.traverse(
            {_,rowIndex,columnIndex,_-> MinesweeperCell(cells,rowIndex,columnIndex) }
            ,null)*/



        //init the board mines

        var auxMinesCount = amountOfMines

        while(auxMinesCount!=0){
            val i = (0..<amountOfRows).random()
            val j = (0..<amountOfColumns).random()
            val msCell: MinesweeperCell? = cells.getElementByPosition(i,j)
            if(msCell?.isMine()!!.not()){
                msCell.setMine()
                auxMinesCount -=1
            }
        }

        //update number count of mines in each cell

        for(i in 0..<amountOfRows){
            for(j in 0..<amountOfColumns){
                cells.getElementByPosition(i,j)?.updateAroundMinesCount()
            }
        }
         /*
        cells.traverse(
            { element, _, _, _ -> element?.updateAroundMinesCount()}
            ,null)*/

    }

    fun getMineSweeperCell(rowId: Int,columnId: Int): MinesweeperCell {
        return cells.getElementByPosition(rowId,columnId)!!
    }

    fun getRows():Int = cells.getRows()

    fun getColumns():Int = cells.getColumns()

    fun allNonMinesCellsRevealed():Boolean{
        var i = 0
        var j: Int
        var allRevealed = true
        while (i<cells.getRows()){

            j = 0
            while(j<cells.getColumns() && allRevealed){

                val cell =  cells.getElementByPosition(i,j)

                if(!cell?.isRevealed()!! && !cell.isMine()){
                    allRevealed = false
                }

                j++
            }

            i = if(!allRevealed){ cells.getRows()}
            else{
                i+1
            }
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
        /*
        cells.traverse(
            {element,_,_,_ -> element?.resetCell()}
            ,null)*/


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
         /*
        cells.traverse(
                { element, _, _, _ -> element?.updateAroundMinesCount()}
        ,null)*/
    }
}