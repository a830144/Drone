package vo;

import java.util.HashSet;
import java.util.Map;

public class User {
	private Integer userId;
	private String username;
	private String password;
	private String EMail;
	private String phone;
	private Map<String,HashSet<String>> functionOperations;
	public Map<String, HashSet<String>> getFunctionOperations() {
		return functionOperations;
	}
	public void setFunctionOperations(Map<String, HashSet<String>> functionOperations) {
		this.functionOperations = functionOperations;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public String getEMail() {
		return EMail;
	}

	public void setEMail(String eMail) {
		EMail = eMail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
