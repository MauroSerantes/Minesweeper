package com.myapps.minesweeper.auxiliarStructures

interface MatrixTraverseDo<T,R> {
    public fun matrixDo(element: T,rowIndex:Int,columnIndex:Int,contextVariable:R)
}