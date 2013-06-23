package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SelectLogo implements ActionListener {

	JTextField url;

	public SelectLogo(JTextField url) {
		this.url = url;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		select();

	}

	private void select() {

		try {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(
					"Image", "jpg", "png", "gif"));
			int selection = fileChooser.showOpenDialog(fileChooser);
			File file = fileChooser.getSelectedFile();
			url.setText(file.getAbsolutePath());

		} catch (NullPointerException e) {
		}

	}
}
