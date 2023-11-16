package com.myapps.minesweeper.auxiliarStructures

class Matrix<T>(amountOfRows:Int,amountOfColumns:Int) {

    private var matrix:MutableList<T?>?
    private var rows:Int
    private var columns:Int

    init {
        if(amountOfRows>=0 && amountOfColumns>=0){
            rows = amountOfRows
            columns = amountOfColumns
            val totalSize = rows*columns
            matrix = MutableList(totalSize){index -> null}

        }
        else{
            rows = 0
            columns = 0
            matrix = null
        }
    }

    fun getRows():Int{return rows}

    fun getColumns():Int{return columns}

    fun insertElement(element:T,rowIndex:Int,columnIndex:Int){
        if(rowIndex in 0..<rows &&  columnIndex in 0..<columns){
            val index = rowIndex * columns + columnIndex
            matrix?.set(index,element)
        }
    }

    fun getElementByPosition(rowIndex: Int,columnIndex: Int):T?{
        if( rowIndex in 0..<rows  && columnIndex in 0..<columns){
            val index = rowIndex * columns + columnIndex
            return matrix?.get(index)
        }
        return null
    }

    fun addRow(){
       rows += 1
       val newSizeArray = rows*columns
       val array = MutableList<T?>(newSizeArray){index -> null}
        System.arraycopy(matrix,0,array,0,newSizeArray-columns)
        matrix = array
    }

    fun addColumn(){
       columns += 1
        val newSizeArray = rows*columns
        val array = MutableList<T?>(newSizeArray){index -> null}
        System.arraycopy(matrix,0,array,0,newSizeArray-rows)
        matrix = array

        for (i in rows - 1 downTo 0) {
            val index = columns * (i + 1) - 1

            for (j in index - 1 downTo index - columns + 1) {
                matrix?.set(i, matrix!![j-1])
            }
        }
    }

    fun deleteRow(rowIndex:Int):Boolean{
        var rowWasDeleted = false

        if (rowIndex < 0 || rowIndex >= rows) return rowWasDeleted

        val initialIndex: Int = columns * (rowIndex + 1)
        val totalCapacity = rows * columns

        for (i in initialIndex until totalCapacity) {
            matrix!![i-columns] = matrix!![i]
        }

        rows -= 1
        val array = MutableList<T?>(totalCapacity - columns){index -> null}
        System.arraycopy(matrix, 0, array, 0, totalCapacity - columns)
        matrix = array
        rowWasDeleted = true

        return rowWasDeleted
    }

    fun deleteColumn(columnIndex: Int):Boolean{
        var columnWasDeleted = false

        if (columnIndex < 0 || columnIndex >= columns) return columnWasDeleted

        for (i in 0 until rows) {
            var index: Int = i * (columns + columnIndex) + 1
            while (index < index + columns && index < rows * columns) {
                matrix!![index - (i+1)] = matrix!![index]
                index++
            }
        }

        columns -= 1
        val newSize = rows * columns
        val array = MutableList<T?>(newSize){index -> null}
        System.arraycopy(matrix, 0, array, 0, newSize)
        columnWasDeleted = true

        return columnWasDeleted
    }

    fun <R> traverse(matrixDo: MatrixTraverseDo<T, R>, contextVariable:R){
        for(i in 0..<rows){
            for(j in 0..<columns){

                matrixDo.matrixDo(this.getElementByPosition(i,j)!!,i,j,contextVariable)

            }
        }
    }

}