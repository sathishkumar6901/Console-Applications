package com.zsgs.bankingapplication.dto;

import java.util.ArrayList;
import java.util.List;

public class Admin {
	private String id;
	private String name;
	private List<String> notifications = new ArrayList<>();
	public Admin(String id, String name) {
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public List<String> getNotifications() {
		return notifications;
	}
	public void setNotifications(String notification) {
		notifications.add(notification);
	}
	public void removeNotification()
	{
		notifications.remove(0);
	}
}
