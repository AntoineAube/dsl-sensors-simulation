package fr.polytech.dsl.dsl.model.structures;

import fr.polytech.dsl.dsl.model.VisitableModel;
import fr.polytech.dsl.dsl.model.ModelVisitor;

import java.io.File;

public class Replay<L> implements VisitableModel {

    private final String sourceType;
    private final File sourceLocation;
    private LocationsSet<L> locations;
    private long timestampOffset;

    public Replay(String sourceType, String sourceLocation) {
        this.sourceType = sourceType;
        this.sourceLocation = new File(sourceLocation);
        locations = new LocationsSet<>();
    }

    public String getSourceType() {
        return sourceType;
    }

    public File getSourceLocation() {
        return sourceLocation;
    }

    public LocationsSet<L> getLocations() {
        return locations;
    }

    public void setLocations(LocationsSet<L> locations) {
        this.locations = locations;
    }

    public long getTimestampOffset() {
        return timestampOffset;
    }

    public void setTimestampOffset(long timestampOffset) {
        this.timestampOffset = timestampOffset;
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
    }

    public static class LocationsSet<L> {

        private L timesLocation;
        private L valuesLocation;
        private L sensorsLocation;
        private L lotsLocation;

        public L getTimesLocation() {
            return timesLocation;
        }

        public void setTimesLocation(L timesLocation) {
            this.timesLocation = timesLocation;
        }

        public L getValuesLocation() {
            return valuesLocation;
        }

        public void setValuesLocation(L valuesLocation) {
            this.valuesLocation = valuesLocation;
        }

        public L getSensorsLocation() {
            return sensorsLocation;
        }

        public void setSensorsLocation(L sensorsLocation) {
            this.sensorsLocation = sensorsLocation;
        }

        public L getLotsLocation() {
            return lotsLocation;
        }

        public void setLotsLocation(L lotsLocation) {
            this.lotsLocation = lotsLocation;
        }
    }
}
