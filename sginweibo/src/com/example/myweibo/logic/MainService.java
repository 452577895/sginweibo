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
	/** Ҫ������������ */
	public static Queue<Task> task = new LinkedList<Task>();
	/** �淢�������UI�ļ��� */
	public static List<IWeiboActivity> appActivity = new ArrayList<IWeiboActivity>();
	/**�߳�ѭ��*/private boolean isloop = false;

	/**
	 * ���һ������ķ���
	 * 
	 * @param tTask
	 */
	public static void addTask(Task tTask) {
		System.out.println("====���������");
		task.add(tTask);
	}

	/**
	 * ���Activity�ķ���
	 */
	public static void addActivity(IWeiboActivity act) {
		System.out.println("====���������");
		appActivity.add(act);
	}

	/**
	 * ͨ��act��name����ȡ���act�ķ���
	 */
	public IWeiboActivity getActByName(String name) {
		System.out.println("====�����ҵ���");
		for (IWeiboActivity act : appActivity) {
			if (act.getClass().getName().indexOf(name) > 0) {
				return act;
			}
		}
		return null;
	}

	@Override
	public void onCreate() {
		System.out.println("====����������");
		isloop = true;
		Thread thread = new Thread(this);
		thread.start();
	}

	/**
	 * ����������У�һ����Ϊ�գ�����ȡ������ȥִ��
	 */
	@Override
	public void run() {
		while (isloop) {
			Task totask = null;
			// ������в�Ϊ�գ�ȡ������
			if (!task.isEmpty()) {
				totask = task.poll();
			}
			// ��ȡ��������ȥִ��
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

	// ִ������ķ���
	private void dotask(Task totask) {
		System.out.println("====����ִ����");
		Message message = Message.obtain();
		message.what = totask.getTaskId();
		switch (totask.getTaskId()) {
		case Task.TASK_WEIBO_LOGIN:
			String logininfo = "�ҵ�½��";
			message.obj = logininfo;
			mHandler.sendMessage(message);
			break;
		}

	}

	// ��Ϣ������
	private Handler mHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Task.TASK_WEIBO_LOGIN:
				String logininfo = (String) msg.obj;
				System.out.println("====��Ϣ�յ���");
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
