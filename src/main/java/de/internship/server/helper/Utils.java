package de.internship.server.helper;

import java.util.Date;

public class Utils {
    public static String generateJson(int status, String message) {
        return "{ \"status\": " + status + "," + "\"message\": " + "\"" + message + "\"" + " }";
    }

    public static long getTimeInMs() {
        Date date = new Date();
        return date.getTime();
    }
}

// INFO: Hab' noch ein Leerzeichen bei vor der letzten Klammer hinzugefügt 24/11/19 - 12/11
