package com.zsgs.flightticketbooking.cancelling;

import com.zsgs.flightticketbooking.dto.Booking;
import com.zsgs.flightticketbooking.dto.Flight;
import com.zsgs.flightticketbooking.dto.Passenger;
import com.zsgs.flightticketbooking.repository.FlightRepository;

public class CancellingModel implements CancellingModelCallBack{
	private CancellingModelControllerCallBack cancellingController;
	
	public CancellingModel(CancellingModelControllerCallBack cancellingController)
	{
		this.cancellingController = cancellingController;
	}
	
	public void toCancelBookingTickets(String emailId, String bookingId)
	{
		Booking currBooking = FlightRepository.getInstance().getBooking(bookingId);
		if(currBooking!=null)
		{
			Passenger passenger = FlightRepository.getInstance().getPassenger(currBooking.getEmailId());
			if(passenger!=null)
			{
				Flight currFlight = FlightRepository.getInstance().getFlight(currBooking.getFlightId());
				cancellingController.toCancelBooking(currFlight, currBooking);
				FlightRepository.getInstance().removeBookingId(bookingId);
			}
			else
				cancellingController.passengerIdNotExistError("Please enter a valid EmailId and Password...");	
		}
		else
			cancellingController.bookingIdError("The given booking id is not exist!!!");
	}
	
	public interface CancellingModelControllerCallBack
	{

		void toCancelBooking(Flight currFlight, Booking currBooking);

		void passengerIdNotExistError(String message);

		void bookingIdError(String message);
		
	}

}
