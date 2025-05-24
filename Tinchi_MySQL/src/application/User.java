package application;

import java.time.LocalDate;

public class User {
	private int userID;
	private String fullName;
	private boolean userType; // Giang vien: true
	private String email;
	private LocalDate birthday;
	private boolean gender; // Nam; true
	
	
	public User() {
	}


	public User(String fullName, boolean userType, String email, LocalDate birthday, boolean gender) {
		this.fullName = fullName;
		this.userType = userType;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
	}


	public User(int userID, String fullName, boolean userType, String email, LocalDate birthday, boolean gender) {
		super();
		this.userID = userID;
		this.fullName = fullName;
		this.userType = userType;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public boolean isUserType() {
		return userType;
	}


	public void setUserType(boolean userType) {
		this.userType = userType;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDate getBirthday() {
		return birthday;
	}


	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}


	public boolean isGender() {
		return gender;
	}


	public void setGender(boolean gender) {
		this.gender = gender;
	}


	
	
	
}
