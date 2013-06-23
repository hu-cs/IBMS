package view;

import javax.swing.JOptionPane;

public class DeletingCustomer extends JOptionPane {

	public DeletingCustomer() {
		showConfirmDialog(
				null,
				"در صورت پاک کردن، مشتری مورد نظر از لیست شما پاک خواهد شد، آیا هنوز مایل هستید ؟");

	}

	public static void main(String[] args) {
		new DeletingCustomer();
	}

}
