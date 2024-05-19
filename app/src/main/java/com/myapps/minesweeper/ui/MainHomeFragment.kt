package com.myapps.minesweeper.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
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

        setTextViewColor(binding.minesweeperTittleText,
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.yellow),
                ContextCompat.getColor(requireContext(),R.color.orange),
                ContextCompat.getColor(requireContext(),R.color.red),
                ContextCompat.getColor(requireContext(),R.color.purple),
                ContextCompat.getColor(requireContext(),R.color.blue),
                ContextCompat.getColor(requireContext(),R.color.green))
        )

        binding.playButton.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.difficulty_selection)

            dialog.findViewById<View>(R.id.beginnerButton)
                .setOnClickListener {
                    val gameDetails =
                        "8,8,10"
                    val direction =
                        MainHomeFragmentDirections.actionMainHomeFragmentToGameFragment(gameDetails)
                    findNavController().navigate(direction)
                    dialog.dismiss()
                }
            dialog.findViewById<View>(R.id.mediumButton).setOnClickListener {
                val gameDetails = "32,8,40"
                val direction =
                    MainHomeFragmentDirections.actionMainHomeFragmentToGameFragment(gameDetails)
                findNavController().navigate(direction)
                dialog.dismiss()
            }

            dialog.findViewById<View>(R.id.advancedButton).setOnClickListener {
                val gameDetails = "60,8,99"
                val direction = MainHomeFragmentDirections.actionMainHomeFragmentToGameFragment(gameDetails)
                findNavController().navigate(direction)
                dialog.dismiss()
            }

            setTextViewColor(dialog.findViewById(R.id.tvBeginner),
                intArrayOf(ContextCompat.getColor(requireContext(),R.color.yellow),
                    ContextCompat.getColor(requireContext(),R.color.orange),
                    ContextCompat.getColor(requireContext(),R.color.red),
                    ContextCompat.getColor(requireContext(),R.color.purple),
                    ContextCompat.getColor(requireContext(),R.color.blue),
                    ContextCompat.getColor(requireContext(),R.color.green))
            )
            setTextViewColor(dialog.findViewById(R.id.tvMedium),
                intArrayOf(ContextCompat.getColor(requireContext(),R.color.yellow),
                    ContextCompat.getColor(requireContext(),R.color.orange),
                    ContextCompat.getColor(requireContext(),R.color.red),
                    ContextCompat.getColor(requireContext(),R.color.purple),
                    ContextCompat.getColor(requireContext(),R.color.blue),
                    ContextCompat.getColor(requireContext(),R.color.green))
            )
            setTextViewColor(dialog.findViewById(R.id.tvAdvanced),
                intArrayOf(ContextCompat.getColor(requireContext(),R.color.yellow),
                    ContextCompat.getColor(requireContext(),R.color.orange),
                    ContextCompat.getColor(requireContext(),R.color.red),
                    ContextCompat.getColor(requireContext(),R.color.purple),
                    ContextCompat.getColor(requireContext(),R.color.blue),
                    ContextCompat.getColor(requireContext(),R.color.green))
            )



            dialog.findViewById<View>(R.id.dialog_button).setOnClickListener{
                dialog.dismiss()
            }
            dialog.show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}



