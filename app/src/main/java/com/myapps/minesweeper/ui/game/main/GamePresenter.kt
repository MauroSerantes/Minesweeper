package com.myapps.minesweeper.ui.game.main

import com.myapps.minesweeper.domain.principal.MinesweeperBoard
import com.myapps.minesweeper.domain.status.GameStatus
import com.myapps.minesweeper.ui.game.base.BasePresenter
import com.myapps.minesweeper.utils.CellData
import com.myapps.minesweeper.utils.Position


class GamePresenter(
    private var gameBoard: MinesweeperBoard,
    private val gameStatus:GameStatus
) : BasePresenter<GameContract.View>() , GameContract.Presenter {
    override fun activateDigMode() {
        gameStatus.setDigMode()
    }

    override fun activateFlagMode() {
        gameStatus.setFlagMode()
    }

    override fun onPressCell(position: Position) {
        if(gameStatus.isWin() || gameStatus.isLose()) return

        val cell = gameBoard.getMineSweeperCell(position.positionX,position.positionY)

        if(cell.isRevealed()) return

        if(gameStatus.getDigFlagMode()){

                if(cell.isMarked()) return

                cell.setRevealed()

                if(cell.isMine()){
                     val list = locationsOfAllMines()
                     view?.showAllMines(list)
                     view?.showLoseScreen()
                     gameStatus.setGameLose()
                }
                else{
                    if(cell.amountOfAroundMines() == 0){
                        val list = ArrayList<CellData>()
                        addRevealedCellsToListOnZeroPressed(position,list)
                        view?.showAllAroundCellsOfZeroCell(position,list)
                    }
                    else{
                        view?.showNonMineCell(CellData(position,cell.amountOfAroundMines()))
                    }
                }
                checkWinCondition()
        }
        else{
                if(cell.isMarked()){
                    gameStatus.increaseNumberOfFlags()
                    view?.onMarkedCell(position)
                    cell.setMarked()
                }
                else{
                    if(gameStatus.getAmountOfFlags() > 0){
                        gameStatus.decreaseNumberOfFlags()
                        view?.onUnmarkedCell(position)
                        cell.setMarked()
                    }
                }

                gameStatus.getAmountOfFlags().let {
                    view?.updateFlagCounter(it)
                }
        }
    }

    override fun resetGame() {
        gameBoard.resetBoard()
        gameStatus.reset()
    }

    private fun checkWinCondition(){
        if(gameBoard.allNonMinesCellsRevealed()){
            gameStatus.setGameWin()
            view?.showWinScreen()
        }

    }

    private fun addRevealedCellsToListOnZeroPressed(position: Position, list: ArrayList<CellData>){
        val cellData = CellData(position,gameBoard.getMineSweeperCell(position.positionX,position.positionY).amountOfAroundMines())

        if(!list.contains(cellData)){
            list.add(cellData)
            if(cellData.counterMines == 0){
                getAllAroundCells(position,list)
            }
        }
    }

    private fun getAllAroundCells(
        cellPosition: Position,
        listOfCells:ArrayList<CellData>){
        val fromRow = if(cellPosition.positionX>0){ cellPosition.positionX-1 }
        else 0

        val fromColumn = if(cellPosition.positionY>0){ cellPosition.positionY-1 }
        else 0

        val toRow = if(cellPosition.positionX< gameBoard.getRows().minus(1)){ cellPosition.positionX+1}
        else gameBoard.getRows().minus(1)

        val toColumn = if(cellPosition.positionY < gameBoard.getColumns().minus(1)){ cellPosition.positionY + 1 }
        else gameBoard.getColumns().minus(1)


        for(i in fromRow..toRow){
            for(j in fromColumn..toColumn){

                val position = Position(i,j)

                if(position.positionX != cellPosition.positionX ||
                    position.positionY != cellPosition.positionY){
                    addRevealedCellsToListOnZeroPressed(position,listOfCells)
                }

            }
        }
    }

    private fun locationsOfAllMines():List<Position>{
        val list = ArrayList<Position>()

        for(i in 0..< gameBoard.getRows()){

            for (j in 0..< gameBoard.getColumns()){

                if(gameBoard.getMineSweeperCell(i,j).isMine()){
                    val position = Position(i,j)
                    list.add(position)
                }

            }

        }
        return list
    }
}