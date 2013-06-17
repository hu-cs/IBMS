package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.mainUi;

public class Material {

	private int id;
	private String name;
	private String unit;
	private float finishedCost;
	private float sellCost;

	public static ArrayList<Material> materialArray = new ArrayList<Material>();

	public Material(String name, String unit, float finishedCost, float sellCost) {
		this.name = name;
		this.unit = unit;
		this.finishedCost = finishedCost;
		this.sellCost = sellCost;
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

	public float getFinishedCost() {
		return finishedCost;
	}

	public void setFinishedCost(float finishedCost) {
		this.finishedCost = finishedCost;
	}

	public float getSellCost() {
		return sellCost;
	}

	public void setSellCost(float sellCost) {
		this.sellCost = sellCost;
	}

	public String toString() {
		return getName();
	}

	//
	// public static Material load(int id) {
	// List<Material> result = list(String.valueOf(id));
	// if (result.size() > 0)
	// return result.get(0);
	// return null;
	// }
	//
	// public static List<Material> list() {
	// return list("%");
	// }
	//
	// public static List<Material> list(String likeId) {
	// PreparedStatement query;
	// try {
	// query = DBConnection.connection
	// .prepareStatement("select material_id, material_name, material_unit, "
	// + "material_finished_cost, material_sell_cost, material_description, "
	// + "data_year_id from material where material_id like ?");
	//
	// query.setString(1, likeId);
	//
	// System.out.println("Query => [" + query + "]");
	//
	// ResultSet loadResult = query.executeQuery();
	//
	// List<Material> list = new ArrayList<Material>();
	//
	// while (loadResult.next()) {
	// Material loadedMaterial = new Material(loadResult.getString(2),
	// loadResult.getString(3), loadResult.getString(6),
	// loadResult.getFloat(4), loadResult.getFloat(5));
	// loadedMaterial.id = loadResult.getInt(1);
	//
	// list.add(loadedMaterial);
	// }
	//
	// return list;
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// return null;
	// }
	//
	// // public static int save(Material marterial) {
	// //
	// // }
	// //
	// public static int update(Material material) {
	// PreparedStatement query;
	// try {
	// query = DBConnection.connection
	// .prepareStatement("update material set material_name = ?, material_unit = ?, "
	// +
	// "material_finished_cost = ?, material_sell_cost = ?, material_description = ? where material_id = ?");
	//
	// query.setString(1, material.getName());
	// query.setString(2, material.getUnit());
	// query.setFloat(3, material.getFinishedCost());
	// query.setFloat(4, material.getSellCost());
	// query.setString(5, material.getDetails());
	// query.setInt(6, material.id);
	//
	// System.out.println("Query => [" + query + "]");
	//
	// return query.executeUpdate();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// return -1;
	// }
	//
	// //
	// // public static int delete(Material material) {
	// //
	// // }
	//
	// public static void updateMaterialAndStockLists(JTable mainTable, int
	// yaerId)
	// throws ClassNotFoundException, SQLException {
	// ArrayList<Material> materialArray = new ArrayList<Material>();
	// PreparedStatement statement = DBConnection.connection
	// .prepareStatement("select material_name" + ",material_unit"
	// + ",material_finished_cost" + ",material_sell_cost"
	// + ",material_description "
	// + "from material where data_year_id = ?");
	// statement.setInt(1, yaerId);
	// ResultSet result = statement.executeQuery();
	// while (result.next()) {
	// name = result.getString(1);
	// unit = result.getString(2);
	// finishedCost = result.getFloat(3);
	// sellCost = result.getFloat(4);
	// details = result.getString(5);
	// Material tempMaterial = new Material(name, unit, details,
	// finishedCost, sellCost);
	//
	// materialArray.add(tempMaterial);
	// System.out.println(tempMaterial.getName());
	// }
	//
	// ((DefaultTableModel) mainTable.getModel()).setRowCount(0);
	// for (Material temp : materialArray) {
	// System.out.println(temp.getName());
	// ((DefaultTableModel) mainTable.getModel()).addRow(new String[] {
	// temp.getSellCost() + "", temp.getFinishedCost() + "",
	// temp.getUnit(), temp.getName(),
	// mainTable.getRowCount() + 1 + "" });
	// }
	//
	// }

	public static void main(String... args) {
		// updateMaterialAndStockLists(mainTable, yaerId)
		// System.out.println(Material.load(3).getName());

		// for (Material mat : Material.list())
		// System.out.println(mat.getName());
		// Customer ali = new Customer("tamim", "a", "haf", "asdf", "" ,
		// payment, companyDemand)
		//
	}
}
