package com.snively.device;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class ReaderDevice implements Runnable {

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
     * Schedules commands to run after a given delay. Processes active tasks for this device.
     */
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    /**
     * An unbound thread safe queue that stores active tasks in the system.
     * Completed tasks are removed on the next iteration.
     */
    private final ConcurrentLinkedQueue<ReaderTask> tasks = new ConcurrentLinkedQueue<ReaderTask>();

    /**
     * The delayed result bearing action.
     */
    private final ScheduledFuture<?> future;

    /**
     * Configures the settings for this device.
     * 
     * @return Denotes the success or failure of the operation.
     */
    public abstract boolean configure();

    /**
     * Terminates this device and performs cleanup operations.
     * 
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

    /**
     * Creates a new instance of this class.
     * 
     * @param endpoint The remote address for this device.
     */
    public ReaderDevice(InetSocketAddress endpoint) {
        this.endpoint = endpoint;
        this.future = executor.scheduleAtFixedRate(this, 0, 500, TimeUnit.MILLISECONDS);
    }

    public void schedule(ReaderTask task) {
        tasks.add(task);
        task.setActive(true);
    }

    @Override
    public void run() {
        for (ReaderTask task : tasks) {
            if (task == null)
                continue;

            if (task.isActive()) {
                if (task.getIterations() == task.getTicks()) {
                    task.execute();

                    task.setIterations(0);
                } else {
                    task.setIterations(task.getIterations() + 1);
                }
            } else {
                task.destruct();

                tasks.remove(task);
            }
        }
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

    public ScheduledFuture<?> getFuture() {
        return future;
    }
}