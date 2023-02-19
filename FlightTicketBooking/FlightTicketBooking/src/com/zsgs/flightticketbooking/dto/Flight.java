package com.zsgs.flightticketbooking.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
	private int flightId;
	private String flightName;
	private String originAirport;
	private String destinationAirport;
	private float costPerTicket;
	private LocalDate journeyDate;
	private LocalTime journeyTime;
	private LocalTime onboardingTime;
	private int totalSeats;
	private int bookedTickets;
	
	public Flight(int flightId,String flightName,String originAirport,String destinationAirport, int totalSeats,float costPerTicket, LocalDate journeyDate,LocalTime journeyTime,LocalTime onboardingTime)
	{
		this.flightId = flightId;
		this.flightName = flightName;
		this.originAirport = originAirport;
		this.destinationAirport = destinationAirport;
		this.totalSeats = totalSeats;
		this.costPerTicket =costPerTicket;
		this.journeyDate = journeyDate;
		this.journeyTime = journeyTime;
		this.onboardingTime = onboardingTime;
	}
	
	public int getBookedTickets() {
		return bookedTickets;
	}

	public void setBookedTickets(int bookedTickets) {
		this.bookedTickets = bookedTickets;
	}

	public int getFlightId() {
		return flightId;
	}
	
	public String getFlightName() {
		return flightName;
	}
	
	public String getOriginAirport() {
		return originAirport;
	}
	
	public String getDestinationAirport() {
		return destinationAirport;
	}
	
	public int getTotalSeats() {
		return totalSeats;
	}
	
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	public int getAvailableSeats() {
		return getTotalSeats()-getBookedTickets();
	}

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

	public LocalTime getJourneyTime() {
		return journeyTime;
	}

	public void setJourneyTime(LocalTime journeyTime) {
		this.journeyTime = journeyTime;
	}

	public LocalTime getOnboardingTime() {
		return onboardingTime;
	}

	public float getCostPerTicket() {
		return costPerTicket;
	}	
}
