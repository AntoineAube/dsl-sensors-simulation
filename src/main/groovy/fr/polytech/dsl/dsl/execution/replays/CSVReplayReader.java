package fr.polytech.dsl.dsl.execution.replays;

public class CSVReplayReader extends ReplayReader<Integer> {

    public CSVReplayReader() {
        super();
    }

    /*@Override
    List<Measure> readMeasures() throws IOException {
        Reader in = new FileReader(getReplay().getSourceLocation());

        List<Measure> measures = new ArrayList<>();
        for (CSVRecord record : CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in)) {
            long time = Long.parseLong(record.get(getReplay().getLocations().getTimesLocation() - 1));
            Object value = parseValue(record.get(getReplay().getLocations().getValuesLocation() - 1));
            String sensor = record.get(getReplay().getLocations().getSensorsLocation() - 1);

            measures.add(new Measure(time, value, sensor));
        }

        return measures;
    }*/
}
