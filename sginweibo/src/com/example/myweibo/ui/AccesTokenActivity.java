package com.example.myweibo.ui;

import com.example.myweibo.R;
import com.example.myweibo.R.layout;
import com.example.myweibo.util.AuthUtil;
import com.example.myweibo.util.JavaScriptInterface;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AccesTokenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acces_token);
		
		String pin = JavaScriptInterface.PIN;
		
		while(pin==null){
			pin = JavaScriptInterface.PIN;
		}
		AuthUtil.getAccessToken(pin);
	}


}
