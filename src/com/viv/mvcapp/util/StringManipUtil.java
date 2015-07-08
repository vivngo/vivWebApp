package com.viv.mvcapp.util;

public class StringManipUtil {
	private StringManipUtil() {
		throw new AssertionError();
	}
	
	public static String capitalizeFirstOnly(String original) {
        if (original == null) {
            return null;
        } else {
            String modified = original.toLowerCase();
            modified = Character.toUpperCase(modified.charAt(0)) + modified.substring(1, modified.length());
            return modified;
        }
    }
    
    public static String combineNames(String first, String last) {
        return first + " " + last;
    }
    
    public static String changeNullToEmpty(String s) {
        if (s == null) {
            return "";
        } else {
            return s;
        }
    }
}
