package com.lxc.base.http.gson.adapter;

/**
 * @author lxc
 */
public class NumberUtils {
	
	public static boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
		
	}
	
	public static boolean isFloat(String str) {
		try {
			Float.parseFloat(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
		
	}
}
