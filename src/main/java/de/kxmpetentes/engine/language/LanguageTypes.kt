package de.kxmpetentes.engine.language

import kotlin.properties.Delegates

enum class LanguageTypes(s: String, i: Int) {

    DE("German", 1),
    EN("English", 2);

    private lateinit var type: String
    private var id: Int = 0


    fun getType(): String {
        return type
    }

    fun getId(): Int {
        return id
    }

    /**
     * @return languagetype
     * @throws NullPointerException id can be null
     */
    open fun getTypeByID(id: Int): LanguageTypes? {
        for (language in LanguageTypes.values()) {
            if (language.getId() == id) {
                return language
            }
        }
        return null
    }

    /**
     * @return languagetype
     * @throws NullPointerException name can be null
     */
    open fun getTypeByName(name: String): LanguageTypes? {
        for (language in LanguageTypes.values()) {
            if (language.getType() == name) {
                return language
            }
        }
        return null
    }
}