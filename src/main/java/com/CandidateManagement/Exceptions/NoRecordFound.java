package com.CandidateManagement.Exceptions;

import java.time.LocalTime;

public class NoRecordFound extends Exception {
	
	@Override
	public String getMessage() {
		return "NoRecordFound @"+LocalTime.now();
	}

}
