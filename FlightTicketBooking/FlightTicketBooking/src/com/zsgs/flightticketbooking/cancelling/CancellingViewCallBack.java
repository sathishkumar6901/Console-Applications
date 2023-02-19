package com.zsgs.flightticketbooking.cancelling;


public interface CancellingViewCallBack {

	void bookingIdError(String message);

	void passengerIdNotExistError(String message);

	void onCancelSuccess(String message);

}
