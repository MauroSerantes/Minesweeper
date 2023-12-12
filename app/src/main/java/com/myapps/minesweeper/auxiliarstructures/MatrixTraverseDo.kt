package com.myapps.minesweeper.auxiliarstructures

interface MatrixTraverseDo<T,R> {
    fun matrixDo(element: T,rowIndex:Int,columnIndex:Int,contextVariable:R)
}