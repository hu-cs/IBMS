package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Timer;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class Time extends JDialog {

	JLabel timeLabel;

	public Time() {
		timeLabel = new JLabel();

		javax.swing.Timer timer = new javax.swing.Timer(1000,
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						setTime(timeLabel);
					}
				});
		timer.start();
		timeLabel.setOpaque(false);
		timeLabel.setFont(new Font("tahoma", Font.PLAIN, 18));
		timeLabel.setForeground(Color.yellow);

		// setTime(timeLabel);
		add(timeLabel);

		getContentPane().setBackground(Color.black);
		setSize(100, 100);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Time();

	}

	private void setTime(JLabel timeLabel) {

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());

		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int isPm = cal.get(Calendar.AM_PM);

		String am_pm = "";
		if (isPm == 0) {
			am_pm = "صبح";
		} else {
			am_pm = "بعد از ظهر";

		}

		System.out.println(am_pm);

		String time = hour + ":" + minute + ":" + second + "   " + am_pm;
		System.err.println(time);
		timeLabel.setText(time);
	}

}
