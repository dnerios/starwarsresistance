package com.letscode.starwarresistence.shared.exceptions;

public class NaoHaTraidoresException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public final String title = "Sem traidores!";

	public NaoHaTraidoresException(String arg0) {
		super(arg0);
	}

	public NaoHaTraidoresException(String message, Throwable cause) {
		super(message, cause);
	}
}
