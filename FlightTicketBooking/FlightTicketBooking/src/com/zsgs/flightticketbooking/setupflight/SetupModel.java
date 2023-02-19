package com.zsgs.flightticketbooking.setupflight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.zsgs.flightticketbooking.dto.Flight;
import com.zsgs.flightticketbooking.repository.FlightRepository;

public class SetupModel implements SetupModelCallBack{
	private SetupModelControllerCallBack setupController;
	
	public SetupModel(SetupModelControllerCallBack setupController) {
		this.setupController = setupController;
	}
	
	public void addFlight(int id, String name,String origin, String destination, int totalseats,float costPerTicket, LocalDate journeyDate,LocalTime journeyTime,LocalTime onboardingTime)
	{
		Flight currentFlight = FlightRepository.getInstance().getFlight(id);
		if(currentFlight!=null)
			setupController.addFlightResult("Sorry the given flight id is already exist!!!");
		else
		{
			FlightRepository.getInstance().addFlightDetails(id,name,origin,destination,totalseats,costPerTicket,journeyDate,journeyTime,onboardingTime);
			setupController.addFlightResult("Flight added successfully!!!");
		}
	}

	public void changeTotalSeats(int id, int totalSeats)
	{
		Flight currentFlight = FlightRepository.getInstance().getFlight(id);
		if(currentFlight!=null)
		{
			currentFlight.setTotalSeats(totalSeats);
			setupController.changeTicketsResult("Total number of the seats of this flight is changed successfully!!!");
		}
		else
			setupController.changeTicketsResult("Sorry the given flight id is not exist!!!");
	}
	
	public void changeFlightTiming(int id, LocalDate journeyDate, LocalTime journeyTime)
	{
		Flight currentFlight = FlightRepository.getInstance().getFlight(id);
		if(currentFlight!=null)
		{
			currentFlight.setJourneyDate(journeyDate);
			currentFlight.setJourneyTime(journeyTime);
			setupController.changeFlightTimingResult("The Journey Date and Time of this Flight is changed successfully!!!");
		}
		else
			setupController.changeFlightTimingResult("Sorry the given flight id is not exist!!!");
	}
	
	public void showFlights()
	{
		List<Flight> flights = FlightRepository.getInstance().showFlights();
		setupController.showFlightDetails(flights);
	}
	
	public interface SetupModelControllerCallBack
	{

		void addFlightResult(String message);

		void showFlightDetails(List<Flight> flights);

		void changeFlightTimingResult(String message);

		void changeTicketsResult(String message);
		
	}

}
