package com.myapps.minesweeper.domain.auxiliar

fun interface MatrixTraverseDo<T,R> {
    fun matrixDo(element: T?,rowIndex:Int,columnIndex:Int,contextVariable:R?)
}