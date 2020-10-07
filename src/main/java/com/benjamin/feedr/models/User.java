package com.benjamin.feedr.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author benjamin
 * @version 1.3.0
 * @since 2020-10-06
 */
@Entity
@Table(name = "FEEDR_USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email_address")
	private String email;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;

	public User() {
	}

	/**
	 * Constructor to initialize User
	 * 
	 * @param firstName : String
	 * @param lastName  : String
	 * @param email     : String
	 * @param username  : String
	 * @param password  : String
	 */
	public User(String firstName, String lastName, String email, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	/**
	 * Returns first name of User
	 * 
	 * @return firstName : String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets first name of User
	 * 
	 * @param firstName : String
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns last name of User
	 * 
	 * @return lastName : String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets last name of User
	 * 
	 * @param lastName : String
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns email of User
	 * 
	 * @return email : String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email of User
	 * 
	 * @param email : String
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns username of User
	 * 
	 * @return username : String
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets username of User
	 * 
	 * @param username : String
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns password of User
	 * 
	 * @return password : String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password of User
	 * 
	 * @param password : String
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Returns userId of User
	 * 
	 * @return userId : String
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets userId of User
	 * 
	 * @param userId : String
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Returns instance variables of User as Strings
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", username=" + username + ", password=" + password + "]";
	}
}
