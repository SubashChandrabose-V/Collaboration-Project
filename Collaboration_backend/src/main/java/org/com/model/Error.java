package org.com.model;
/*
 * 	sending error message to the client 
 */
public class Error {
private int code;
private String message;
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
public Error(int code, String message) {
	super();
	this.code = code;
	this.message = message;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
}
