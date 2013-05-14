package model;

import java.util.ArrayList;

public class Customer {

	String name;
	String lastName;
	String fatherName;
	String address;
	float shopping;
	float payment;
	float companyDemand;

	public static ArrayList<Customer> customerList = new ArrayList<Customer>();

	public Customer(String name, String lastName, String fatherName,
			String address, float shopping, float payment, float companyDemand) {

		this.name = name;

		setName(name);
		setLastName(lastName);
		setFatherName(fatherName);
		setAddress(address);
		setShopping(shopping);
		setPayment(payment);
		setCompanyDemand(companyDemand);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getShopping() {
		return shopping;
	}

	public void setShopping(float shopping) {
		this.shopping = shopping;
	}

	public float getPayment() {
		return payment;
	}

	public void setPayment(float payment) {
		this.payment = payment;
	}

	public float getCompanyDemand() {
		return companyDemand;
	}

	public void setCompanyDemand(float companyDemand) {
		this.companyDemand = companyDemand;
	}

	public String toString() {
		return name;
	}

}
