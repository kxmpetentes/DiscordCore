package de.kxmpetentes.engine.model

import java.awt.Color

data class ColorModel(private val colors: Collection<Color>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ColorModel

        if (colors != other.colors) return false

        return true
    }

    fun getColor(): Color {
        return colors.random()
    }

    override fun hashCode(): Int {
        return colors.hashCode()
    }

}
