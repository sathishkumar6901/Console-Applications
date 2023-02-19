package com.zsgs.flightticketbooking.setupflight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.zsgs.flightticketbooking.dto.Flight;
import com.zsgs.flightticketbooking.setupflight.SetupModel.SetupModelControllerCallBack;

public class SetupController implements SetupControllerCallBack, SetupModelControllerCallBack{
	private SetupViewCallBack setupView;
	private SetupModelCallBack setupModel;
	
	public SetupController(SetupViewCallBack setupView)
	{
		this.setupView = setupView;
		this.setupModel = new SetupModel(this);
	}
	
	public void addFlight(int id, String name,String origin, String destination, int totalseats,float costPerTicket, LocalDate journeyDate,LocalTime journeyTime)
	{
		LocalTime onboardingTime=journeyTime.minusHours(2);
		setupModel.addFlight(id,name.toUpperCase(),origin.toUpperCase(),destination.toUpperCase(),totalseats,costPerTicket,journeyDate,journeyTime,onboardingTime);
	}
	
	public void changeTotalSeats(int id, int totalSeats)
	{
		setupModel.changeTotalSeats(id, totalSeats);
	}
	
	public void changeFlightTiming(int id, LocalDate journeyDate, LocalTime journeyTime)
	{
		setupModel.changeFlightTiming(id, journeyDate, journeyTime);
	}
	
	public void showFlights()
	{
		setupModel.showFlights();
	}
	
	public void addFlightResult(String message)
	{
		setupView.addFlightResult(message);
	}
	
	public void changeTicketsResult(String message)
	{
		setupView.changeTicketsResult(message);
	}
	
	public void changeFlightTimingResult(String message)
	{
		setupView.changeFlightTimingResult(message);
	}
	
	public void showFlightDetails(List<Flight> flights)
	{
		setupView.showFlightDetails(flights);
	}

}
