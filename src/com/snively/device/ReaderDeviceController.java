package com.snively.device;

import java.util.ArrayList;

public class ReaderDeviceController {

    /**
     * The restrictive class instance.
     */
    private static final ReaderDeviceController singleton = new ReaderDeviceController();

    /**
     * Array implementation that houses registered devices. This includes both
     * active and inactive devices.
     */
    private final ArrayList<ReaderDevice> devices = new ArrayList<ReaderDevice>();

    /**
     * Registers a new device. This does not start the device.
     * 
     * @param device The device to register.
     */
    public void register(ReaderDevice device) {
        device.configure();
        devices.add(device);
    }

    /**
     * Removes an existing device. This does not start the device.
     * 
     * @param device The device to remove.
     */
    public void deregister(ReaderDevice device) {
        device.terminate();
        devices.remove(device);
    }

    /**
     * Starts the inventory operation for this device.
     * 
     * @param device The device to start operations for.
     */
    public void start(ReaderDevice device) {
        device.start();
    }

    /**
     * Stops the inventory operation for this device.
     * 
     * @param device The device to stop operations for.
     */
    public void stop(ReaderDevice device) {
        device.stop();
    }

    public static ReaderDeviceController getSingleton() {
        return singleton;
    }

    public ArrayList<ReaderDevice> getDevices() {
        return devices;
    }
}
