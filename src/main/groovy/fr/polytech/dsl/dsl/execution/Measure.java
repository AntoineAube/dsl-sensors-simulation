package fr.polytech.dsl.dsl.execution;

public class Measure {

    private long timestamp;
    private Object value;
    private String sensorName;

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

    public void setValue(Object value) {
        this.value = value;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
}
