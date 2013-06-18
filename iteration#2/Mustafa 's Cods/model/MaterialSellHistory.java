package model;

import java.util.ArrayList;

public class MaterialSellHistory {

	public static ArrayList<MaterialSellHistory> materialHistory;

	String name;
	String cutomer;
	int qty;
	String dateAndTime;

	public MaterialSellHistory() {
		setName(name);
		setCutomer(cutomer);
		setQty(qty);
		setDateAndTime(dateAndTime);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCutomer() {
		return cutomer;
	}

	public void setCutomer(String cutomer) {
		this.cutomer = cutomer;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

}
