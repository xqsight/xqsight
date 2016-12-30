package com.xqsight.common.model;

import java.io.Serializable;

public abstract class Model extends BaseModel implements Serializable {

	private static final long serialVersionUID = 5859337383077120660L;
	
	public abstract Serializable getPK();

}
