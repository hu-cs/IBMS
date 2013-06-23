package view;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class DeletingYearId {

	public DeletingYearId() {
		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		JOptionPane question = new JOptionPane();
		question.showConfirmDialog(null,
				"اگر پاک کنید، تمام اطلاعات مربوط به این سال پاک خواهد شد، آیا مطمئن هستید؟");
	}

	public static void main(String[] args) {
		new DeletingYearId();
	}
}
