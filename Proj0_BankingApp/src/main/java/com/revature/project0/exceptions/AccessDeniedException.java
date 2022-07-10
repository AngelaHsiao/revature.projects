package com.revature.project0.exceptions;

public class AccessDeniedException extends Exception{

	public String getMessage() {
		return "You are not authorized to access this feature";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
