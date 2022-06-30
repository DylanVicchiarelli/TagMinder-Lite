package com.snively.device;

import java.util.ArrayList;

public interface ReaderEvent<T> {

    /**
     * Executes this event when the predicate condition is met.
     * 
     * @param device The device that has registered this event.
     * @param tags   The tags that were read during the inventory operation.
     * @param parent An instance of the software development kit's hardware control
     *               class or another parental control parameter for this event.
     * @return Denotes the success or failure of this event's execution.
     */
    boolean execute(ReaderDevice device, ArrayList<ReadTag> tags, T parent);

    /**
     * Establishes a predicate condition that must be met to trigger this event.
     * 
     * @param device The device that has registered this event.
     * @param tags   The tags that were read during the inventory operation. Used to
     *               assert this condition.
     * @param parent An instance of the software development kit's hardware control
     *               class or another parental control parameter for this event.
     * @return Denotes if the condition was met.
     */
    boolean predicate(ReaderDevice device, ArrayList<ReadTag> tags, T parent);
}