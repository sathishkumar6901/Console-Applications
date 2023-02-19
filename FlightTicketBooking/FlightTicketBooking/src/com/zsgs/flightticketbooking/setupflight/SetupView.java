package com.zsgs.flightticketbooking.setupflight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.zsgs.flightticketbooking.dto.Flight;
public class SetupView implements SetupViewCallBack{
	private SetupControllerCallBack setupController;
	private Scanner scanner = new Scanner(System.in);
	
	public SetupView()
	{
		setupController = new SetupController(this);
	}
	
	public void toCreate() {
		setupFlight();
	}

	private void setupFlight() {
		boolean proceed=true;
		do {
			System.out.println("1.Add Flight \n2.Change Total Number of Seats \n3.Change Journey date and Time \n4.View Flights \n5.exit\n");
			System.out.println("Enter your choice:");
			int choice = scanner.nextInt();
			switch(choice)
			{
				case 1: {
						System.out.println("Enter Flight ID:");
						int id =scanner.nextInt();
						System.out.println("Enter Flight Name:");
						String name = scanner.next();
						System.out.println("Enter Origin Airport of a Flight:");
						String origin = scanner.next();
						System.out.println("Enter Destination Airport of a Flight:");
						String destination = scanner.next();
						System.out.println("Enter Total Number of seats");
						int totalseats = scanner.nextInt();
						System.out.println("Enter cost per ticket:");
						float costPerTicket = scanner.nextFloat();
						
						System.out.println("Enter Journey Date:");
						String date=scanner.next();
						DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
						LocalDate journeyDate = LocalDate.parse(date, dateFormat);
						
						System.out.println("Enter Journey Time:");
						String time =scanner.next();
					    LocalTime journeyTime=LocalTime.parse(time);
					    
					    setupController.addFlight(id,name,origin,destination,totalseats,costPerTicket,journeyDate,journeyTime);
					    break;
						}
				
				case 2: {
						System.out.println("Enter Flight Id:");
						int id = scanner.nextInt();
						System.out.println("Enter number of seats to be change:");
						int totalSeats = scanner.nextInt();
						setupController.changeTotalSeats(id,totalSeats);
						break;
						}
						
				case 3: {
						System.out.println("Enter Flight Id:");
						int id = scanner.nextInt();
						System.out.println("Enter the Journey date to be change:");
						String date=scanner.next();
						DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu");
						LocalDate journeyDate = LocalDate.parse(date, dateFormat);
						System.out.println("Enter Journey Time:");
						String time =scanner.next();
					    LocalTime journeyTime=LocalTime.parse(time);
					    setupController.changeFlightTiming(id,journeyDate,journeyTime);
					    break;
						}
				case 4: {
						setupController.showFlights();
						break;
						}
				case 5: {
						proceed = false;
						break;
						}
				}
		}while(proceed);	
	}
	public void addFlightResult(String message)
	{
		System.out.println("----->"+message+"<-----\n");
	}
	
	public void changeTicketsResult(String message)
	{
		System.out.println("----->"+message+"<-----\n");
	}
	
	public void changeFlightTimingResult(String message)
	{
		System.out.println("----->"+message+"<-----\n");
	}
	
	public void showFlightDetails(List<Flight> flights)
	{
		System.out.println("------>Flights List<------");
		for(Flight flight:flights)
		{
			System.out.println("Flight Id:		"+flight.getFlightId());
			System.out.println("Flight Name:		"+flight.getFlightName());
			System.out.println("Origin Airport:		"+flight.getOriginAirport());
			System.out.println("Destination Airport:	"+flight.getDestinationAirport());
			System.out.println("Total Tickets:		"+flight.getTotalSeats());
			System.out.println("Booked Tickets:		"+flight.getBookedTickets());
			System.out.println("Available Tickets:	"+flight.getAvailableSeats());
			System.out.println("Journey Date:		"+flight.getJourneyDate());
			System.out.println("Journey Time:		"+flight.getJourneyTime().toString());
			System.out.println("----------------------------------");
		}
		System.out.println();
	}
}
