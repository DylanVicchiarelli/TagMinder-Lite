package com.snively.device.devices;

import java.net.InetSocketAddress;
import java.util.ArrayList;

import com.mot.rfid.api3.InvalidUsageException;
import com.mot.rfid.api3.OperationFailureException;
import com.mot.rfid.api3.RFIDReader;
import com.mot.rfid.api3.RfidEventsListener;
import com.mot.rfid.api3.RfidReadEvents;
import com.mot.rfid.api3.RfidStatusEvents;
import com.mot.rfid.api3.STATUS_EVENT_TYPE;
import com.mot.rfid.api3.TagData;
import com.snively.device.ReadTag;
import com.snively.device.ReaderDevice;

public class ZebraReaderDevice extends ReaderDevice implements RfidEventsListener {

    /**
     * Provides access to the device's hardware configurations and network.
     */
    private final RFIDReader reader = new RFIDReader();

    public ZebraReaderDevice(InetSocketAddress endpoint) {
        super(endpoint);
    }

    @Override
    public boolean configure() {
        reader.setHostName(endpoint.getHostName());
        reader.setPort(endpoint.getPort());
        try {
            reader.connect();

            reader.Events.setInventoryStartEvent(true);
            reader.Events.setInventoryStopEvent(true);
            reader.Events.setTagReadEvent(true);
            reader.Events.addEventsListener(this);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return reader.isConnected();
    }

    @Override
    public void eventReadNotify(RfidReadEvents events) {
        if (reader == null)
            return;

        final TagData[] tags = reader.Actions.getReadTags(100);
        if (tags == null)
            return;

        final ArrayList<ReadTag> results = new ArrayList<ReadTag>();

        for (int it = 0; it < tags.length; it++) {
            final TagData tag = tags[it];

            if (tag == null)
                continue;

            results.add(new ReadTag(tag.getTagID(), tag.getPeakRSSI()));
        }

        if (event.predicate(this, results, reader))
            event.execute(this, results, reader);
    }

    @Override
    public void eventStatusNotify(RfidStatusEvents events) {
        final STATUS_EVENT_TYPE status = events.StatusEventData.getStatusEventType();

        if (status == STATUS_EVENT_TYPE.INVENTORY_START_EVENT) {

        } else if (status == STATUS_EVENT_TYPE.INVENTORY_STOP_EVENT) {

        }
    }

    @Override
    public void start() {
        try {
            reader.Actions.Inventory.perform();
            setPerformingInventory(true);
        } catch (InvalidUsageException | OperationFailureException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            reader.Actions.Inventory.stop();
            setPerformingInventory(false);
        } catch (InvalidUsageException | OperationFailureException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public boolean terminate() {
        try {
            reader.disconnect();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return !reader.isConnected();
    }
}