package com.snively.device;

public class Antenna {

	/**
	 * The transmission power for this antenna.
	 */
	private int power;

	public Antenna(int power) {
		this.power = power;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
}
