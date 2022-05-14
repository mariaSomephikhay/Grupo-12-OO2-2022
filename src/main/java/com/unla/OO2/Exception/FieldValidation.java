package com.unla.OO2.Exception;

public class FieldValidation extends Exception{
	private static final long serialVersionUID = -4995433707591853255L;
	private String fieldName;
	public FieldValidation(String message, String fieldName) {
		super(message);
		this.fieldName = fieldName;
	}
	public String getFieldName() {
		return this.fieldName;
	}
}