package view;

import javax.swing.JOptionPane;

public class DeletingMaterial extends JOptionPane {

	public DeletingMaterial() {
		showConfirmDialog(
				null,
				MaterialList.askForDelete);

	}

	public static void main(String[] args) {
		new DeletingMaterial();
	}

}
