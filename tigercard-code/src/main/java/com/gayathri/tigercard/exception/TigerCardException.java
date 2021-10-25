package com.gayathri.tigercard.exception;

import lombok.Data;

@Data
public class TigerCardException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private TigerCardErrorCode code;

	public TigerCardException(final TigerCardErrorCode code) {
		super();
		this.code = code;
	}

	public TigerCardException(final TigerCardErrorCode code, final Throwable e) {
		super(e);
		this.code = code;
	}

	public TigerCardException(final TigerCardErrorCode code, final String message) {
		super(message);
		this.code = code;
	}

	public TigerCardException(final TigerCardErrorCode code, final String message, final Throwable e) {
		super(message, e);
		this.code = code;
	}

}
