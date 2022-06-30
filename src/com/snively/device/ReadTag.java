package com.snively.device;

public class ReadTag {

    /**
     * The tag or electronic product code.
     */
    private String code;

    /**
     * The received signal strength indicator.
     */
    private double rssi;

    public ReadTag(String code, double rssi) {
        this.code = code;
        this.rssi = rssi;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getRssi() {
        return rssi;
    }

    public void setRssi(double rssi) {
        this.rssi = rssi;
    }
}
