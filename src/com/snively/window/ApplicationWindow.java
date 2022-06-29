package com.snively.window;

import javax.swing.JFrame;

import com.snively.Constants;

public class ApplicationWindow {

	private final JFrame frame;

	public ApplicationWindow(String title) {
		this.frame = new JFrame(title);

		frame.setSize(Constants.APPLICATION_WINDOW_SIZE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}
}
