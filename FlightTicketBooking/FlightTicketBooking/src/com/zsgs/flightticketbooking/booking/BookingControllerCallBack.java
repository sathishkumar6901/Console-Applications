package com.zsgs.flightticketbooking.booking;


public interface BookingControllerCallBack {

	boolean searchFlights(String destination);

	void bookFlightTickets(String emailId,int flightId,int ticketCount);

	void passengerRegistration(String name, String password, int age, String emailId, long phoneNumber);

}
