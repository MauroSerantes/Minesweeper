package com.myapps.minesweeper.gui


import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.myapps.minesweeper.auxiliarStructures.Matrix
import com.myapps.minesweeper.principalClasses.MinesweeperBoard
import com.myapps.minesweeper.R


class MinesweeperBeginner : AppCompatActivity() {

    private val totalAmountOfMines = 24
    private val rows = 12
    private val columns = 8
    private var gameBoard: MinesweeperBoard = MinesweeperBoard(totalAmountOfMines,rows,columns)

    private val cellsImageButton: Matrix<ImageButton> = Matrix(rows,columns)

    private var isDigMode:Boolean = true
    private var gameStateLose = false
    private var gameStateWin = false

    private lateinit var clearButton:ImageButton
    private lateinit var flagButton:ImageButton
    private lateinit var resetButton:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minesweeper_beginner)

          initCommandsButtons()
          initCellsButtons()
          addCommandsButtonsFuntionalities()
          addButtonsFunctionalities()

    }

    fun initCommandsButtons(){
        clearButton  = findViewById(R.id.clear_button)
        flagButton = findViewById(R.id.flag_button)
        resetButton = findViewById(R.id.reset_button)
    }

    fun initCellsButtons(){
        for(i in 0..<rows){
            for(j in 0..<columns){
                val buttonId = "button$i$j"
                val id = resources.getIdentifier(buttonId,"id",packageName)
                val imageButton:ImageButton = findViewById(id)
                cellsImageButton.insertElement(imageButton,i,j)
            }
        }
    }

    fun addCommandsButtonsFuntionalities(){
        clearButton.setOnClickListener{
            isDigMode = true
            clearButton.setBackgroundColor(ContextCompat.getColor(this,R.color.background_selection_button))
            flagButton.setBackgroundColor(ContextCompat.getColor(this,R.color.background_button))
        }

        flagButton.setOnClickListener{
            isDigMode = false
            flagButton.setBackgroundColor(ContextCompat.getColor(this,R.color.background_selection_button))
            clearButton.setBackgroundColor(ContextCompat.getColor(this,R.color.background_button))
        }

        resetButton.setOnClickListener{
            isDigMode = true
            clearButton.setBackgroundColor(ContextCompat.getColor(this,R.color.background_selection_button))
            flagButton.setBackgroundColor(ContextCompat.getColor(this,R.color.background_button))
            resetGame()
        }
    }


    fun addButtonsFunctionalities(){
        for(i in 0..<rows){
            for(j in 0..<columns){
                val imageButton = cellsImageButton.getElementByPosition(i,j)

                imageButton?.setOnClickListener{
                    val cellGame = gameBoard.getMineSweeperCell(i,j)
                    if(isDigMode && !gameStateLose){
                        imageButton.setBackgroundColor(ContextCompat.getColor(this,R.color.background_button))

                        var resource = 0
                        resource = if(cellGame?.isMine()!!){
                            R.drawable.mine
                        } else{
                            getDrawableDependOnNumberOfMines(cellGame.amountOfAroundMines()!!)
                        }

                        if(!cellGame?.isMarked()!! && !cellGame?.isRevealed()!!){
                            imageButton.setImageResource(resource)
                            cellGame.setRevealed()
                            if(resource==0){
                                revealedCells()
                            }
                            if(resource == R.drawable.mine){
                                revealAllMines()
                                gameStateLose = true
                            }
                        }
                    }
                    else{

                       if(!cellGame?.isRevealed()!! && !gameStateLose){
                           if(!cellGame?.isMarked()!!){
                               val cell = ContextCompat.getDrawable(this,R.drawable.cell)
                               val flag = ContextCompat.getDrawable(this, R.drawable.flag)

                               val finalDrawable = LayerDrawable(arrayOf(cell, flag))
                               imageButton.setImageDrawable(finalDrawable)
                               cellGame.setMarked()
                           }
                           else{
                               imageButton.setImageResource(R.drawable.cell)
                               cellGame.setMarked()
                           }
                       }
                    }
                }

            }
        }
    }


    private fun getDrawableDependOnNumberOfMines(numberOfMines:Int):Int{
        if(numberOfMines == 1) return R.drawable.one
        if(numberOfMines == 2) return R.drawable.two
        if(numberOfMines == 3) return R.drawable.three
        if(numberOfMines == 4) return R.drawable.four
        if(numberOfMines == 5) return R.drawable.five
        if(numberOfMines == 6) return R.drawable.six
        if(numberOfMines == 7) return R.drawable.seven
        if(numberOfMines == 8) return R.drawable.eight
        return 0
    }


    private fun revealedCells(){
        for(i in 0..<12){
            for(j in 0..<8){
                val cell = gameBoard.getMineSweeperCell(i,j)
                if(cell?.isRevealed()!! && !cell?.isMine()!!){
                    cellsImageButton.getElementByPosition(i,j)?.setBackgroundColor(ContextCompat.getColor(this,R.color.background_button))
                    cellsImageButton.getElementByPosition(i,j)?.setImageResource(getDrawableDependOnNumberOfMines(
                        cell.amountOfAroundMines()!!))
                }
            }
        }
    }

    private fun revealAllMines(){
        for(i in 0..<12){
            for(j in 0..<8){
                val cell = gameBoard.getMineSweeperCell(i,j)
                if(cell?.isMine()!!){
                    cell.setRevealed()
                    cellsImageButton.getElementByPosition(i,j)?.setBackgroundColor(ContextCompat.getColor(this,R.color.background_button))
                    cellsImageButton.getElementByPosition(i,j)?.setImageResource(R.drawable.mine)
                }
            }
        }
    }

    private fun resetGame(){
        gameBoard.resetBoard()
        gameStateLose = false
        gameStateWin = false
        for(i in 0..<rows){
            for(j in 0..columns){
                cellsImageButton.getElementByPosition(i,j)?.setImageResource(R.drawable.cell)
            }
        }
    }

}