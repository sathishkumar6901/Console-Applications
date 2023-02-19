package com.zsgs.flightticketbooking.login;

import java.util.Scanner;

import com.zsgs.flightticketbooking.booking.BookingView;
import com.zsgs.flightticketbooking.dto.Admin;
import com.zsgs.flightticketbooking.setupflight.SetupView;

public class LoginView implements LoginViewCallBack{
	private LoginControllerCallBack loginController;
	private Scanner scanner =new Scanner(System.in);
	
	private LoginView()
	{
		loginController = new LoginController(this);
	}
	
	public static void main(String args[])
	{
		LoginView loginView = new LoginView();
		loginView.mainMenu();
	}
	private void mainMenu()
	{
		boolean repeated = true;
		do {
			System.out.println("----------------->ZSGS Airport<-------------------\n");
			System.out.println("1.Admin Login \n2.Book Ticket \n3.Exit\n");
			System.out.println("Enter your choice:");
			int choice = scanner.nextInt();
			switch(choice)
			{
				case 1: checkAdminLogin();
						break;
				case 2: BookingView bookingView = new BookingView();
						bookingView.booking();
						break;
				case 3: repeated = false;
						break;
			}
		}while(repeated);
		
	}

	private void checkAdminLogin() {
		System.out.println("----->Welcome to Admin Login Page<------\n");
		System.out.println("Enter Admin Name:");
		String name = scanner.next();
		System.out.println("Enter Admin Password:");
		String password = scanner.next();
		loginController.checkValidAdmin(name,password);
	}
	public void loginSuccess(Admin adminUser)
	{
		System.out.println("---->Welcome "+adminUser.getAdminName()+"<----\n");
		SetupView setupView = new SetupView();
		setupView.toCreate();
	}
	public void loginFailure(String message)
	{
		System.out.println(message);
		System.out.println();
	}

}
