package com.forward.slash.duolingojournal;

/**
 * Created by dotua on 09-Jun-15.
 */
public class Utility {
    public static boolean tryParseInt(String value)
    {
        try
        {
            Integer.parseInt(value);
            return true;
        } catch(NumberFormatException nfe)
        {
            return false;
        }
    }

    public static boolean tryParseDouble(String value)
    {
        try
        {
            Double.parseDouble(value);
            return true;
        } catch(NumberFormatException nfe)
        {
            return false;
        }
    }

    public static int hhmmtomm (int hh, int mm) {
        if (hh>=0 && mm>=0) {
            return hh * 60 + mm;
        }
        else
            return 0;
    }

    public static int mmtohh (int mm) {
        if (mm>=0) {
            return mm / 60;
        }
        else
            return 0;
    }

    public static int mmtommResidual (int mm) {
        if (mm>=0) {
            return mm % 60;
        }
        else
            return 0;
    }

    /**
     * return true if all strings in focusedState are false
     * otherwise, return true if at least one of them is true
     * @param focusedState
     * @return
     */
    public static boolean isAllFalse (String[] focusedState)
    {
        for(String s:focusedState) {
            boolean focused = Boolean.parseBoolean(s);
            if (focused)
                return false; //not all are false
        }
        return true; // all are false
    }


}
