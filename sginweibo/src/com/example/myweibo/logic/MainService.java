package com.example.myweibo.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.example.myweibo.entity.Task;
import com.example.myweibo.inte.IWeiboActivity;

public class MainService extends Service implements Runnable {
	/** 要处理的任务队列 */
	public static Queue<Task> task = new LinkedList<Task>();
	/** 存发起任务的UI的集合 */
	public static List<IWeiboActivity> appActivity = new ArrayList<IWeiboActivity>();
	/**线程循环*/private boolean isloop = false;

	/**
	 * 添加一个任务的方法
	 * 
	 * @param tTask
	 */
	public static void addTask(Task tTask) {
		System.out.println("====任务添加了");
		task.add(tTask);
	}

	/**
	 * 添加Activity的方法
	 */
	public static void addActivity(IWeiboActivity act) {
		System.out.println("====界面添加了");
		appActivity.add(act);
	}

	/**
	 * 通过act的name来获取这个act的方法
	 */
	public IWeiboActivity getActByName(String name) {
		System.out.println("====界面找到了");
		for (IWeiboActivity act : appActivity) {
			if (act.getClass().getName().indexOf(name) > 0) {
				return act;
			}
		}
		return null;
	}

	@Override
	public void onCreate() {
		System.out.println("====服务启动了");
		isloop = true;
		Thread thread = new Thread(this);
		thread.start();
	}

	/**
	 * 监听任务队列，一旦不为空，立即取出任务去执行
	 */
	@Override
	public void run() {
		while (isloop) {
			Task totask = null;
			// 如果队列不为空，取出任务
			if (!task.isEmpty()) {
				totask = task.poll();
			}
			// 将取出的任务去执行
			if (totask != null) {
				dotask(totask);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 执行任务的方法
	private void dotask(Task totask) {
		System.out.println("====任务执行了");
		Message message = Message.obtain();
		message.what = totask.getTaskId();
		switch (totask.getTaskId()) {
		case Task.TASK_WEIBO_LOGIN:
			String logininfo = "我登陆了";
			message.obj = logininfo;
			mHandler.sendMessage(message);
			break;
		}

	}

	// 消息处理者
	private Handler mHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Task.TASK_WEIBO_LOGIN:
				String logininfo = (String) msg.obj;
				System.out.println("====消息收到了");
				IWeiboActivity act = getActByName("LoginActivity");
				act.refresh(logininfo);
				break;

			}

		};
	};

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
