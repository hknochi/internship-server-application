package de.internship.server.helper;

public class Utils {
    public static String generateJson(int status, String message) {
        return "{ \"status\": " + status + "," + "\"message\": " + "\"" + message + "\"" + " }";
    }
}

// INFO: Hab' noch ein Leerzeichen bei vor der letzten Klammer hinzugefügt 24/11/19 - 12/11
