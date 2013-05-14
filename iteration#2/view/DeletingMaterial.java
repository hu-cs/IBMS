package view;

import javax.swing.JOptionPane;

public class DeletingMaterial extends JOptionPane {

	public DeletingMaterial() {
		showConfirmDialog(
				null,
				"در صورت پاک کردن، جنس مورد نظر از لیست شما پاک خواهد شد، آیا هنوز مایل هستید ؟");

	}

	public static void main(String[] args) {
		new DeletingMaterial();
	}

}
