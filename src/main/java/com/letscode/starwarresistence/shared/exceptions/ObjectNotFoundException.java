package com.letscode.starwarresistence.shared.exceptions;

public class ObjectNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public final String title = "Algo de errado não está certo...";

	public ObjectNotFoundException(String arg0) {
		super(arg0);
	}

	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
