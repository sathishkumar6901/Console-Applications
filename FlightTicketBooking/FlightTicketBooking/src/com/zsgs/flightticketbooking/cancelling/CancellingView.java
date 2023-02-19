package com.zsgs.flightticketbooking.cancelling;

import java.util.Scanner;

public class CancellingView implements CancellingViewCallBack{
	private CancellingControllerCallBack cancellingController;
	private Scanner scanner = new Scanner(System.in);
	
	public CancellingView()
	{
		cancellingController = new CancellingController(this);
	}
	
	public void toCancelTickets() {
		System.out.println("Enter your Email Id:");
		String emailId = scanner.next();
		System.out.println("Enter your Booking Id");
		String bookingId = scanner.next();
		cancellingController.toCancelBookingTickets(emailId, bookingId);
	}
	
	public void onCancelSuccess(String message)
	{
		System.out.println(message);
	}
	
	public void bookingIdError(String message)
	{
		System.out.println(message);
		System.out.println();
	}
	
	public void passengerIdNotExistError(String message)
	{
		System.out.println(message);
		System.out.println();
	}
}
