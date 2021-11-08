package com.letscode.starwarresistence.shared.exceptions;

public class NaoHaRebeldesException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public final String title = "Já não se fazem rebeldes como antigamente...";

	public NaoHaRebeldesException(String arg0) {
		super(arg0);
	}

	public NaoHaRebeldesException(String message, Throwable cause) {
		super(message, cause);
	}
}
