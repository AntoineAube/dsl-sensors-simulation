package fr.polytech.dsl.dsl.execution;

public class Measure {

    private long timestamp;
    private final Object value;
    private final String sensorName;

    public Measure(long timestamp, Object value, String sensorName) {
        this.timestamp = timestamp;
        this.value = value;
        this.sensorName = sensorName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Object getValue() {
        return value;
    }

    public String getSensorName() {
        return sensorName;
    }
}
