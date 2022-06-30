package com.snively.device.events;

import java.util.ArrayList;

import com.impinj.octane.ImpinjReader;
import com.impinj.octane.OctaneSdkException;
import com.mot.rfid.api3.GPO_PORT_STATE;
import com.mot.rfid.api3.InvalidUsageException;
import com.mot.rfid.api3.OperationFailureException;
import com.mot.rfid.api3.RFIDReader;
import com.snively.device.ReadTag;
import com.snively.device.ReaderDevice;
import com.snively.device.ReaderEvent;
import com.snively.device.ReaderTask;

public class TriggerGeneralOutputEvent implements ReaderEvent<Object> {

    /**
     * The electronic product code that triggers this event.
     */
    public static final String TRIGGER_TAG_EPC = "A0000123";

    /**
     * The device's general purpose output port that is triggered by this event.
     */
    public static final int TRIGGER_READER_GPO_PORT = 1;

    /**
     * The time that it takes for the general purpose output port port to reset.
     * Measured in five hundred millisecond intervals. Equivalent of five seconds.
     */
    public static final int READER_GPO_PORT_RESET_TIMER = 10;

    @Override
    public boolean execute(ReaderDevice device, ArrayList<ReadTag> tags, Object parent) {
        synchronized (parent) {
            if (parent instanceof RFIDReader) {

                final RFIDReader reader = (RFIDReader) parent;

                try {
                    reader.Config.GPO.setPortState(TRIGGER_READER_GPO_PORT, GPO_PORT_STATE.TRUE);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                device.schedule(new ReaderTask(READER_GPO_PORT_RESET_TIMER) {

                    @Override
                    public void execute() {
                        try {
                            reader.Config.GPO.setPortState(TRIGGER_READER_GPO_PORT, GPO_PORT_STATE.FALSE);
                        } catch (InvalidUsageException | OperationFailureException exception) {
                            exception.printStackTrace();
                        }
                    }

                    @Override
                    public void destruct() {

                    }
                });
            } else if (parent instanceof ImpinjReader) {

                final ImpinjReader reader = (ImpinjReader) parent;

                try {
                    reader.setGpo(TRIGGER_READER_GPO_PORT, true);
                } catch (OctaneSdkException exception) {
                    exception.printStackTrace();
                }

                device.schedule(new ReaderTask(READER_GPO_PORT_RESET_TIMER) {

                    @Override
                    public void execute() {
                        try {
                            reader.setGpo(TRIGGER_READER_GPO_PORT, false);
                        } catch (OctaneSdkException exception) {
                            exception.printStackTrace();
                        }
                    }

                    @Override
                    public void destruct() {

                    }
                });
            }
        }
        return true;
    }

    @Override
    public boolean predicate(ReaderDevice device, ArrayList<ReadTag> tags, Object parent) {
        return tags.stream().filter(it -> it.getCode() == TRIGGER_TAG_EPC) != null;
    }
}
