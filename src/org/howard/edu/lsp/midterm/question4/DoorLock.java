package org.howard.edu.lsp.midterm.question4;

public class DoorLock extends Device implements Networked, BatteryPowered {
    private int batteryPercent;

    public DoorLock(String id, String location, int initialBattery) {
        super(id, location);
        setBatteryPercent(initialBattery);
    }

    // Networked
    @Override public void connect()    { setConnected(true);  }
    @Override public void disconnect() { setConnected(false); }
    @Override public boolean isConnected() { return super.isConnected(); }

    // BatteryPowered
    @Override public int getBatteryPercent() { return batteryPercent; }
    @Override public void setBatteryPercent(int percent) {
        if (percent < 0 || percent > 100) throw new IllegalArgumentException("battery 0..100");
        this.batteryPercent = percent;
    }

    // Status
    @Override public String getStatus() {
        String connStatus = isConnected() ? "up" : "down";
        return "DoorLock[id=" + getId() + ", loc=" + getLocation() +
                       ", conn=" + connStatus + ", batt=" + batteryPercent + "%]";
    }
}

/*
 * References:
 * 
 * 1. Oracle Corporation. (2021). Java Platform, Standard Edition 11 API Specification.
 *    Retrieved from https://docs.oracle.com/en/java/javase/11/docs/api/
 * 
 * 2. Oracle Corporation. (2021). The Java Tutorials - Interfaces.
 *    Retrieved from https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html
 * 
 * 3. Bloch, J. (2018). Effective Java (3rd ed.). Addison-Wesley Professional.
 * 
 * 4. Oracle Corporation. (2021). The Java Tutorials - Exceptions.
 *    Retrieved from https://docs.oracle.com/javase/tutorial/essential/exceptions/
 */
