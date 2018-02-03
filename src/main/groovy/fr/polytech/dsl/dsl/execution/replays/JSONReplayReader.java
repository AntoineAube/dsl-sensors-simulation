package fr.polytech.dsl.dsl.execution.replays;

public class JSONReplayReader extends ReplayReader<String> {

    public JSONReplayReader() {
        //super(replay);
    }

   /* @Override
    List<Measure> readMeasures() throws IOException {
        JSONParser parser = new JSONParser();

        JSONArray fullDocument;
        try {
            fullDocument = (JSONArray) parser.parse(new FileReader(getReplay().getSourceLocation()));
        } catch (ParseException e) {
            throw new IOException(e);
        }

        List<Measure> measures = new ArrayList<>();

        for (Object documentItem : fullDocument) {
            JSONObject measureDocument = (JSONObject) documentItem;

            Replay.LocationsSet<String> locations = getReplay().getLocations();

            measures.add(new Measure(
                    (Long) measureDocument.get(locations.getTimesLocation()),
                    measureDocument.get(locations.getValuesLocation()),
                    (String) measureDocument.get(locations.getSensorsLocation())));
        }

        return measures;
    }*/
}
