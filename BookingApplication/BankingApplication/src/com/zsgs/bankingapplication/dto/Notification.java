package com.zsgs.bankingapplication.dto;

public class Notification {

	private String customerId;
	private String requestType;
	private String requestResult = "pending";
	private String viewedResult = "unseen";
	private String replyMessage = "";

	public Notification(String customerId, String requestType) {
		this.customerId = customerId;
		this.requestType = requestType;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public String getRequestType() {
		return requestType;
	}

	public String getRequestResult() {
		return requestResult;
	}

	public void setRequestResult(String requestResult) {
		this.requestResult = requestResult;
	}

	public String getViewedResult() {
		return viewedResult;
	}

	public void setViewedResult(String viewedResult) {
		this.viewedResult = viewedResult;
	}

	public String getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}
}
