package com.snively.device;

public abstract class ReaderTask {

    /**
     * The amount of ticks between executions. Measured in five hundred millisecond
     * intervals.
     */
    private final int ticks;
    
    /**
     * The current iteration count.
     */
    private int iterations;
    
    /**
     * Denotes if this task is currently active.
     */
    private boolean active;
    
    /**
     * Executes this task.
     */
    public abstract void execute();
    
    /**
     * Destroys this task once the activity status is flagged <code>false</code>.
     */
    public abstract void destruct();
    
    public ReaderTask(int ticks) {
        this.ticks = ticks;
    }

    public int getTicks() {
        return ticks;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
