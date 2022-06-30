package com.snively.device.devices;

import java.net.InetSocketAddress;
import java.util.ArrayList;

import com.impinj.octane.ImpinjReader;
import com.impinj.octane.OctaneSdkException;
import com.impinj.octane.ReportConfig;
import com.impinj.octane.ReportMode;
import com.impinj.octane.Settings;
import com.impinj.octane.Tag;
import com.impinj.octane.TagReport;
import com.impinj.octane.TagReportListener;
import com.snively.device.ReadTag;
import com.snively.device.ReaderDevice;

public class ImpinjReaderDevice extends ReaderDevice implements TagReportListener {

    private final ImpinjReader reader = new ImpinjReader();

    public ImpinjReaderDevice(InetSocketAddress endpoint) {
        super(endpoint);
    }

    @Override
    public boolean configure() {
        final Settings settings = reader.queryDefaultSettings();
        final ReportConfig report = settings.getReport();
        try {
            report.setIncludeAntennaPortNumber(true);
            report.setIncludePeakRssi(true);
            report.setMode(ReportMode.Individual);

            reader.connect(endpoint.getHostName(), endpoint.getPort());
            reader.setTagReportListener(this);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return reader.isConnected();
    }

    public ImpinjReader getReader() {
        return reader;
    }

    @Override
    public void onTagReported(ImpinjReader reader, TagReport report) {

        final ArrayList<ReadTag> results = new ArrayList<ReadTag>();

        for (Tag tag : report.getTags()) {
            if (tag == null)
                continue;
            results.add(new ReadTag(tag.getEpc().toString(), tag.getPeakRssiInDbm()));
        }

        if (event == null)
            return;

        if (event.predicate(this, results, reader))
            event.execute(this, results, reader);
    }

    @Override
    public void start() {
        try {
            reader.start();
            setPerformingInventory(true);
        } catch (OctaneSdkException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            reader.stop();
            setPerformingInventory(false);
        } catch (OctaneSdkException exception) {
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