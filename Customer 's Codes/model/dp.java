package model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jdesktop.swingx.JXDatePicker;

public class dp extends JFrame {

	public dp() {

		final JXDatePicker date = new JXDatePicker();

		JButton ok = new JButton("Print");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (date.getEditor().getText().equals("")) {
					new JOptionPane().showMessageDialog(null,
							"Please select any date");
					return;
				} else {
					System.out.println(date.getEditor().getText());
				}

			}
		});
		add(ok, BorderLayout.SOUTH);
		add(date, BorderLayout.NORTH);

		setSize(400, 440);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new dp();
	}

}
