package de.kxmpetentes.engine.utils;

import java.time.LocalDate;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 10.01.2021 um 16:30
 */

public class TimeUtils {

    public static String getDateTime(LocalDate localDate) {
        return localDate.getDayOfMonth() + "." + localDate.getMonth().getValue() + "." + localDate.getYear();
    }

}
