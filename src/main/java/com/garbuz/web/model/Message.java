package com.garbuz.web.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Message")
public class Message {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;
	private String firstName;
	private String lastName;
	private String message;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
		return Objects.hash(firstName, id, lastName, message);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Message))
			return false;
		Message other = (Message) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(message, other.message);
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", message=" + message
				+ "]";
	}

}
