package com.example.myweibo.util;

import weibo4j.Weibo;
import weibo4j.WeiboException;
import weibo4j.http.AccessToken;
import weibo4j.http.RequestToken;

public class AuthUtil {

	private static Weibo weibo;
	private static RequestToken requestToken ;
	static{
		System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
		System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
		weibo = new Weibo();
	}
	
	/**
	 * ªÒ»° ⁄»®URL
	 * @return
	 */
	public static String getAuthorizationURL(){
		try {
			requestToken = weibo.getOAuthRequestToken();
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return requestToken.getAuthorizationURL();
	}
	
	public static void getAccessToken(String pin){
		try {
			AccessToken accessToken = requestToken.getAccessToken(pin);
			System.out.println("Got access token.");
			System.out.println("Access token: "+ accessToken.getToken());
			System.out.println("Access token secret: "+ accessToken.getTokenSecret());
			System.out.println(accessToken.getToken()+" "+accessToken.getTokenSecret());
			System.out.println("userID: "+accessToken.getUserId());
		}catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
