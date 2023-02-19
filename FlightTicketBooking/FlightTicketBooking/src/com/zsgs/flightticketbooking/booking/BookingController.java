package com.zsgs.flightticketbooking.booking;

import java.util.List;

import com.zsgs.flightticketbooking.booking.BookingModel.BookingModelControllerCallBack;
import com.zsgs.flightticketbooking.dto.Booking;
import com.zsgs.flightticketbooking.dto.Flight;
import com.zsgs.flightticketbooking.dto.Passenger;

public class BookingController implements BookingControllerCallBack, BookingModelControllerCallBack {
	private static int value = 1;
	private BookingViewCallBack bookingView;
	private BookingModelCallBack bookingModel;
	public BookingController(BookingViewCallBack bookingView) {
		this.bookingView = bookingView;
		this.bookingModel = new BookingModel(this);
	}
	
	public void passengerRegistration(String name,String password,int age,String emailId, long phoneNumber)
	{
		bookingModel.passengerRegistration(name, password,age, emailId, phoneNumber);
	}
	
	public boolean searchFlights(String destination)
	{
		return bookingModel.searchFlights(destination.toUpperCase());
	}
	
	public void bookFlightTickets(String emailId,int flightId,int ticketCount)
	{
		String bookingId = "SK00"+emailId.substring(0,3)+""+String.valueOf(value)+"00KS";
		value++;
		bookingModel.bookFlightTickets(emailId,flightId,bookingId,ticketCount);
	}
	
	public void registrationResult(String message)
	{
		bookingView.registrationResult(message);
	}
	
	public void searchFlightResult(List<Flight> flights)
	{
		bookingView.searchFlightResult(flights);
	}
	
	public boolean getPayment()
	{
		return bookingView.getPayment();
	}
	
	public float calculateTotalCost(Flight currFlight, Booking currBooking)
	{
		int percentageOfTickets = (currFlight.getAvailableSeats()/currFlight.getTotalSeats())*100;
		int ticketCount = currBooking.getTicketCount();
		float costPerTicket = currFlight.getCostPerTicket();
		float totalCost;
		
		if(percentageOfTickets>=75)
			totalCost = ticketCount * costPerTicket * 1f;
		else if(percentageOfTickets>=50)
			totalCost = ticketCount * costPerTicket * 1.4f;
		else if(percentageOfTickets>=20)
			totalCost = ticketCount * costPerTicket * 1.8f;
		else
			totalCost = ticketCount * costPerTicket * 2f;
		
		return totalCost;
	}
	public void bookingDetailSuccess(Flight currFlight, Passenger currPassenger, Booking currBooking)
	{		
		bookingView.bookingDetailSuccess(currFlight, currPassenger, currBooking);
	}

	public void bookingDetailFailure(String message)
	{
		bookingView.bookingDetailFailure(message);
	}
}
