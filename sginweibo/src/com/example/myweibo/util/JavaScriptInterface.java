package com.example.myweibo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.PatternMatcher;

public class JavaScriptInterface {
	public static String PIN;
	/**
	 * »ñÈ¡PINÖµ
	 * @param html
	 */
	public void getPin(String html){
		String pin = null;
		String req="[1-9]{6}";
		Pattern pattern = Pattern.compile(req);
		Matcher matcher = pattern.matcher(html);
		if(matcher.find()){
			pin = matcher.group(0);
		}
		PIN = pin;
		System.out.println("pin ----> "+pin);
	};
}
