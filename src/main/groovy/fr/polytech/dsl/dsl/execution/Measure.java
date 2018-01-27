package fr.polytech.dsl.dsl.execution;

public class Measure {

    private long timestamp;
    private final Object value;
    private final String sensorName;
    private final String lotName;

    public Measure(long timestamp, Object value, String sensorName, String lotName) {
        this.timestamp = timestamp;
        this.value = value;
        this.sensorName = sensorName;
        this.lotName = lotName;
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

    public String getLotName() {
        return lotName;
    }
}
