package com.letscode.starwarresistence.shared.exceptions;

public class TraidorNegocianteException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public final String title = "Traidor tentado realizar negociações!";

	public TraidorNegocianteException(String arg0) {
		super(arg0);
	}

	public TraidorNegocianteException(String message, Throwable cause) {
		super(message, cause);
	}
}
