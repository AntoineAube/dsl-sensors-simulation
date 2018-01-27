package fr.polytech.dsl.dsl.execution.replays;

import fr.polytech.dsl.dsl.model.structures.Replay;

public class ReplayReaderFactory {

    public ReplayReader createReplayReader(Replay replay) {
        if ("csv".equals(replay.getSourceType())) {
            return new CSVReplayReader(replay);
        } else if ("json".equals(replay.getSourceType())) {
            return new JSONReplayReader(replay);
        }

        throw new RuntimeException("Unknown source type: " + replay.getSourceType());
    }
}
