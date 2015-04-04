package com.example.myweibo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.myweibo.R;
import com.example.myweibo.entity.Task;
import com.example.myweibo.inte.IWeiboActivity;
import com.example.myweibo.logic.MainService;

public class LoginActivity extends Activity implements IWeiboActivity {
	private TextView tv_demo;
	private Button bt_demo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		init();
		Intent intent = new Intent(this,MainService.class);
		startService(intent);
		
		bt_demo.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Task task = new Task(Task.TASK_WEIBO_LOGIN, null);
				MainService.addTask(task);
				MainService.addActivity(LoginActivity.this);
			}
		});
	}

	@Override
	public void init() {
		tv_demo = (TextView) findViewById(R.id.tv_demo);
		bt_demo = (Button) findViewById(R.id.bt_demo);
	}

	@Override
	public void refresh(Object... params) {
		String str = (String) params[0];
		if(str!=null){
			tv_demo.setText(str);
		}
		System.out.println("====界面更新了");
	}



}
