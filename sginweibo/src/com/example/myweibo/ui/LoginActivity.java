package com.example.myweibo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.myweibo.R;
import com.example.myweibo.entity.Task;
import com.example.myweibo.inte.IWeiboActivity;
import com.example.myweibo.logic.MainService;

public class LoginActivity extends Activity implements IWeiboActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_login);
		Intent intent = new Intent(this,AuthActivity.class);
		startActivity(intent);
		/*init();
		Intent intent = new Intent(this,MainService.class);
		startService(intent);
		*/
		/*bt_demo.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Task task = new Task(Task.TASK_WEIBO_LOGIN, null);
				MainService.addTask(task);
				MainService.addActivity(LoginActivity.this);
			}
		});*/
	}

	@Override
	public void init() {
		
	}

	@Override
	public void refresh(Object... params) {
		/*String str = (String) params[0];
		if(str!=null){
			tv_demo.setText(str);
		}
		System.out.println("====界面更新了");*/
	}



}
