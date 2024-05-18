package com.myapps.minesweeper.ui

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.myapps.minesweeper.R
import com.myapps.minesweeper.databinding.FragmentMainHomeBinding
import com.myapps.minesweeper.utils.setTextViewColor


class MainHomeFragment : Fragment() {

    private var _binding: FragmentMainHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtonsForSelectionDifficultyMenu()
        initSelectionDifficultyButtons()
        setGradientColorTextForPresentationGame()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setGradientColorTextForPresentationGame(){
        setTextViewColor(binding.minesweeperTittleText,
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.yellow),
                ContextCompat.getColor(requireContext(),R.color.orange),
                ContextCompat.getColor(requireContext(),R.color.red),
                ContextCompat.getColor(requireContext(),R.color.purple),
                ContextCompat.getColor(requireContext(),R.color.blue),
                ContextCompat.getColor(requireContext(),R.color.green))
        )
        setTextViewColor(binding.tvBeginner,
            intArrayOf(ContextCompat.getColor(requireContext(),R.color.yellow),
                ContextCompat.getColor(requireContext(),R.color.orange),
                ContextCompat.getColor(requireContext(),R.color.red),
                ContextCompat.getColor(requireContext(),R.color.purple),
                ContextCompat.getColor(requireContext(),R.color.blue),
                ContextCompat.getColor(requireContext(),R.color.green))
        )
        setTextViewColor(binding.tvMedium,
            intArrayOf(ContextCompat.getColor(requireContext(),R.color.yellow),
                ContextCompat.getColor(requireContext(),R.color.orange),
                ContextCompat.getColor(requireContext(),R.color.red),
                ContextCompat.getColor(requireContext(),R.color.purple),
                ContextCompat.getColor(requireContext(),R.color.blue),
                ContextCompat.getColor(requireContext(),R.color.green))
        )
        setTextViewColor(binding.tvAdvanced,
            intArrayOf(ContextCompat.getColor(requireContext(),R.color.yellow),
                ContextCompat.getColor(requireContext(),R.color.orange),
                ContextCompat.getColor(requireContext(),R.color.red),
                ContextCompat.getColor(requireContext(),R.color.purple),
                ContextCompat.getColor(requireContext(),R.color.blue),
                ContextCompat.getColor(requireContext(),R.color.green))
        )
    }

    private fun initButtonsForSelectionDifficultyMenu(){
        binding.apply {
            playButton.setOnClickListener{
                binding.selectionDifficultyLayout.visibility = View.VISIBLE
                binding.playButton.isEnabled = false
            }

            backButton.setOnClickListener{
                binding.selectionDifficultyLayout.visibility = View.GONE
                binding.playButton.isEnabled = true
            }
        }
    }

    private fun initSelectionDifficultyButtons(){
        binding.apply {
            beginnerButton.setOnClickListener {
                val gameDetails = "8,8,10" //the first number is for rows, the second for columns and the last
                //is for the amount of mines
                val direction = MainHomeFragmentDirections.actionMainHomeFragmentToGameFragment(gameDetails)
                findNavController().navigate(direction)
            }
            mediumButton.setOnClickListener {
                val gameDetails = "32,8,40"
                val direction = MainHomeFragmentDirections.actionMainHomeFragmentToGameFragment(gameDetails)
                findNavController().navigate(direction)
            }
            advancedButton.setOnClickListener {
                val gameDetails = "60,8,99"
                val direction = MainHomeFragmentDirections.actionMainHomeFragmentToGameFragment(gameDetails)
                findNavController().navigate(direction)
            }
        }
    }



}