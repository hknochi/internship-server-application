package de.internship.server.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validator {
    public static int validateString(String value, int minLength, int maxLength, boolean requiresAlnum, boolean requiresAl, boolean isGenderChoice) {
        System.out.println("validation value:" + value);
        if ((requiresAlnum && requiresAl) || (requiresAlnum && isGenderChoice) || (requiresAl && isGenderChoice)) {
            return 1;
        }
        if (value.length() < minLength || value.length() > maxLength || value == null || value.equals("")) {
            return 2;
        }
        if (requiresAlnum) {
            Pattern patternAlphanumeric = Pattern.compile("^\\p{Alnum}{" + minLength + "," + maxLength + "}$");
            Matcher patternMatchAlphanumeric = patternAlphanumeric.matcher(value);
            if (!patternMatchAlphanumeric.find()) {
                return 3;
            }
        }
        if (requiresAl) {
            Pattern patternAlphabetic = Pattern.compile("^[a-zA-Z]*$");
            Matcher patternMatchAlphabetic = patternAlphabetic.matcher(value);
            if (!patternMatchAlphabetic.find()) {
                return 4;
            }
        }
        if (isGenderChoice) {
            if (!(value.equals("male") || value.equals("female") || value.equals("diverse"))) {
                return 5;
            }
        }
        return 0;

        /*
        --- return code value/meaning:
        0/successful
        1/contradictory parameters
        2/string length not within valid area (too long or too short)
        3/alphanumeric check result: not alphanumeric
        4/alphabetic check result: not alphabetic
        5/inadequate gender
         ---
         */
    }

    public static int validateInt(int yearOfBirth) {
        if (yearOfBirth < 1900) {
            return 1;
        } else if (yearOfBirth > 2019) {
            return 2;
        }
        return 0;
    }
    /*
    --- return code value/meaning:
    0/successful
    1/invalid int value (too low)
    2/invalid int value (too high)
    ---
     */

    public static int validateMsg(/*Long msgId,*/ String transmitterUsername, String receiverUsername, String msgContent, long sendTime) {
        /*if(msgId < 0) {
            return 1;
        }
        /*if(testMsgReg(msgId)) {
            return 2;
        }
        */
        if(transmitterUsername.equals(receiverUsername)) {
            return 3;
        }
        if(msgContent.length()<1) {
            return 4;
        }
        if(sendTime<0) {
            return 5;
        }
        return 0;
        /*
        --- return code value/meaning:
        0/successful
        1/invalid message id
        2/msg id duplicate
        3/transmitter equals receiver, that is not allowed
        4/empty message
        5/time travel is not allowed yet either
         */
    }




}
