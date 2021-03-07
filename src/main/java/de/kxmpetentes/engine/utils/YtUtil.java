package de.kxmpetentes.engine.utils;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kxmpetentes
 * Website: kxmpetentes.de
 * GitHub: git.kxmpetentes.de
 * Erstellt am: 07.03.2021 um 01:10
 */

public class YtUtil {

    private YtUtil() {
    }

    public final static Pattern yturl = Pattern.compile("^(?:https?:\\/\\/)?(?:(?:www\\.)?)?(?:youtube\\.com|youtu\\.be)\\/.*?(?:embed|e|v|watch.*?v=)?\\/?([-_a-z0-9]{10,})?(?:&?index=\\d+)?(?>(?:playlist\\?|&)?list=([^#\\\\&\\?]{12,}))?", Pattern.CASE_INSENSITIVE);
    private final static Pattern youtubeCode = Pattern.compile("^[A-Za-z0-9_-]{11}$");

    /**
     * checks if it could be a youtube videocode
     *
     * @param videocode code to check
     * @return could be a code
     */
    public static boolean isValidYoutubeCode(String videocode) {
        return youtubeCode.matcher(videocode).matches();
    }

    /**
     * Extracts the videocode from an url
     *
     * @param url youtube link
     * @return videocode
     */
    public static String extractCodeFromUrl(String url) {
        Matcher matcher = yturl.matcher(url);
        if (matcher.find()) {
            if (matcher.group(1) != null) {
                return matcher.group(1);
            }
        }
        return url;
    }

    /**
     * Extracts the playlistcode from a yt url
     *
     * @param url the url
     * @return playlistcode || null if not found
     */
    public static String getPlayListCode(String url) {
        Matcher matcher = yturl.matcher(url);
        if (matcher.find()) {
            if (matcher.groupCount() == 2) {
                return matcher.group(2);
            }
        }
        return null;
    }

    /**
     * @param videocode youtubecode
     * @return whats in the <title> tag on a youtube page
     */
    public static String getTitleFromPage(String videocode) {
        String ret = "";
        try {
            URL loginurl = new URL("https://www.youtube.com/watch?v=" + videocode);
            URLConnection yc = loginurl.openConnection();
            yc.setConnectTimeout(10 * 1000);
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            StringBuilder input = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                input.append(inputLine);
            in.close();
            int start = input.indexOf("<title>");
            int end = input.indexOf("</title>");
            ret = input.substring(start + 7, end - 10);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return StringEscapeUtils.unescapeHtml4(ret);
    }
}