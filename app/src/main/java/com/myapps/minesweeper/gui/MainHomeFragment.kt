package com.myapps.minesweeper.gui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.myapps.minesweeper.R
import com.myapps.minesweeper.databinding.FragmentMainHomeBinding
import com.myapps.minesweeper.utils.setTextViewColor


class MainHomeFragment : Fragment() {

    private var _binding:FragmentMainHomeBinding ? = null
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

        //text colors
        setTextViewColor(binding.minesweeperTittleText,
            intArrayOf(ContextCompat.getColor(requireContext(),R.color.yellow),
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

        //init first screen button functionalities
        binding.playButton.setOnClickListener{
            binding.selectionDifficultyLayout.visibility = View.VISIBLE
            binding.playButton.isEnabled = false
        }


        // init selection of difficulty and navigation buttons
        binding.backButton.setOnClickListener{
            binding.selectionDifficultyLayout.visibility = View.GONE
            binding.playButton.isEnabled = true
        }

        binding.beginnerButton.setOnClickListener {
            val gameDetails = "12,8,20" //the first number is for rows, the second for columns and the last
            //is for the amount of mines
            val direction = MainHomeFragmentDirections.actionMainHomeFragmentToGameFragment(gameDetails)
            findNavController().navigate(direction)

        }

        binding.mediumButton.setOnClickListener {
            val gameDetails = "15,8,30" //the first number is for rows, the second for columns and the last
            //is for the amount of mines
            val direction = MainHomeFragmentDirections.actionMainHomeFragmentToGameFragment(gameDetails)
            findNavController().navigate(direction)
        }

        binding.advancedButton.setOnClickListener {
            val gameDetails = "20,8,40" //the first number is for rows, the second for columns and the last
            //is for the amount of mines
            val direction = MainHomeFragmentDirections.actionMainHomeFragmentToGameFragment(gameDetails)
            findNavController().navigate(direction)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}