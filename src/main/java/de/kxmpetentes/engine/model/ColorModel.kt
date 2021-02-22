package de.kxmpetentes.engine.model

import java.awt.Color
import kotlin.random.Random

data class ColorModel(private val colors: Array<Color>) {



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ColorModel

        if (!colors.contentEquals(other.colors)) return false

        return true
    }

    override fun hashCode(): Int {
        return colors.contentHashCode()
    }

    fun getColor(): Color {
        return colors[Random.nextInt()]
    }

}
