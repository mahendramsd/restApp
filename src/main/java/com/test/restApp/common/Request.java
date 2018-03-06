package com.test.restApp.common;

import java.io.Serializable;

// TODO: Auto-generated Javadoc

/**
 * The Class Request.
 */
public class Request implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 381990188489940223L;
	
	/** The data. */
	private Object data;
	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	
	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Object data) {
		this.data = data;
	}

	
}
