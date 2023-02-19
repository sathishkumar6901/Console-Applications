package com.zsgs.flightticketbooking.dto;

public class Booking {
	private String emailId;
	private String bookingId;
	private int flightId;
	private int ticketCount;
	private float totalCost;
	
	public Booking(String emailId,int flightId,String bookingId, int ticketCount) {
		this.emailId =emailId;
		this.flightId = flightId;
		this.bookingId = bookingId;
		this.ticketCount = ticketCount;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public int getFlightId() {
		return flightId;
	}
	
	public int getTicketCount() {
		return ticketCount;
	}
	
	public float getTotalCost() {
		return totalCost;
	}
	
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public String getBookingId() {
		return bookingId;
	}
	
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
}
