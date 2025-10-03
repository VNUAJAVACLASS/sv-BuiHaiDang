package model;

public class User {
	private String username;
	private String password;
	private String fullname;
	private Byte role;
	private String email;
	private String mobile;
	private String address;

	public User(String username, String password, String fullname, Byte role, String email, String mobile,
			String address) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}
	
	

	


	
	public Byte getRole() {
		return role;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Byte isRole() {
		return role;
	}

	public void setRole(Byte role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
