package com.snively.device.events;

import java.util.ArrayList;

import com.impinj.octane.ImpinjReader;
import com.impinj.octane.OctaneSdkException;
import com.mot.rfid.api3.GPO_PORT_STATE;
import com.mot.rfid.api3.RFIDReader;
import com.snively.device.ReadTag;
import com.snively.device.ReaderDevice;
import com.snively.device.ReaderEvent;

public class TriggerGeneralOutputEvent implements ReaderEvent<Object> {

	public static final String TRIGGER_TAG_EPC = "A0000123";

	@Override
	public boolean execute(ReaderDevice device, ArrayList<ReadTag> tags, Object parent) {
		if (tags.stream().filter(it -> it.getCode() == TRIGGER_TAG_EPC) != null) {

			if (parent instanceof RFIDReader) {

				final RFIDReader reader = (RFIDReader) parent;

				try {
					reader.Config.GPO.setPortState(1, GPO_PORT_STATE.TRUE);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			} else if (parent instanceof ImpinjReader) {

				final ImpinjReader reader = (ImpinjReader) parent;

				try {
					reader.setGpo(1, true);
				} catch (OctaneSdkException exception) {
					exception.printStackTrace();
				}
			}
			return true;
		}
		return false;
	}
}
