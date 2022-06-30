package com.snively.window.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;

import com.snively.TagMinderLite;
import com.snively.device.ReaderDevice;
import com.snively.device.devices.ZebraReaderDevice;
import com.snively.device.events.TriggerGeneralOutputEvent;

public class ButtonConnectToReaderActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) {
		final ReaderDevice device = new ZebraReaderDevice(new InetSocketAddress("0.0.0.0", 43594));
		device.setEvent(new TriggerGeneralOutputEvent());

		/*
		 * Registers this device and configures it's attributes prior to operations.
		 */
		TagMinderLite.getDeviceController().register(device);

		/*
		 * Starts the inventory operations for this device.
		 */
		TagMinderLite.getDeviceController().start(device);
	}
}