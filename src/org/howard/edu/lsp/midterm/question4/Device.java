package org.howard.edu.lsp.midterm.question4;

public abstract class Device {
    private String id;
    private String location;
    private long lastHeartbeatEpochSeconds;
    private boolean connected;

    // PROVIDED CONSTRUCTOR
    public Device(String id, String location) {
        if (id == null || id.isEmpty() || location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Invalid id or location");
        }
        this.id = id;
        this.location = location;
        this.lastHeartbeatEpochSeconds = 0;
        this.connected = false;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public long getLastHeartbeatEpochSeconds() {
        return lastHeartbeatEpochSeconds;
    }

    public boolean isConnected() {
        return connected;
    }

    protected void setConnected(boolean connected) {
        this.connected = connected;
    }

    public void heartbeat() {
        this.lastHeartbeatEpochSeconds = System.currentTimeMillis() / 1000;
    }

    public abstract String getStatus();
}

/*
 * References:
 * 
 * 1. Oracle Corporation. (2021). Java Platform, Standard Edition 11 API Specification.
 *    Retrieved from https://docs.oracle.com/en/java/javase/11/docs/api/
 * 
 * 2. Oracle Corporation. (2021). The Java Tutorials - Abstract Classes and Methods.
 *    Retrieved from https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html
 * 
 * 3. Bloch, J. (2018). Effective Java (3rd ed.). Addison-Wesley Professional.
 * 
 * 4. Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). Design Patterns: 
 *    Elements of Reusable Object-Oriented Software. Addison-Wesley.
 */
