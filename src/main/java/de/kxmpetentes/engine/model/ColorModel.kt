package de.kxmpetentes.engine.model

import java.awt.Color

data class ColorModel(private val colors: Collection<Color>) {

    private val colorArrayList: ArrayList<Color> = ArrayList()

    init {
        this.colors.forEach {
            colorArrayList.add(it.darker())
            colorArrayList.add(it.brighter())
            colorArrayList.add(it)
        }
    }

    fun getColor(): Color {
        return colorArrayList.random()
    }

}
