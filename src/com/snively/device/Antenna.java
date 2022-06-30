package com.snively.device;

public class Antenna {

	/**
	 * The transmission power for this antenna.
	 */
	private int power;

	/**
	 * The port number on the device that this antenna is connected to.
	 */
	private int port;

	public Antenna(int power, int port) {
		this.power = power;
		this.port = port;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
