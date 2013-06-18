package view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import controller.EditManagerAccoutnsPassword;
import controller.EditOfficerPasswordAccount;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ui.renderers.ComAddItemRenderer;

public class UserAccount extends JDialog {
	private JTextField managerUserNameTxf;
	private JPasswordField managerLastpassField;
	private JPasswordField managerNewpassField;
	private JTextField officerUserNameTxf;
	private JPasswordField officerLastPass;
	private JPasswordField officerNewPass;

	public UserAccount() {

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getContentPane().add(tabbedPane, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("حسابرس", null, panel_1, null);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("9px:grow"),
				ColumnSpec.decode("max(65dlu;default):grow"),
				ColumnSpec.decode("5px"), ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				RowSpec.decode("6dlu"), FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("24px"), }));

		managerUserNameTxf = new JTextField();
		managerUserNameTxf.setEditable(false);
		panel_1.add(managerUserNameTxf, "2, 2, fill, fill");
		managerUserNameTxf.setHorizontalAlignment(SwingConstants.RIGHT);
		managerUserNameTxf.setColumns(10);

		JLabel managerUserNameLbl = new JLabel(CompanyUsed.userName);
		panel_1.add(managerUserNameLbl, "4, 2, right, default");

		managerLastpassField = new JPasswordField();
		managerLastpassField
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panel_1.add(managerLastpassField, "2, 4, fill, fill");

		JLabel managerLastPasswordLbl = new JLabel("کلمه عبور قبلی");
		panel_1.add(managerLastPasswordLbl, "4, 4, right, default");

		managerNewpassField = new JPasswordField();
		managerNewpassField
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panel_1.add(managerNewpassField, "2, 6, fill, fill");

		JLabel managerNewPasswordLbl = new JLabel("کلمه عبور جدید");
		panel_1.add(managerNewPasswordLbl, "4, 6, right, default");

		JButton managerCancelButton = new JButton(CompanyUsed.cancel);
		managerCancelButton.setIcon(new ImageIcon(getClass().getResource(
				"/icon/cancel.png")));
		managerCancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		panel_1.add(managerCancelButton, "2, 10, right, default");

		JButton mangerOkbutton = new JButton(CompanyUsed.okText);
		mangerOkbutton.setIcon(new ImageIcon(getClass().getResource(
				"/icon/accept.png")));
		mangerOkbutton.addActionListener(new EditManagerAccoutnsPassword(
				managerLastpassField, managerNewpassField, this));
		panel_1.add(mangerOkbutton, "4, 10, left, default");

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("حسابدار", null, panel_2, null);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("9px:grow"), ColumnSpec.decode("65dlu:grow"),
				ColumnSpec.decode("5px"), ColumnSpec.decode("default:grow"),
				ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				RowSpec.decode("6dlu"), FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("24px"), }));

		officerUserNameTxf = new JTextField();
		officerUserNameTxf.setHorizontalAlignment(SwingConstants.RIGHT);
		officerUserNameTxf.setEditable(false);
		panel_2.add(officerUserNameTxf, "2, 2, fill, fill");
		officerUserNameTxf.setColumns(10);

		JLabel officerUserNameLbl = new JLabel(CompanyUsed.userName);
		panel_2.add(officerUserNameLbl, "4, 2, right, default");

		officerLastPass = new JPasswordField();
		officerLastPass
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panel_2.add(officerLastPass, "2, 4, fill, fill");

		JLabel officerLastPassLbl = new JLabel("کلمه عبور قبلی");
		panel_2.add(officerLastPassLbl, "4, 4, right, default");

		officerNewPass = new JPasswordField();
		officerNewPass
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panel_2.add(officerNewPass, "2, 6, fill, fill");

		JLabel officerNewPassLbl = new JLabel("کلمه عبور جدید");
		panel_2.add(officerNewPassLbl, "4, 6, right, default");

		JButton officerCancelBtn = new JButton(CompanyUsed.cancel);
		officerCancelBtn.setIcon(new ImageIcon(getClass().getResource(
				"/icon/cancel.png")));
		officerCancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		panel_2.add(officerCancelBtn, "2, 10, right, default");

		JButton officerOkButton = new JButton(CompanyUsed.okText);
		officerOkButton.setIcon(new ImageIcon(getClass().getResource(
				"/icon/accept.png")));
		officerOkButton.addActionListener(new EditOfficerPasswordAccount(
				officerLastPass, officerNewPass, this));
		panel_2.add(officerOkButton, "4, 10, left, default");

		setUserName(managerUserNameTxf, officerUserNameTxf);

		setSize(304, 189);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel(new com.jtattoo.plaf.noire.NoireLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		new UserAccount();

	}

	private void setUserName(JTextField managerTxf, JTextField officerTxf) {

		ArrayList<String> accounts = new ArrayList<String>();
		try {
			PreparedStatement statement = connection.DBConnection.connection
					.prepareStatement("SELECT user_name FROM `user_account`");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				accounts.add(result.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		managerTxf.setText(accounts.get(0));
		officerTxf.setText(accounts.get(1));

	}

}
