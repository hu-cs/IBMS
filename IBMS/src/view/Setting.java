package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import controller.CompanyCharactristics;
import controller.SelectLogo;
import controller.SettingCompanyCharactristics;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Setting extends JDialog {
	private JTextField nameTxf;
	private JTextField firstPhoneTxf;
	private JTextField secondPhoneTxf;
	private JTextField logoTxf;
	JTextArea addressTa;
	private JTextField emailTxf;
	private JTextField webSiteTxf;
	private JTextField faxTxf;

	public Setting() {

		JPanel buttonPanel = new JPanel();
		FlowLayout fl_buttonPanel = (FlowLayout) buttonPanel.getLayout();
		fl_buttonPanel.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton cancelButton = new JButton(CompanyUsed.cancel);
		cancelButton.setIcon(new ImageIcon(getClass().getResource(
				"/icon/cancel.png")));
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
		buttonPanel.add(cancelButton);

		JButton okButton = new JButton(CompanyUsed.okText);
		okButton.setIcon(new ImageIcon(getClass().getResource(
				"/icon/accept.png")));
		buttonPanel.add(okButton);

		JPanel contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("62px:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(29dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(35dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("50px:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(19dlu;default)"), },
						new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC,
								RowSpec.decode("max(15dlu;default)"),
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								RowSpec.decode("max(16dlu;default)"),
								FormFactory.RELATED_GAP_ROWSPEC,
								RowSpec.decode("max(40dlu;default)"),
								FormFactory.RELATED_GAP_ROWSPEC,
								RowSpec.decode("16dlu"),
								FormFactory.RELATED_GAP_ROWSPEC,
								RowSpec.decode("16dlu"),
								FormFactory.RELATED_GAP_ROWSPEC,
								RowSpec.decode("default:grow"),
								FormFactory.RELATED_GAP_ROWSPEC,
								RowSpec.decode("default:grow"),
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC,
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC, }));

		JLabel titleLabel = new JLabel("تنظیمات مشخصات شرکت");
		titleLabel.setForeground(Color.YELLOW);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		contentPanel.add(titleLabel, "3, 2, 18, 1, center, fill");

		nameTxf = new JTextField();
		nameTxf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		nameTxf.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		nameTxf.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPanel.add(nameTxf, "17, 6, 4, 1, fill, fill");
		nameTxf.setColumns(10);

		JLabel nameLabel = new JLabel(CompanyUsed.name);
		contentPanel.add(nameLabel, "22, 6");

		addressTa = new JTextArea();
		addressTa.setRows(1);
		addressTa.setAutoscrolls(false);
		addressTa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		addressTa.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		addressTa.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPanel.add(addressTa, "14, 8, 7, 1, fill, fill");

		JLabel addressLabel = new JLabel(CompanyUsed.address);
		contentPanel.add(addressLabel, "22, 8");

		secondPhoneTxf = new JTextField();
		secondPhoneTxf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		secondPhoneTxf
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		secondPhoneTxf.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPanel.add(secondPhoneTxf, "14, 10, 3, 1, fill, fill");
		secondPhoneTxf.setColumns(10);

		firstPhoneTxf = new JTextField();
		firstPhoneTxf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		firstPhoneTxf
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		firstPhoneTxf.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPanel.add(firstPhoneTxf, "18, 10, 3, 1, fill, fill");
		firstPhoneTxf.setColumns(10);

		JLabel phoneLable = new JLabel(CompanyUsed.tell);
		contentPanel.add(phoneLable, "22, 10");

		faxTxf = new JTextField();
		faxTxf.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		faxTxf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		faxTxf.setBorder(new LineBorder(Color.BLACK, 1, true));
		faxTxf.setText("0404303223");
		contentPanel.add(faxTxf, "18, 12, 3, 1, fill, fill");
		faxTxf.setColumns(10);

		JLabel label = new JLabel(CompanyUsed.fax);
		contentPanel.add(label, "22, 12");

		webSiteTxf = new JTextField();
		webSiteTxf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		webSiteTxf.setBorder(new LineBorder(Color.BLACK, 1, true));
		webSiteTxf.setText("www.shokufabahar.com");
		contentPanel.add(webSiteTxf, "14, 14, 7, 1, fill, fill");
		webSiteTxf.setColumns(10);

		JLabel label_2 = new JLabel(CompanyUsed.webSite);
		contentPanel.add(label_2, "22, 14");

		emailTxf = new JTextField();
		emailTxf.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		emailTxf.setBorder(new LineBorder(Color.BLACK, 1, true));
		emailTxf.setText("info@shokufabahar.com");
		contentPanel.add(emailTxf, "14, 16, 7, 1, fill, fill");
		emailTxf.setColumns(10);

		JLabel label_3 = new JLabel(CompanyUsed.email);
		contentPanel.add(label_3, "22, 16");

		logoTxf = new JTextField();
		logoTxf.setEditable(false);
		logoTxf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		logoTxf.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		logoTxf.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		contentPanel.add(logoTxf, "14, 20, 5, 1, fill, fill");
		logoTxf.setColumns(10);

		okButton.addActionListener(new SettingCompanyCharactristics(nameTxf,
				addressTa, firstPhoneTxf, secondPhoneTxf, logoTxf, faxTxf,
				webSiteTxf, emailTxf, this));

		JButton logoButton = new JButton("انتخاب");
		contentPanel.add(logoButton, "20, 20, right, default");
		logoButton.addActionListener(new SelectLogo(logoTxf));

		JLabel logoLabel = new JLabel(CompanyUsed.logo);
		contentPanel.add(logoLabel, "22, 20");

		loadData();
		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png"))
				.getImage());
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(423, 402);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("");
	}

	public static void main(String[] args) {

		try {
			UIManager
					.setLookAndFeel(new com.jtattoo.plaf.noire.NoireLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new Setting();

	}

	private void loadData() {
		nameTxf.setText(CompanyCharactristics.getName());
		addressTa.setText(CompanyCharactristics.getAddress());
		firstPhoneTxf.setText(CompanyCharactristics.getPhone());
		secondPhoneTxf.setText(CompanyCharactristics.getTell());
		logoTxf.setText(CompanyCharactristics.getLogo());
		faxTxf.setText(CompanyCharactristics.getFax());
		webSiteTxf.setText(CompanyCharactristics.getWebSite());
		emailTxf.setText(CompanyCharactristics.getEmail());

	}

}
