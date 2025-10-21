package org.howard.edu.lsp.midterm.question4;

public class Thermostat extends Device implements Networked {
    private double temperatureC;

    public Thermostat(String id, String location, double initialTempC) {
        super(id, location);
        this.temperatureC = initialTempC;
    }

    public double getTemperatureC() {
        return temperatureC;
    }

    public void setTemperatureC(double temperatureC) {
        this.temperatureC = temperatureC;
    }

    // Networked
    @Override public void connect()    { setConnected(true);  }
    @Override public void disconnect() { setConnected(false); }
    @Override public boolean isConnected() { return super.isConnected(); }

    // Status
    @Override public String getStatus() {
        String connStatus = isConnected() ? "up" : "down";
        return "Thermostat[id=" + getId() + ", loc=" + getLocation() +
                       ", conn=" + connStatus + ", tempC=" + temperatureC + "]";
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
