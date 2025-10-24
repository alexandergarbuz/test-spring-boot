package com.garbuz.web.dto;

import java.util.Objects;

public class HelloDto {
	
	private String firstName;
	private String lastName;
	private String message;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, message);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof HelloDto))
			return false;
		HelloDto other = (HelloDto) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(message, other.message);
	}
	@Override
	public String toString() {
		return "HelloDto [firstName=" + firstName + ", lastName=" + lastName + ", message=" + message + "]";
	}
}
