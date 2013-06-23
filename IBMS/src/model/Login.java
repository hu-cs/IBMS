package model;

import java.util.ArrayList;

public class Login {

	String status;
	String userName;
	String passWord;

	public static ArrayList<Login> userAccountList = new ArrayList<Login>();

	public Login(String status, String userName, String passWord) {
		super();
		this.status = status;
		this.userName = userName;
		this.passWord = passWord;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
