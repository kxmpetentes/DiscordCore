package de.kxmpetentes.engine.language;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 05.01.2021 um 03:49
 */

public enum LanguageTypes {

    DE("German"),
    EN("English");

    final String type;

    LanguageTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
