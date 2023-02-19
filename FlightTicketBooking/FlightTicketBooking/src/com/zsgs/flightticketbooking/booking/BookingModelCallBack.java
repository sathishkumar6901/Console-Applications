package com.zsgs.flightticketbooking.booking;


public interface BookingModelCallBack {

	boolean searchFlights(String destination);

	void bookFlightTickets(String emailId,int flightId,String bookingId, int ticketCount);

	void passengerRegistration(String name, String password, int age, String emailId, long phoneNumber);

}
