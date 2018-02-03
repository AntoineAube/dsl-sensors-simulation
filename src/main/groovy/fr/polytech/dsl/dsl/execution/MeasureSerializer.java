package fr.polytech.dsl.dsl.execution;

public class MeasureSerializer {

    private static final String RETENTION_POLICY = "retention_policy";
    private static final String VALUE_FIELD = "value";

    private boolean initialized;
    /*private final Configuration databaseConfiguration;

    public MeasureSerializer(Configuration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;

        initialized = false;
    }

    public void saveMeasures(List<Measure> measures) {
        if (!initialized) {
            initialize();
        }

        BatchPoints batch = BatchPoints.database(databaseConfiguration.getDatabaseName())
                .tag("async", "true")
                // .retentionPolicy(RETENTION_POLICY)
                .consistency(InfluxDB.ConsistencyLevel.ALL)
                .build();

        for (Measure measure : measures) {
            batch.point(intoPoint(measure));
        }

        InfluxDB connection = InfluxDBFactory.connect(databaseConfiguration.getDatabaseLocation());
        connection.write(batch);

        connection.close();
    }

    public void saveMeasure(Measure measure) {
        if (!initialized) {
            initialize();
        }

        InfluxDB connection = InfluxDBFactory.connect(databaseConfiguration.getDatabaseLocation());
        connection.setDatabase(databaseConfiguration.getDatabaseName());

        connection.write(intoPoint(measure));

        connection.close();
    }

    private static Point intoPoint(Measure measure) {
        Point.Builder builder = Point.measurement(measure.getSensorName())
                .time(measure.getTimestamp(), TimeUnit.MILLISECONDS);

        Object value = measure.getValue();

        if (value instanceof String) {
            builder.addField(VALUE_FIELD, (String) value);
        } else if (value instanceof Integer) {
            builder.addField(VALUE_FIELD, (Integer) value);
        } else if (value instanceof Boolean) {
            builder.addField(VALUE_FIELD, (Boolean) value);
        }

        return builder.build();
    }

    private void initialize() {
        InfluxDB connection = InfluxDBFactory.connect(databaseConfiguration.getDatabaseLocation());

        if (!connection.databaseExists(databaseConfiguration.getDatabaseName())) {
            connection.createDatabase(databaseConfiguration.getDatabaseName());
            // connection.createRetentionPolicy(RETENTION_POLICY, databaseConfiguration.getDatabaseName(), "30d", "30m", 2, true);
            connection.enableBatch(2000, 100, TimeUnit.MILLISECONDS);
        }

        initialized = true;

        connection.close();
    }*/
}
