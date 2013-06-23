package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class SettingCompanyCharactristics implements ActionListener {

	JTextField name;
	JTextArea address;
	JTextField mobile;
	JTextField tell;
	JTextField logo;
	JTextField fax;
	JTextField website;
	JTextField email;
	JDialog dialog;

	public SettingCompanyCharactristics(JTextField name, JTextArea address,
			JTextField mobile, JTextField tell, JTextField logo,
			JTextField fax, JTextField website, JTextField email, JDialog dialog) {
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.tell = tell;
		this.logo = logo;
		this.fax = fax;
		this.website = website;
		this.email = email;
		this.dialog = dialog;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (imageValidation() != -1) {
			saveSetting();
			dialog.setVisible(false);

		}

	}

	private void saveSetting() {

		CompanyCharactristics.setName(name.getText());
		CompanyCharactristics.setAddress(address.getText());
		CompanyCharactristics.setLogo(logo.getText());
		CompanyCharactristics.setPhone(mobile.getText(), tell.getText());
		CompanyCharactristics.setFax(fax.getText());
		CompanyCharactristics.setWebSite(website.getText());
		CompanyCharactristics.setEmail(email.getText());
	}

	private int imageValidation() {

		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		try {
			String name = this.name.getText();
			String address = this.address.getText();
			String logo = this.logo.getText();
			int mobile = Integer.parseInt(this.mobile.getText());
			int tell = Integer.parseInt(this.tell.getText());

		} catch (NullPointerException e) {
			new JOptionPane().showMessageDialog(null,
					"لطفا اطلاعات را ده صورت درست و کامل وارد کنید!", "آگهی",
					JOptionPane.OK_OPTION);
			return -1;
		} catch (NumberFormatException e) {
			new JOptionPane().showMessageDialog(null,
					"شماره های تماس یا فکس وارد شده درست نیست، لطفا دقت کنید!",
					"", JOptionPane.OK_OPTION);
			return -1;
		}

		if ((name.getText().trim().isEmpty())
				|| (address.getText().trim().isEmpty())
				|| (mobile.getText().trim().isEmpty())
				|| (tell.getText().trim().isEmpty())) {
			new JOptionPane()
					.showMessageDialog(
							null,
							"یکی از اطلاعات مورد نیاز به صورت خالی وارد شده است،اطفا دقت نموده دوباره تلاش کنید!",
							"آگهی", JOptionPane.OK_OPTION);
			return -1;
		}

		String url = logo.getText();
		ImageIcon icon = new ImageIcon(url);
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();
		if ((width > 128) || (height > 128)) {
			new JOptionPane()
					.showMessageDialog(null,
							"اابعاد لوگو وارد شده بزرگ تر از 128*128است؛ لطفا لوگوی مناسب انتخاب کنید!");
			return -1;

		}

		System.out.println(width + "*" + height);

		return 0;
	}
}
