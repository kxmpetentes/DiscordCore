package de.kxmpetentes.engine.command;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 04.01.2021 um 21:44
 */

public enum CommandType {

    UTILS("Utils", 0),
    MODERATION("Moderation", 1),
    FUN("Fun", 2),
    ROLE("Role", 3),
    SETUP("Setup", 4);

    private final String moduleName;
    private final int typeID;

    private CommandType(String moduleName, int typeID) {
        this.moduleName = moduleName;
        this.typeID = typeID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getTypeID() {
        return typeID;
    }
}