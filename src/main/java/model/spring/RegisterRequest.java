package model.spring;

public class RegisterRequest {

	private String email;
	private String password;
	private String confirmPassword;
	private String name;
	private String phone;
	private String account_num;
	private String account_name;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAccountNum() {
		return account_num;
	}
	
	public void setAccountNum(String account_num) {
		this.account_num = account_num;
	}
	
	public String getAccountName() {
		return account_name;
	}
	
	public void setAccountName(String account_name) {
		this.account_name = account_name;
	}

	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirmPassword);
	}

}
