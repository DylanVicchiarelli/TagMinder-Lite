package com.snively.device;

import java.net.InetSocketAddress;
import java.util.ArrayList;

public abstract class ReaderDevice {

	/**
	 * The remote address for this device.
	 */
	protected final InetSocketAddress endpoint;

	/**
	 * The current event to execute on inventory reporting.
	 */
	protected ReaderEvent<Object> event;

	/**
	 * The antennas that are connected to this device.
	 */
	protected final ArrayList<Antenna> antennas = new ArrayList<Antenna>();

	/**
	 * Denotes if this device is currently performing inventory operations.
	 */
	private boolean performingInventory;

	/**
	 * Configures the settings for this device.
	 * @return Denotes the success or failure of the operation.
	 */
	public abstract boolean configure();
	
	/**
	 * Terminates this device.
	 * @return Denotes the success or failure of the operation.
	 */
	public abstract boolean terminate();

	/**
	 * Starts the inventory operations for this device.
	 */
	public abstract void start();

	/**
	 * Stops the inventory operations for this device.
	 */
	public abstract void stop();

	public ReaderDevice(InetSocketAddress endpoint) {
		this.endpoint = endpoint;
	}

	public InetSocketAddress getEndpoint() {
		return endpoint;
	}

	public ArrayList<Antenna> getAntennas() {
		return antennas;
	}

	public boolean isPerformingInventory() {
		return performingInventory;
	}

	public void setPerformingInventory(boolean performingInventory) {
		this.performingInventory = performingInventory;
	}

	public ReaderEvent<Object> getEvent() {
		return event;
	}

	public void setEvent(ReaderEvent<Object> event) {
		this.event = event;
	}
}