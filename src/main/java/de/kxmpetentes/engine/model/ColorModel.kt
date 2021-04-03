package de.kxmpetentes.engine.model

import java.awt.Color

class ColorModel(vararg colors: Color) {

    private val colorArrayList: ArrayList<Color> = ArrayList()

    init {
        colors.forEach {
            colorArrayList.add(it.darker())
            colorArrayList.add(it.brighter())
            colorArrayList.add(it)
        }
    }

    fun getColor(): Color {
        return colorArrayList.random()
    }

}
