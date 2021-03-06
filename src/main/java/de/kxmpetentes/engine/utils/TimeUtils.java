package de.kxmpetentes.engine.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitLab: gitlab.com/kxmpetentes
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 10.01.2021 um 16:30
 */

public class TimeUtils {

    private TimeUtils() {
    }

    public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;
    private static final SimpleDateFormat timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private static final SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy/MM/dd");

    public static String getTimestampFormat(long time) {
        try {
            return timestamp.format(new Date(time));
        } catch (Exception e) {
            return "cant figure out (" + time + ")";
        }
    }

    public static String formatYMD(Date date) {
        return ymdFormat.format(date);
    }

    /**
     * @param time timestamp in seconds
     * @return x m[inutes [from now]]
     */
    public static String getRelativeTime(long time) {
        long usedTime = time * 1000L;
        long now = System.currentTimeMillis();

        if (usedTime <= 0) {
            return "???";
        }

        long diff;
        if (usedTime > now) {
            diff = usedTime - now;
        } else {
            diff = now - usedTime;
        }

        if (diff < MINUTE_MILLIS) {
            return (diff / SECOND_MILLIS) + "s";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "~1m";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + "m";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "~1h";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + "h";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "~1d";
        } else if (diff < 14 * DAY_MILLIS) {
            return diff / DAY_MILLIS + "d";
        }
        return ">2w";
    }

    private static final Map<Character, Long> TIME_SYMBOLS = new HashMap<>();

    static {
        TIME_SYMBOLS.put('w', 604800000L);
        TIME_SYMBOLS.put('d', 86400000L);
        TIME_SYMBOLS.put('h', 3600000L);
        TIME_SYMBOLS.put('m', 60000L);
        TIME_SYMBOLS.put('s', 1000L);
    }

    /**
     * Takes the value of the string as represented by trailing
     * w, d, h, m, or s characters and gets a millisecond value
     * from them.  Any values with no label are added as millis.
     *
     * @param s the string to be parsed
     * @return the value of the string in milliseconds
     * or null if it can not be parsed
     */
    public static long toMillis(String s) {
        s = s.toLowerCase();
        long val = 0;
        StringBuilder working = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                working.append(s.charAt(i));
            } else if (TIME_SYMBOLS.containsKey(s.charAt(i))) {
                if (working.length() > 0) {
                    val += Misc.parseInt(working.toString(), 0) * TIME_SYMBOLS.get(s.charAt(i));
                    working = new StringBuilder();
                }
            }
        }
        if (working.length() != 0) {
            val += Misc.parseInt(working.toString(), 0);
        }
        return val;
    }
}
