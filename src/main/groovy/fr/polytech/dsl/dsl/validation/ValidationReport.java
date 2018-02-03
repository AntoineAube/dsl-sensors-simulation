package fr.polytech.dsl.dsl.validation;

import fr.polytech.dsl.dsl.model.VisitableModel;

import java.util.ArrayList;
import java.util.List;

public class ValidationReport {

    private final List<Entry> reportedEntries;

    public ValidationReport() {
        reportedEntries = new ArrayList<>();
    }

    public List<Entry> getReportedEntries() {
        return reportedEntries;
    }

    public static Entry.Builder entry() {
        return Entry.builder();
    }

    public static Entry.Builder error(VisitableModel involvedModel) {
        return Entry.builder()
                .status(ValidationStatus.ERROR)
                .model(involvedModel);
    }

    public static Entry.Builder warning(VisitableModel involvedModel) {
        return Entry.builder()
                .status(ValidationStatus.WARNING)
                .model(involvedModel);
    }

    private static class Entry {

        private final ValidationStatus status;
        private final VisitableModel involvedModel;
        private final String message;

        private Entry(ValidationStatus status, VisitableModel involvedModel, String message) {
            this.status = status;
            this.involvedModel = involvedModel;
            this.message = message;
        }

        public ValidationStatus getStatus() {
            return status;
        }

        public VisitableModel getInvolvedModel() {
            return involvedModel;
        }

        public String getMessage() {
            return message;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {
            private ValidationStatus status;
            private VisitableModel involvedModel;
            private String message;

            public Builder status(ValidationStatus status) {
                this.status = status;

                return this;
            }

            public Builder model(VisitableModel involvedModel) {
                this.involvedModel = involvedModel;

                return this;
            }

            public Builder message(String message) {
                this.message = message;

                return this;
            }

            Entry build() {
                return new Entry(status, involvedModel, message);
            }

            public void save(ValidationReport report) {
                report.reportedEntries.add(build());
            }
        }
    }
}
