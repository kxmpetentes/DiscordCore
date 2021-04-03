package de.kxmpetentes.engine.model

import java.awt.Color

/**
 * @param colors        all colors to be added
 */
class ColorModel(vararg colors: Color) {

    private val colorArrayList: ArrayList<Color> = ArrayList()

    init {
        colors.forEach {
            colorArrayList.add(it.darker())
            colorArrayList.add(it.brighter())
            colorArrayList.add(it)
        }
    }

    /**
     * @return          random color
     */
    fun getColor(): Color {
        return colorArrayList.random()
    }

}
