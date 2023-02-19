package com.zsgs.flightticketbooking.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.zsgs.flightticketbooking.dto.Admin;
import com.zsgs.flightticketbooking.dto.AdminCredentials;
import com.zsgs.flightticketbooking.dto.PassengerCredentials;
import com.zsgs.flightticketbooking.dto.Flight;
import com.zsgs.flightticketbooking.dto.Passenger;
import com.zsgs.flightticketbooking.dto.Booking;

public class FlightRepository {
	private static FlightRepository flightDbInstance;
	
	private List<AdminCredentials> adminCredentials = new ArrayList<>();
	private List<PassengerCredentials> passengerCredentials = new ArrayList<>();
	private List<Flight> flights = new ArrayList<>();
	private List<Booking> bookings = new ArrayList<>();
	
	public static FlightRepository getInstance()
	{
		if(flightDbInstance==null)
		{
			flightDbInstance = new FlightRepository();
			flightDbInstance.addAdmin();
		}
		return flightDbInstance;
	}

	private void addAdmin() {
		adminCredentials.add(new AdminCredentials(001,"Sathish_Krishna","zsgs"));
	}

	public Admin checkAdminCredencials(String name, String password) {
		for(AdminCredentials credentials:adminCredentials)
		{
			if(credentials.getAdminName().equals(name) && credentials.getAdminPassword().equals(password))
				return credentials;
		}
		return null;
	}

	public void addFlightDetails(int id, String name, String origin,String destination, int totalseats, float costPerTicket, LocalDate journeyDate,LocalTime journeyTime, LocalTime onboardingTime) {
		flights.add(new Flight(id,name,origin,destination,totalseats,costPerTicket,journeyDate,journeyTime,onboardingTime));	
	}
	
	public Passenger isValidPassenger(String emailId, String password) {
		for(PassengerCredentials passengerCredential:passengerCredentials)
		{
			if(passengerCredential.getEmailId().equals(emailId) && passengerCredential.getPassword().equals(password))
				return passengerCredential;
		}
		return null;
	}
	
	public void addPassenger(String name, String password, int age, String emailId, long phoneNumber) {
		passengerCredentials.add(new PassengerCredentials(name,password,age,emailId,phoneNumber));
	}
	
	public List<Flight> searchFlights(String destination) {
		List<Flight> flightList = new ArrayList<>();
		for(Flight flight:flights)
			if(flight.getDestinationAirport().equals(destination))
				flightList.add(flight);
		return flightList;
	}

	public void bookTickets(String emailId,int flightId,String bookingId, int ticketCount) {
		bookings.add(new Booking(emailId,flightId,bookingId,ticketCount));
	}

	public Flight getFlight(int flightId) {
		for(Flight flight:flights)
			if(flight.getFlightId()==flightId)
				return flight;
		return null;
	}	
	
	public Passenger getPassenger(String emailId) {
		for(PassengerCredentials passengerCredential:passengerCredentials)
		{
			if(passengerCredential.getEmailId().equals(emailId))
				return passengerCredential;
		}
		return null;
	}
	
	public Booking getBooking(String bookingId) {
		for(Booking booking:bookings)
			if(booking.getBookingId().equals(bookingId))
				return booking;
		return null;
	}

	public void removeBookingId(String bookingId) {
		for(Booking booking:bookings)
			if(booking.getBookingId().equals(bookingId))
			{
				bookings.remove(booking);
				break;
			}		
	}

	public List<Flight> showFlights() {
		List<Flight> flightList = new ArrayList<>();
		for(Flight flight:flights)
		{
			flightList.add(flight);
		}
		return flightList;
	}
}