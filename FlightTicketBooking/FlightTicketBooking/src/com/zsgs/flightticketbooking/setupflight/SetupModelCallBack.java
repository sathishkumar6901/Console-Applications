package com.zsgs.flightticketbooking.setupflight;

import java.time.LocalDate;
import java.time.LocalTime;

public interface SetupModelCallBack {

	void addFlight(int id, String name, String destination, String destination2, int totalTickets, float costPerTicket, LocalDate journeyDate,
			LocalTime journeyTime, LocalTime onboardingTime);

	void changeTotalSeats(int id, int totalSeats);

	void changeFlightTiming(int id, LocalDate journeyDate, LocalTime journeyTime);

	void showFlights();

}
