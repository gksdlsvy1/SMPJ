package model.vo;

import java.util.Date;

import model.exception.IdPasswordNotMatchingException;

public class User {

	private Long user_no;
	private String email;
	private String password;
	private String name;
	private String phone;
	private int level;
	private Date create_time;
	private Date update_time;
	private String account_num;
	private String account_name;
	private int status;

	public User(String email, String password, String name, String phone, Date create_time, Date update_time,
			String account_num, String account_name) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.create_time = create_time;
		this.update_time = update_time;
		this.account_num = account_num;
		this.account_name = account_name;
		this.level = 1;
		this.status = 1;
	}

	public void setUserNo(Long user_no) {
		this.user_no = user_no;
	}

	public Long getUserNo() {
		return user_no;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public Date getCreateTime() {
		return create_time;
	}
	
	public Date getUpdateTime() {
		return update_time;
	}
	
	public String getAccountNum() {
		return account_num;
	}
	
	public String getAccountName() {
		return account_name;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public void changePassword(String oldPassword, String newPassword) {
		if (!password.equals(oldPassword))
			throw new IdPasswordNotMatchingException();
		this.password = newPassword;
	}

}
