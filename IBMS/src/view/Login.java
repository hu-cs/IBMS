package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.ComponentOrientation;
import java.awt.Font;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jgoodies.forms.layout.Sizes;

import connection.DBConnection;
import controller.LogingConfirmBtn;

public class Login extends JFrame {
	private JTextField userNameTxf;
	private JPasswordField passwordField;

	public Login() {

		getAccounts();

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lockLabel = new JLabel();
		panel.add(lockLabel, BorderLayout.CENTER);
		lockLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lockLabel.setIcon(new ImageIcon(getClass().getResource(
				"/icon/Banner.png")));

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("17dlu:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("105px:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("min(75px;default):grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						RowSpec.decode("max(16dlu;default)"),
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(19dlu;default)"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, RowSpec.decode("5dlu"),
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), }));

		JComboBox userCombobox = new JComboBox();
		userCombobox.addItem(CompanyUsed.manager);
		userCombobox.addItem(CompanyUsed.accounter);
		panel_3.add(userCombobox, "16, 2, fill, default");

		JLabel selectUserLabel = new JLabel(CompanyUsed.selectUer);
		selectUserLabel
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		selectUserLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/user.png")));
		panel_3.add(selectUserLabel, "18, 2, right, default");

		userNameTxf = new JTextField();
		userNameTxf.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		userNameTxf.setEditable(false);
		userNameTxf.setText(model.Login.userAccountList.get(0).getUserName());
		panel_3.add(userNameTxf, "16, 4, fill, center");
		userNameTxf.setColumns(10);

		JLabel userNameLabel = new JLabel(CompanyUsed.userName);
		userNameLabel
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		userNameLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/User32.png")));
		panel_3.add(userNameLabel, "18, 4, right, default");

		passwordField = new JPasswordField();
		passwordField
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panel_3.add(passwordField, "16, 6, fill, center");

		JLabel passwordLabel = new JLabel(CompanyUsed.password);
		passwordLabel
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		passwordLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/password.png")));
		panel_3.add(passwordLabel, "18, 6, right, default");

		JComboBox dataYearIdCombobox = new JComboBox();
		panel_3.add(dataYearIdCombobox, "16, 8, fill, default");

		JLabel dataYearIdLabel = new JLabel(CompanyUsed.datayear);
		dataYearIdLabel
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		dataYearIdLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/data_year.png")));
		panel_3.add(dataYearIdLabel, "18, 8, right, default");

		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_3.add(buttonPanel, "14, 10, 5, 1, fill, fill");

		JButton cancelButton = new JButton(CompanyUsed.cancel);
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				setVisible(false);
			}
		});
		buttonPanel.add(cancelButton);

		setDataYearId(dataYearIdCombobox);
		JButton okButton = new JButton(CompanyUsed.login);
		// dataYearIdCombobox.setSelectedIndex(0);
		okButton.addActionListener(new LogingConfirmBtn(userNameTxf,
				passwordField, dataYearIdCombobox, this));
		buttonPanel.add(okButton);

		userCombobox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (((JComboBox<String>) e.getSource()).getSelectedIndex() == 0) {
					userNameTxf.setText(model.Login.userAccountList.get(0)
							.getUserName());
				} else {
					userNameTxf.setText(model.Login.userAccountList.get(1)
							.getUserName());
				}

			}
		});

		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png")).getImage());
		setSize(390, 386);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public static void main(String[] args) {

		try {
			UIManager
					.setLookAndFeel(new com.jtattoo.plaf.noire.NoireLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		new Login();

	}

	private void getAccounts() {

		String status = "";
		String userName = "";
		String password = "";

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT status,user_name,password FROM `user_account`");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				status = result.getString(1);
				userName = result.getString(2);
				password = result.getString(3);

				model.Login account = new model.Login(status, userName,
						password);
				model.Login.userAccountList.add(account);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setDataYearId(JComboBox<String> yearCombobox) {

		int dataYearId = 0;
		try {
			PreparedStatement statment = DBConnection.connection
					.prepareStatement("SELECT year_id FROM `data_year`");
			ResultSet result = statment.executeQuery();
			while (result.next()) {
				dataYearId = result.getInt(1);
				yearCombobox.addItem(dataYearId + "");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
