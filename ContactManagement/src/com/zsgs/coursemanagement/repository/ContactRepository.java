package com.zsgs.coursemanagement.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.zsgs.coursemanagement.dto.Contacts;

public class ContactRepository {
	private static ContactRepository contactDbInstance;
	
	List<Contacts> contacts = new ArrayList<>();
	
	public static ContactRepository getInstance() {
		if(contactDbInstance==null)
			contactDbInstance = new ContactRepository();
		return contactDbInstance;
	}
	
	public Contacts getContact(String name) {
		for(Contacts contact:contacts)
			if(contact.getName().equals(name))
				return contact;
		return null;
	}
	
	public void addNewContact(String name, String emailId, String phoneNumber, LocalDate dob) {
		contacts.add(new Contacts(name,emailId,phoneNumber,dob));
		
	}
	
	public void addExistingContact(Contacts contact, String phoneNumber) {
		contact.addMobileNumber(phoneNumber);	
	}

	public boolean deleteContact(Contacts contact, String phoneNumber) {
		for(String s:contact.getMobileNumber()) {
			if(s.equals(phoneNumber)) {
				contact.removeNumber(phoneNumber);
				return true;
			}
		}
		return false;
	}

	public void editName(Contacts contact, String name, String newName) {
		contact.setName(newName);
	}

	public boolean changePhoneNumber(Contacts contact, String phoneNumber, String newPhoneNumber) {
		for(String s:contact.getMobileNumber())
			if(s.equals(phoneNumber)) {
				contact.removeNumber(phoneNumber);
				contact.addMobileNumber(newPhoneNumber);
				return true;
			}
		return false;
	}

	public void editEmailId(Contacts contact, String emailId) {
		contact.setEmailId(emailId);
	}

	public void editDOB(Contacts contact, LocalDate dob) {
		contact.setDob(dob);
		
	}

	public List<Contacts> searchByName(String name) {
		List<Contacts> contact = new ArrayList<>();
		for(Contacts currContact:contacts)
			if(currContact.getName().contains(name))
				contact.add(currContact);	
		return contact;
	}

	public List<Contacts> searchByPhoneNumber(String phoneNumber) {
		List<Contacts> contact = new ArrayList<>();
		for(Contacts currContact:contacts) {
			for(String number: currContact.getMobileNumber())
				if(number.contains(phoneNumber))
					contact.add(currContact);
		}
		return contact;
	}

	public List<Contacts> getAllContacts() {
		return contacts;
	}
}