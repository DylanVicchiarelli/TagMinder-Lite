package com.snively;

import com.snively.device.ReaderDeviceController;
import com.snively.window.ApplicationWindow;

/**
 * Starts the application and maintains mutable global variables.
 * @author Dylan Vicchiarelli
 */
public class TagMinderLite {

	/**
	 * Renders the application's user interface.
	 */
	private static ApplicationWindow window = new ApplicationWindow("TagMinder Lite");

	/**
	 * Controls the devices that have been connected to this application.
	 */
	private static ReaderDeviceController controller = new ReaderDeviceController();

	/**
	 * The entry point for the application.
	 * @param commands The command line arguments.
	 */
	public static void main(String[] commands) {

	}

	public static ApplicationWindow getWindow() {
		return window;
	}

	public static void setWindow(ApplicationWindow window) {
		TagMinderLite.window = window;
	}

	public static ReaderDeviceController getDeviceController() {
		return controller;
	}

	public static void setDeviceController(ReaderDeviceController deviceController) {
		TagMinderLite.controller = deviceController;
	}
}