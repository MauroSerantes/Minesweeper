package com.myapps.minesweeper.utils

import android.graphics.LinearGradient
import android.graphics.Shader
import android.text.TextPaint
import android.widget.TextView

fun setTextViewColor(textView:TextView,listOfColorsIds:IntArray){
    val paint:TextPaint = textView.paint
    val width:Float = paint.measureText(textView.text.toString())

    val shader:Shader = LinearGradient(0.0f,0.0f,width,textView.textSize,listOfColorsIds,null,Shader.TileMode.CLAMP)
    textView.paint.shader = shader
    textView.setTextColor(listOfColorsIds[0])
}

