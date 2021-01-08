package de.kxmpetentes.engine.language;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 05.01.2021 um 03:49
 */

public enum LanguageTypes {

    DE("German", 0),
    EN("English", 1);

    final String type;
    final int id;

    LanguageTypes(String type, int id) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    /**
     * @return languagetype
     * @throws NullPointerException id can be null
     */
    public static LanguageTypes getTypeByID(int id) {
        for (LanguageTypes language : values()) {
            if (language.getId() == id) {
                return language;
            }
        }

        return null;
    }

    /**
     * @return languagetype
     * @throws NullPointerException name can be null
     */
    public static LanguageTypes getTypeByName(String name) {
        for (LanguageTypes language : values()) {
            if (language.getType().equals(name)) {
                return language;
            }
        }

        return null;
    }
}
