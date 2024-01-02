package com.myapps.minesweeper.ui.game.main

import android.content.Context
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.myapps.minesweeper.R
import com.myapps.minesweeper.databinding.FragmentGameBinding
import com.myapps.minesweeper.utils.GameDetails
import com.myapps.minesweeper.domain.status.GameStatus
import com.myapps.minesweeper.domain.auxiliar.Matrix
import com.myapps.minesweeper.domain.principal.MinesweeperBoard
import com.myapps.minesweeper.utils.CellData
import com.myapps.minesweeper.utils.Position


class GameFragment : Fragment(), GameContract.View{

    private var _binding: FragmentGameBinding?=null
    private val binding get() = _binding!!

    private val args:GameFragmentArgs by navArgs()

    private lateinit var gameUiCells: Matrix<ImageButton>
    private lateinit var  presenter: GamePresenter
    private lateinit var gameDetails: GameDetails

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onAttach(context: Context) {
        val gameDetailsString = args.gameDetails
        val gameDetailsParts = gameDetailsString.split(",")
        gameDetails = GameDetails(
            gameDetailsParts[0].toInt(),
            gameDetailsParts[1].toInt(),
            gameDetailsParts[2].toInt()
        )

        presenter = GamePresenter(
            MinesweeperBoard(gameDetails.amountOfMines,gameDetails.amountOfRows,gameDetails.amountOfColumns),
            GameStatus(gameDetails.amountOfMines)
        )

        super.onAttach(context)
        presenter.onAttach(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
        initGameBoardUiCellsAndFunctionalities()
        setGameButtonsFunctionalities()
        setInitAmountOfMinesText()
        setStatusGameScreenListener()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
        _binding = null
    }

    private fun initGameBoardUiCellsAndFunctionalities(){
        binding.glCellsOfGame.columnCount = gameDetails.amountOfColumns

        gameUiCells = Matrix(gameDetails.amountOfRows,gameDetails.amountOfColumns)

        for(i in 0..< gameDetails.amountOfRows){
            for(j in 0..< gameDetails.amountOfColumns){
                val imageButton = ImageButton(requireContext())
                imageButton.elevation = 5.0f
                imageButton.setPadding(2,2,2,2)
                imageButton.setImageResource(R.drawable.bg_unselected_cell)
                binding.glCellsOfGame.addView(imageButton)

                imageButton.setOnClickListener {
                    val position = Position(i,j)
                    presenter.onPressCell(position)
                }
                gameUiCells.insertElement(imageButton,i,j)
            }
        }

    }
    private fun setInitAmountOfMinesText(){
        binding.tvAmountOfFlags.text = gameDetails.amountOfMines.toString()
    }
    private fun setStatusGameScreenListener(){
        binding.winLoseContainer.setOnClickListener{
            binding.winLoseContainer.visibility = View.GONE
        }
    }

    private fun setGameButtonsFunctionalities(){
        binding.resetButton.setOnClickListener{
                presenter.resetGame()
                val cellBackground = ContextCompat.getDrawable(requireContext(),R.drawable.bg_unselected_cell)
                gameUiCells.traverse({
                        element,_,_,_->
                    element?.setImageDrawable(cellBackground)
                },null)
                binding.tvAmountOfFlags.text = gameDetails.amountOfMines.toString()
                binding.digButton.setBackgroundResource(R.drawable.bg_select_mode)
                binding.flagButton.setBackgroundResource(R.drawable.bg_unselect_mode)
        }

        binding.digButton.setOnClickListener{
                presenter.activateDigMode()
                binding.digButton.setBackgroundResource(R.drawable.bg_select_mode)
                binding.flagButton.setBackgroundResource(R.drawable.bg_unselect_mode)
        }

        binding.flagButton.setOnClickListener {
                presenter.activateFlagMode()
                binding.flagButton.setBackgroundResource(R.drawable.bg_select_mode)
                binding.digButton.setBackgroundResource(R.drawable.bg_unselect_mode)
        }
    }

    override fun showWinScreen() {
        binding.tvWinCondition.text = getString(R.string.game_win)
        binding.winLoseContainer.visibility = View.VISIBLE
    }

    override fun showLoseScreen() {
        binding.tvWinCondition.text = getString(R.string.game_lose)
        binding.winLoseContainer.visibility = View.VISIBLE
    }

    override fun updateFlagCounter(
        counterFlag: Int
    ) {
        binding.tvAmountOfFlags.text = counterFlag.toString()
    }

    override fun showAllMines(
        listOfPositionsOfMines:List<Position>
    ) {
        val cellBackground = ContextCompat.getDrawable(requireContext(),R.drawable.bg_selected_cell)
        val mine = ContextCompat.getDrawable(requireContext(),R.drawable.mine)
        val finalDrawable = LayerDrawable(arrayOf(cellBackground, mine))

        listOfPositionsOfMines.forEach{
            position ->
            gameUiCells.getElementByPosition(position.positionX,position.positionY)?.setImageDrawable(finalDrawable)
        }
    }

    override fun showNonMineCell(
        cellData: CellData
    ) {
        val cellBackground = ContextCompat.getDrawable(requireContext(),R.drawable.bg_selected_cell)
        val numberOfMines = ContextCompat.getDrawable(requireContext(),getDrawableDependOnNumberOfMines(cellData.counterMines))
        val finalDrawable = LayerDrawable(arrayOf(cellBackground, numberOfMines))
        gameUiCells.getElementByPosition(cellData.position.positionX,cellData.position.positionY)?.setImageDrawable(finalDrawable)
    }

    override fun showAllAroundCellsOfZeroCell(
        position: Position,
        listOfPositionsOfMines: List<CellData>
    ) {
        val cellBackground = ContextCompat.getDrawable(requireContext(),R.drawable.bg_selected_cell)

        listOfPositionsOfMines.forEach { cellData ->

            if(cellData.counterMines == 0){
                gameUiCells.getElementByPosition(cellData.position.positionX,cellData.position.positionY)?.setImageDrawable(cellBackground)
            }
            else{
                val numberOfMines = ContextCompat.getDrawable(requireContext(),getDrawableDependOnNumberOfMines(cellData.counterMines))
                val finalDrawable = LayerDrawable(arrayOf(cellBackground, numberOfMines))
                gameUiCells.getElementByPosition(cellData.position.positionX,cellData.position.positionY)?.setImageDrawable(finalDrawable)
            }

        }
    }

    override fun onUnmarkedCell(position: Position) {
        val cellBackground = ContextCompat.getDrawable(requireContext(),R.drawable.bg_unselected_cell)
        val flag = ContextCompat.getDrawable(requireContext(),R.drawable.flag)
        val finalDrawable = LayerDrawable(arrayOf(cellBackground,flag))
        gameUiCells.getElementByPosition(position.positionX,position.positionY)?.setImageDrawable(finalDrawable)
    }

    override fun onMarkedCell(position: Position) {
        val cellBackground = ContextCompat.getDrawable(requireContext(),R.drawable.bg_unselected_cell)
        gameUiCells.getElementByPosition(position.positionX,position.positionY)?.setImageDrawable(cellBackground)
    }

    private fun getDrawableDependOnNumberOfMines(numberOfMines:Int):Int =
        when(numberOfMines){
            1-> R.drawable.one
            2-> R.drawable.two
            3-> R.drawable.three
            4-> R.drawable.four
            5-> R.drawable.five
            6-> R.drawable.six
            7-> R.drawable.seven
            8-> R.drawable.eight
            else->0
        }

}


