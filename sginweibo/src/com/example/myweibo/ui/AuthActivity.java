package com.example.myweibo.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.myweibo.R;

public class AuthActivity extends Activity {
	private Dialog dialog;
	private Button bt_begin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_auth);
		View dailogview = View.inflate(this, R.layout.dailog_begin, null);	
		dialog = new Dialog(this,R.style.myDialogTheme);
		dialog.setContentView(dailogview);
		dialog.show();
		bt_begin = (Button) dailogview.findViewById(R.id.bt_begin);
		bt_begin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AuthActivity.this,WebViewActivity.class);
				startActivity(intent);
			}
		});
		
}

}
