package com.zsgs.flightticketbooking.booking;

import com.zsgs.flightticketbooking.dto.Passenger;

import java.util.List;

import com.zsgs.flightticketbooking.dto.Booking;
import com.zsgs.flightticketbooking.dto.Flight;
import com.zsgs.flightticketbooking.repository.FlightRepository;

public class BookingModel implements BookingModelCallBack {
	private BookingModelControllerCallBack bookingController;
	
	public BookingModel(BookingModelControllerCallBack bookingController) {
		this.bookingController = bookingController;
	}
	
	public void passengerRegistration(String name,String password,int age, String emailId, long phoneNumber)
	{
		Passenger passenger = FlightRepository.getInstance().isValidPassenger(emailId,password);
		if(passenger!=null)
			bookingController.registrationResult("This EmailId is already exist...");
		else
		{
			FlightRepository.getInstance().addPassenger(name,password,age,emailId,phoneNumber);
			bookingController.registrationResult("Your account is added successfully");
		}
	}
	
	public boolean searchFlights(String destination)
	{
		List<Flight> flights= FlightRepository.getInstance().searchFlights(destination);
		bookingController.searchFlightResult(flights);
		if(flights.size()!=0)
			return true;
		return false;
	}
	
	public void bookFlightTickets(String emailId,int flightId,String bookingId,int ticketCount)
	{
		Flight currentFlight = FlightRepository.getInstance().getFlight(flightId);
		if(currentFlight.getAvailableSeats()>=ticketCount && bookingController.getPayment())
		{
			FlightRepository.getInstance().bookTickets(emailId,flightId,bookingId,ticketCount);
			
			Booking currBooking = FlightRepository.getInstance().getBooking(bookingId);
			
			Flight currFlight = FlightRepository.getInstance().getFlight(currBooking.getFlightId());
			
			float totalCost = bookingController.calculateTotalCost(currFlight, currBooking);
			
			currBooking.setTotalCost(totalCost);
			currFlight.setBookedTickets(ticketCount);
			
			Passenger currPassenger = FlightRepository.getInstance().getPassenger(currBooking.getEmailId());
			
			bookingController.bookingDetailSuccess(currFlight, currPassenger,currBooking);
		}
		else
			bookingController.bookingDetailFailure("only "+ currentFlight.getAvailableSeats()+" tickets are available.");
			
	}
	
	public interface BookingModelControllerCallBack
	{

		void searchFlightResult(List<Flight> flights);

		float calculateTotalCost(Flight currFlight, Booking currBooking);

		void bookingDetailSuccess(Flight currFlight, Passenger currPassenger, Booking currBooking);

		void bookingDetailFailure(String message);

		boolean getPayment();

		void registrationResult(String message);
		
	}

}
