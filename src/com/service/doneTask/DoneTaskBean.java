package com.service.doneTask;

import java.util.Map;

public class DoneTaskBean {
	/**
	 * 城管事件ID号
	 */
	private String id;
	/**
	 * 城管事件中的办理情况，其中键为流程中节点的意见名称，值对应意见名称的值
	 */
	private Map comment;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Map getComment() {
		return comment;
	}
	public void setComment(Map comment) {
		this.comment = comment;
	}
	
}
