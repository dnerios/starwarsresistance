package com.letscode.starwarresistence.shared.exceptions;

public class PontosInsuficientesException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public final String title = "Traidor tentado realizar negociações!";

	public PontosInsuficientesException(String arg0) {
		super(arg0);
	}

	public PontosInsuficientesException(String message, Throwable cause) {
		super(message, cause);
	}
}
