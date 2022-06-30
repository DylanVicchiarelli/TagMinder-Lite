package com.snively.window;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.snively.Constants;
import com.snively.window.listeners.ButtonConnectToReaderActionListener;
import com.snively.window.listeners.ButtonDisconnectFromReaderActionListener;

public class ApplicationWindow {

	private final JFrame frame;

	private final JButton buttonConnectToReader = new JButton("Connect");
	private final JButton buttonDisconnectFromReader = new JButton("Disconnect");

	public ApplicationWindow(String title) {
		this.frame = new JFrame(title);

		frame.setSize(Constants.APPLICATION_WINDOW_SIZE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		buttonConnectToReader.setSize(new Dimension(75, 25));
		buttonConnectToReader.addActionListener(new ButtonConnectToReaderActionListener());

		buttonDisconnectFromReader.setSize(new Dimension(75, 25));
		buttonDisconnectFromReader.addActionListener(new ButtonDisconnectFromReaderActionListener());
		
		frame.add(buttonConnectToReader);
		frame.add(buttonDisconnectFromReader);

		frame.setLayout(new FlowLayout());
		frame.pack();
	}

	public JFrame getFrame() {
		return frame;
	}

	public JButton getButtonConnectToReader() {
		return buttonConnectToReader;
	}

	public JButton getButtonDisconnectFromReader() {
		return buttonDisconnectFromReader;
	}
}
