package com.myapps.minesweeper.ui.game.base

interface BaseContract {

    interface View{

    }

    interface Presenter<V: View>{
        fun onAttach(view: V)
        fun onViewCreated()
        fun onDetach()
    }
}