package hibernate;

import java.util.Scanner;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

@MappedSuperclass
public abstract class Human {
	@Id
	@Column(name = "MaSV")
	protected String code;
	
	@Column(name = "Ten")
	protected String fullname;

	@Transient
	protected String address;
	
	

	public Human() {
		this.code = "xxxxxxxxxxxxxxxxxxxxxxxx";
	}

	public Human(String code) {
		this.code = code;
	}

	public Human(String code, String fullname) {
		this.code = code;
		this.fullname = fullname;
	}

	public Human(String address, String code, String fullname) {
		this.address = address;
		this.code = code;
		this.fullname = fullname;
	}

	// Update abstract
	public abstract void enterInfo(Scanner sc);

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

//	@Override
//	public String toString() {
//		return "Human [address=" + address + ", code=" + code + ", fullname=" + fullname + "]";
//	}

}
