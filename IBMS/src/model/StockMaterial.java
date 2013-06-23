package model;

import java.util.ArrayList;

public class StockMaterial {
	public static ArrayList<StockMaterial> stockList = new ArrayList<StockMaterial>();
	String name;
	String unit;
	int qty;

	public StockMaterial(String name, String unit, int qty) {
		super();
		this.name = name;
		this.unit = unit;
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

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}
