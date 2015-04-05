package com.example.myweibo.ui;

import weibo4j.Weibo;

import com.example.myweibo.R;
import com.example.myweibo.R.layout;
import com.example.myweibo.util.AuthUtil;
import com.example.myweibo.util.JavaScriptInterface;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.Loader;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends Activity {
	public static final int  PBDIALOG_DIS = 1;
	
	private WebView wv_auth;
	private ProgressDialog pDialog;
	private String urlString = null;;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_web_view);
		urlString = AuthUtil.getAuthorizationURL();
		if(urlString==null){
			Toast.makeText(this, "获取授权URL失败", 3000).show();
			return;
		}
		init();
		load(urlString, wv_auth);
	}
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what==PBDIALOG_DIS){
				pDialog.dismiss();
			}
		};
	};
	
	public void init(){
		if(pDialog == null){
			pDialog = new ProgressDialog(this);
		}
		pDialog.show();
		wv_auth = (WebView) findViewById(R.id.wv_auth);
		//设置javascript是否可行
		wv_auth.getSettings().setJavaScriptEnabled(true);
		wv_auth.addJavascriptInterface(new JavaScriptInterface(), "Methords");
		wv_auth.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				load(url, view);
				return super.shouldOverrideUrlLoading(view, url);
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				
				if(url.equals("http://api.t.sina.com.cn/oauth/authorize")){
					
					String load = "javascript:window.Methords.getPin('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');";
					view.loadUrl(load);
					Intent intent = new Intent(WebViewActivity.this,AccesTokenActivity.class);
					startActivity(intent);
				}
			}
			
		});
		wv_auth.setWebChromeClient(new WebChromeClient(){
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if(newProgress==100){
				handler.sendEmptyMessage(PBDIALOG_DIS);
				}
				super.onProgressChanged(view, newProgress);
			}
			
		});
		
		
	}
	public void load(final String url,final WebView view){
		if(url==null||"".equals(url)){
			return;
		}
		new Thread(){
			public void run() {
				view.loadUrl(url);
			};
			
		}.start();
	}
}
