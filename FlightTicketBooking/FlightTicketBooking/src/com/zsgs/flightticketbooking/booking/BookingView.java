package com.zsgs.flightticketbooking.booking;

import java.util.List;
import java.util.Scanner;

import com.zsgs.flightticketbooking.cancelling.CancellingView;
import com.zsgs.flightticketbooking.dto.Booking;
import com.zsgs.flightticketbooking.dto.Flight;
import com.zsgs.flightticketbooking.dto.Passenger;

public class BookingView implements BookingViewCallBack{
	private BookingControllerCallBack bookingController;
	private Scanner scanner = new Scanner(System.in);
	public BookingView()
	{
		bookingController = new BookingController(this);
	}
	
	public void booking()
	{
		boolean bookTicket = true;
		do {
			System.out.println("1.Passenger Registration \n2.Book Tickets \n3.Cancel Tickets \n4.Exit");
			int choice = scanner.nextInt();
			switch(choice){
				case 1: System.out.println("------>Passenger Registration Page<------\n");
						passengerRegistration();
						break;
				case 2: System.out.println("------>Ticket Booking Page<------\n");
						bookingTickets();
						break; 
				case 3: System.out.println("------>Ticket cancellation Page<------\n");
						CancellingView cancellingView = new CancellingView();
						cancellingView.toCancelTickets();
						break;
				case 4: bookTicket=false;
						break;
			}
		}while(bookTicket);
	}

	private void passengerRegistration() {
		
		System.out.println("Enter your Name:");
		String name = scanner.next();
		System.out.println("Enter your Password:");
		String password = scanner.next();
		System.out.println("Enter your Age:");
		int age = scanner.nextInt();
		System.out.println("Enter your EmailId:");
		String emailId = scanner.next();
		System.out.println("Enter your phone number:");
		long phoneNumber = scanner.nextLong();
		bookingController.passengerRegistration(name,password,age,emailId,phoneNumber);
	}

	private void bookingTickets() {
		
		System.out.println("Enter your EmailId:");
		String emailId = scanner.next();
		System.out.println("Enter your destination airport:");
		String destination = scanner.next();
					
		boolean isFlightAvailable = bookingController.searchFlights(destination);
		
		if(isFlightAvailable)
		{
			System.out.println("Enter the Flight Id:");
			int flightId = scanner.nextInt();
			System.out.println("Enter how many tickets you want?");
			int ticketCount = scanner.nextInt();
			bookingController.bookFlightTickets(emailId,flightId,ticketCount);
		}
	}
	
	public void registrationResult(String message)
	{
		System.out.println("----->"+message+"<-----\n");
	}
	
	public void searchFlightResult(List<Flight> flights)
	{
		if(flights.isEmpty())
			System.out.println("Sorry... Currently no flight is available for your destination");
		else
		{
			System.out.println("------>Flights List<------");
			for(Flight flight:flights)
			{
				System.out.println("Flight Id:		"+flight.getFlightId());
				System.out.println("Flight Name:		"+flight.getFlightName());
				System.out.println("Origin Airport:		"+flight.getOriginAirport());
				System.out.println("Destination Airport:	"+flight.getDestinationAirport());
				System.out.println("Available Tickets:	"+flight.getAvailableSeats());
				System.out.println("Journey Date:		"+flight.getJourneyDate());
				System.out.println("Journey Time:		"+flight.getJourneyTime().toString());
				System.out.println("----------------------------------");
			}
			System.out.println();
		}
	}
	
	public boolean getPayment()
	{
		System.out.println("Do you want to continue with the payment(yes/no):");
		String input = scanner.next();
		return input.equals("yes");
	}
	public void bookingDetailSuccess(Flight currFlight, Passenger currPassenger, Booking currBooking)
	{		
		System.out.println("Your payment "+currBooking.getTotalCost()+" transfered successfully!!!\n");
		System.out.println("Your Booking Id is "+currBooking.getBookingId());
		System.out.println();
		;
		System.out.println("------->Booking Ticket Details<-------\n");
		
		
		System.out.println("Passenger Name:		"+currPassenger.getPassengerName());
		System.out.println("Passenger EmailId:	"+currPassenger.getEmailId());
		System.out.println("Passenger Age:		"+currPassenger.getAge());
		System.out.println("Flight Id:		"+currFlight.getFlightId());
		System.out.println("Flight Name:		"+currFlight.getFlightName());
		System.out.println("Origin Airport:		"+currFlight.getOriginAirport());
		System.out.println("Destination Airport:	"+currFlight.getDestinationAirport());
		System.out.println("Journey Date:		"+currFlight.getJourneyDate());
		System.out.println("Journey Time:		"+currFlight.getJourneyTime());
		System.out.println("Onboarding Time:	"+currFlight.getOnboardingTime());
		System.out.println("Total cost:		"+currBooking.getTotalCost());
		
		System.out.println("----->You must Reach "+currFlight.getOriginAirport()+" Airport on/befor the onboarding time<-----\n");
	}
	
	public void bookingDetailFailure(String message)
	{
		System.out.println("----->"+message+"<-----\n");
	}
}
