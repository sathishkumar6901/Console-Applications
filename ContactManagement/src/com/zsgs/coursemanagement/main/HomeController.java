package com.zsgs.coursemanagement.main;

import java.util.List;

import com.zsgs.coursemanagement.dto.Contacts;
import com.zsgs.coursemanagement.main.HomeModel.HomeModelControllerCallBack;

public class HomeController implements HomeControllerCallBack, HomeModelControllerCallBack {
	private HomeViewCallBack homeView;
	private HomeModelCallBack homeModel;
	
	public HomeController(HomeViewCallBack homeView) {
		this.homeView = homeView;
		this.homeModel = new HomeModel(this);
	}
	
	public void viewAllContacts() {
		homeModel.viewAllContacts();
	}
	
	/***********Model to Controller*************/
	public void noContacts(String message) {
		homeView.noContacts(message);
	}

	public void printContacts(List<Contacts> contacts) {
		homeView.printContacts(contacts);
	}

}
