package com.example.myweibo.entity;

import java.util.Map;

public class Task {
	public static final int TASK_WEIBO_LOGIN = 0;
	
	
	
	private int taskId;
	private Map<String, Object> taskParam;
	public Task(int taskId, Map<String, Object> taskParam) {
		super();
		this.taskId = taskId;
		this.taskParam = taskParam;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public Map<String, Object> getTaskParam() {
		return taskParam;
	}
	public void setTaskParam(Map<String, Object> taskParam) {
		this.taskParam = taskParam;
	}
	

}
