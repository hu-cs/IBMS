package model;

public class StockMaterial {
	String name;
	String unit;
	int qty;

	public StockMaterial(String name, String unit, int qty) {
		setName(name);
		setUnit(unit);
		setQty(qty);
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
