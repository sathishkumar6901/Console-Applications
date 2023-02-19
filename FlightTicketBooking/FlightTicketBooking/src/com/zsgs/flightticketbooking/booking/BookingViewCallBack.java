package com.zsgs.flightticketbooking.booking;

import java.util.List;

import com.zsgs.flightticketbooking.dto.Booking;
import com.zsgs.flightticketbooking.dto.Flight;
import com.zsgs.flightticketbooking.dto.Passenger;

public interface BookingViewCallBack {

	void searchFlightResult(List<Flight> flights);

	void registrationResult(String message);

	boolean getPayment();

	void bookingDetailFailure(String message);

	void bookingDetailSuccess(Flight currFlightId, Passenger currPassengerId, Booking currBookingId);

}
