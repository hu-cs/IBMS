package model;

import java.util.ArrayList;

public class SoldMaterial {

	public static ArrayList<SoldMaterial> soldMaterial = new ArrayList<SoldMaterial>();
	String name;
	String unit;
	int qty;

	public SoldMaterial(String name, String unit, int qty) {
		setName(name);
		setQty(qty);
		setUnit(unit);
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}
