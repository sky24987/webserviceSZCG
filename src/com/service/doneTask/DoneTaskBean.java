package com.service.doneTask;

import java.util.Map;

public class DoneTaskBean {
	/**
	 * �ǹ��¼�ID��
	 */
	private String id;
	/**
	 * �ǹ��¼��еİ�����������м�Ϊ�����нڵ��������ƣ�ֵ��Ӧ������Ƶ�ֵ
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
