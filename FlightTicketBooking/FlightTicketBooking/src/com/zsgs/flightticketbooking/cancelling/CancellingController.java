package com.zsgs.flightticketbooking.cancelling;

import com.zsgs.flightticketbooking.cancelling.CancellingModel.CancellingModelControllerCallBack;
import com.zsgs.flightticketbooking.dto.Booking;
import com.zsgs.flightticketbooking.dto.Flight;

public class CancellingController implements CancellingControllerCallBack, CancellingModelControllerCallBack {
	private CancellingViewCallBack cancellingView;
	private CancellingModelCallBack cancellingModel;
	
	public CancellingController(CancellingViewCallBack cancellingView)
	{
		this.cancellingView = cancellingView;
		this.cancellingModel = new CancellingModel(this);
	}
	
	public void toCancelBookingTickets(String emailId, String bookingId)
	{
		cancellingModel.toCancelBookingTickets(emailId, bookingId);
	}
	
	public void toCancelBooking(Flight currFlight, Booking currBooking)
	{
		float totalTicketCost = currBooking.getTotalCost();
		int ticketCount = currBooking.getTicketCount();
		
		int bookedTickets = currFlight.getBookedTickets() - ticketCount;
		
		currFlight.setBookedTickets(bookedTickets);
		
		float refundCost = (totalTicketCost*75)/100;
		String message = "Your "+currBooking.getBookingId()+" ticket is cancelled successfully!!!!\n\n";
		message+="Your 75 percentage of booking cost("+refundCost+") will be returned within 2 working days!!!\\n";
		
		cancellingView.onCancelSuccess(message);
	}
	
	public void bookingIdError(String message)
	{
		cancellingView.bookingIdError(message);
	}
	
	public void passengerIdNotExistError(String message)
	{
		cancellingView.passengerIdNotExistError(message);
	}
}
