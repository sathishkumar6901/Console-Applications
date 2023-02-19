package com.zsgs.flightticketbooking.setupflight;

import java.util.List;

import com.zsgs.flightticketbooking.dto.Flight;

public interface SetupViewCallBack {

	void addFlightResult(String message);
	
	void changeTicketsResult(String message);

	void changeFlightTimingResult(String message);

	void showFlightDetails(List<Flight> flights);

}
