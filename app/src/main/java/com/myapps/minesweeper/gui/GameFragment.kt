package com.myapps.minesweeper.gui

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.myapps.minesweeper.R
import com.myapps.minesweeper.utils.GameDetails
import com.myapps.minesweeper.utils.GameStatus
import com.myapps.minesweeper.auxiliarstructures.Matrix
import com.myapps.minesweeper.databinding.FragmentGameBinding
import com.myapps.minesweeper.principalclasses.MinesweeperBoard


class GameFragment : Fragment() {

    private var _binding:FragmentGameBinding?=null
    private val binding get() = _binding!!

    private val args:GameFragmentArgs by navArgs()

    private lateinit var  gameDetails: GameDetails // for the amount of rows , columns and mines
    private lateinit var gameStatus: GameStatus // different game modes and win or lose status

    // this involves the game at logic level and the relation with the ui level
    private lateinit var gameBoard:MinesweeperBoard
    private lateinit var gameUiCells:Matrix<ImageButton>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(layoutInflater,container,false)
        return  binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get the argument pass to this fragment
        val gameDetailsString = args.gameDetails
        val gameDetailsParts = gameDetailsString.split(",")

        gameDetails = GameDetails(
            gameDetailsParts[0].toInt(),
            gameDetailsParts[1].toInt(),
            gameDetailsParts[2].toInt()
        )

       // prepare grid layout (for display the cells)
        binding.glCellsOfGame.columnCount = gameDetails.getAmountOfColumns()

        // init boardOfGame and ui board associated
        gameBoard = MinesweeperBoard(gameDetails.getAmountOfMines(),gameDetails.getAmountOfRows(),gameDetails.getAmountOfColumns())
        gameUiCells = Matrix(gameDetails.getAmountOfRows(),gameDetails.getAmountOfColumns())


        // create all buttons insert all the image buttons into the grid layout and the control button matrix
        for(i in 0..< gameDetails.getAmountOfRows()){
            for(j in 0..< gameDetails.getAmountOfColumns()) {
                val imageButton = ImageButton(requireContext())
                imageButton.elevation = 5.0f
                imageButton.setPadding(2,2,2,2)
                imageButton.setImageResource(R.drawable.bg_unselected_cell)
                binding.glCellsOfGame.addView(imageButton)
                gameUiCells.insertElement(imageButton,i,j)
            }
        }

        // init gameStatus
        gameStatus = GameStatus(gameDetails.getAmountOfMines())


        binding.tvAmountOfFlags.text = gameStatus.getAmountOfFlags().toString()

        addCommandsButtonsFunctionalities()

        addButtonsFunctionalities()

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




    private fun addCommandsButtonsFunctionalities(){
        binding.digButton.setOnClickListener{
            gameStatus.setDigMode()
            Toast.makeText(requireContext(),"DIG MODE ACTIVATED",Toast.LENGTH_SHORT).show()
        }

        binding.flagButton.setOnClickListener{
            gameStatus.setFlagMode()
            Toast.makeText(requireContext(),"FLAG MODE ACTIVATED",Toast.LENGTH_SHORT).show()
        }

        binding.resetButton.setOnClickListener{
            gameStatus.setDigMode()
            resetGame()
        }
    }



    private fun resetGame(){
        gameBoard.resetBoard()
        gameStatus.reset()
        binding.tvAmountOfFlags.text = gameStatus.getAmountOfFlags().toString()
        for(i in 0..<gameDetails.getAmountOfRows()){
            for(j in 0..<gameDetails.getAmountOfColumns()){
                 gameUiCells.getElementByPosition(i,j)?.setImageResource(R.drawable.bg_unselected_cell)
            }
        }
    }



