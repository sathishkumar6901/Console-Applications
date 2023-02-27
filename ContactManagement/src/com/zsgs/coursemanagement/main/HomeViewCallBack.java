package com.zsgs.coursemanagement.main;

import java.util.List;

import com.zsgs.coursemanagement.dto.Contacts;

public interface HomeViewCallBack {

	void noContacts(String message);

	void printContacts(List<Contacts> contacts);

}
