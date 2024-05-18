package com.myapps.minesweeper.ui.game.main

import com.myapps.minesweeper.ui.game.base.BaseContract
import com.myapps.minesweeper.utils.CellData
import com.myapps.minesweeper.utils.Position

interface GameContract {
    interface View : BaseContract.View {
        fun showWinScreen()
        fun showLoseScreen()
        fun updateFlagCounter(counterFlag: Int)
        fun showAllMines(listOfPositionsOfMines: List<Position>)
        fun showNonMineCell(cellData: CellData)
        fun showAllAroundCellsOfZeroCell(position: Position, listOfPositionsOfMines: List<CellData>)
        fun onUnmarkedCell(position: Position)
        fun onMarkedCell(position: Position)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun activateDigMode()
        fun activateFlagMode()
        fun onPressCell(position: Position)
        fun resetGame()
    }
}