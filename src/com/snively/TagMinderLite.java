package com.snively;

import java.net.InetSocketAddress;

import com.snively.device.ReaderDevice;
import com.snively.device.ReaderDeviceController;
import com.snively.device.devices.ImpinjReaderDevice;
import com.snively.device.devices.ZebraReaderDevice;
import com.snively.device.events.TriggerGeneralOutputEvent;
import com.snively.window.ApplicationWindow;

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

		final ReaderDevice zebra_device = new ZebraReaderDevice(new InetSocketAddress("0.0.0.0", 43594));
		zebra_device.setEvent(new TriggerGeneralOutputEvent());

		final ReaderDevice impinj_device = new ImpinjReaderDevice(new InetSocketAddress("0.0.0.0", 43594));
		impinj_device.setEvent(new TriggerGeneralOutputEvent());

		controller.register(zebra_device);
		controller.register(impinj_device);

		controller.start(zebra_device);
		controller.start(impinj_device);
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