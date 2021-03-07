package de.kxmpetentes.engine.model

import sun.text.resources.ro.CollationData_ro
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ColorModel

        if (colors != other.colors) return false

        return true
    }

    fun getColor(): Color {
        return colorArrayList.random()
    }

    override fun hashCode(): Int {
        return colorArrayList.hashCode()
    }



}
