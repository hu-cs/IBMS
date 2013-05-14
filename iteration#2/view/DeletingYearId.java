package view;

import javax.swing.JOptionPane;

public class DeletingYearId {

	public DeletingYearId() {
		JOptionPane question = new JOptionPane();
		question.showConfirmDialog(null,
				"اگر پاک کنید، تمام اطلاعات مربوط به این سال پاک خواهد شد، آیا مطمئن هستید؟");
	}

	public static void main(String[] args) {
		new DeletingYearId();
	}
}
