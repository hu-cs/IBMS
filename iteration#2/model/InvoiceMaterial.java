package model;

import view.mainUi;

public class InvoiceMaterial {

	String name;
	String unit;
	float sellCost;
	float qty;

	public InvoiceMaterial(String name, String unit, float sellCost, float qty) {
		super();
		this.name = name;
		this.unit = unit;
		this.sellCost = sellCost;
		this.qty = qty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getSellCost() {
		return sellCost;
	}

	public void setSellCost(float sellCost) {
		this.sellCost = sellCost;
	}

	public float getQty() {
		return qty;
	}

	public void setQty(float qty) {
		this.qty = qty;
	}

	public double getTotal() {
		return getQty() * getSellCost();
	}

	public static void main(String[] args) {

	}
}