    private fun addButtonsFunctionalities(){
        for(i in 0..<gameDetails.getAmountOfRows()){
            for(j in 0..<gameDetails.getAmountOfColumns()){

                val imageButton = gameUiCells.getElementByPosition(i,j)

                imageButton?.setOnClickListener{

                        val cellGame = gameBoard.getMineSweeperCell(i,j)

                        if(!gameStatus.isWin() && !gameStatus.isLose()){

                             if(gameStatus.getDigFlagMode() && !cellGame.isMarked() && !cellGame.isRevealed()){

                                val resource = if(cellGame.isMine()) R.drawable.mine
                                else getDrawableDependOnNumberOfMines(cellGame.amountOfAroundMines())

                                 val cellBackground = ContextCompat.getDrawable(requireContext(),R.drawable.bg_selected_cell)

                                 val drawable = if(resource == 0) null
                                  else ContextCompat.getDrawable(requireContext(),resource)

                                 val finalDrawable = if(drawable == null) cellBackground
                                    else LayerDrawable(arrayOf(cellBackground, drawable))

                                 imageButton.setImageDrawable(finalDrawable)
                                 cellGame.setRevealed()

                                 if(gameBoard.allNonMinesCellsRevealed()){
                                     gameStatus.setGameWin()
                                     displayWinLoseStatus()
                                 }

                                 if(resource==0) revealCells()

                                 if(resource == R.drawable.mine){
                                     revealAllMines()
                                     gameStatus.setGameLose()
                                     displayWinLoseStatus()
                                 }


                            }
                            else{
                                if(!cellGame.isRevealed()){
                                    if(!cellGame.isMarked()){
                                        val cell = ContextCompat.getDrawable(requireContext(),R.drawable.bg_unselected_cell)
                                        val flag = ContextCompat.getDrawable(requireContext(), R.drawable.flag)

                                        val finalDrawable = LayerDrawable(arrayOf(cell, flag))
                                        imageButton.setImageDrawable(finalDrawable)
                                        cellGame.setMarked()
                                        updateFlagCounter(false)
                                    }
                                    else{
                                        imageButton.setImageResource(R.drawable.bg_unselected_cell)
                                        cellGame.setMarked()
                                        updateFlagCounter(true)
                                    }
                                }
                            }
                        }

                }
            }
        }
    }


    private fun displayWinLoseStatus(){
        if(gameStatus.isLose()){
            binding.tvWinCondition.setText(R.string.game_lose)
        }
        if(gameStatus.isWin()){
            binding.tvWinCondition.setText(R.string.game_win)
        }
        if(gameStatus.isWin() || gameStatus.isLose()){
            binding.winLoseContainer.visibility = View.VISIBLE
            binding.winLoseContainer.setOnClickListener{
                binding.winLoseContainer.visibility  = View.GONE
            }
        }
    }



    private fun updateFlagCounter(isIncreased:Boolean){
        if(isIncreased){
            gameStatus.increaseNumberOfFlags()
        }
        else{
           gameStatus.decreaseNumberOfFlags()
        }
        binding.tvAmountOfFlags.text = gameStatus.getAmountOfFlags().toString()
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


    private fun revealCells(){
        for(i in 0..<gameDetails.getAmountOfRows()){
            for(j in 0..<gameDetails.getAmountOfColumns()){

                val cell = gameBoard.getMineSweeperCell(i,j)

                if(cell.isRevealed() && !cell.isMine()){

                    val cellBackground = ContextCompat.getDrawable(requireContext(),R.drawable.bg_selected_cell)
                    val value = getDrawableDependOnNumberOfMines(cell.amountOfAroundMines())


                    val mineNumber = if(value!=0){
                        ContextCompat.getDrawable(requireContext(),value)
                    }
                    else null


                    val finalDrawable = if(value!=0){
                        LayerDrawable(arrayOf(cellBackground, mineNumber))
                    }else cellBackground


                    gameUiCells.getElementByPosition(i,j)?.setImageDrawable(finalDrawable)
                }
            }
        }
    }


    private fun revealAllMines(){
        for(i in 0..<gameDetails.getAmountOfRows()){
            for(j in 0..<gameDetails.getAmountOfColumns()){

                val cell = gameBoard.getMineSweeperCell(i,j)
                if(cell.isMine()){
                    cell.setRevealed()
                    val cellBackground = ContextCompat.getDrawable(requireContext(),R.drawable.bg_selected_cell)
                    val mine = ContextCompat.getDrawable(requireContext(),R.drawable.mine)

                    val finalDrawable = LayerDrawable(arrayOf(cellBackground, mine))
                    gameUiCells.getElementByPosition(i,j)?.setImageDrawable(finalDrawable)
                }

            }
        }
    }


}


