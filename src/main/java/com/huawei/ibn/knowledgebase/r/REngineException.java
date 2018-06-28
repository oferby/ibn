package com.huawei.ibn.knowledgebase.r;

public class REngineException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -312978268880398717L;

	public REngineException(final String what) {
		super("R error evaluating " + what);
	}
}
