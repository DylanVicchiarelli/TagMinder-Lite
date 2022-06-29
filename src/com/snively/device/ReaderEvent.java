package com.snively.device;

import java.util.ArrayList;

public interface ReaderEvent<T> {

	boolean execute(ReaderDevice device, ArrayList<ReadTag> tags, T parent);
}