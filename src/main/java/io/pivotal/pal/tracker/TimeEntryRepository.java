package io.pivotal.pal.tracker;

public class TimeEntryRepository {
    private TimeEntry timeEntry;
    private long timeEntryId;
    private long eq;

    public void create(TimeEntry timeEntry) {

        this.timeEntry = timeEntry;
    }

    public void find(long timeEntryId) {

        this.timeEntryId = timeEntryId;
    }

    public void list() {

    }

    public void update(long timeEntryId, TimeEntry timeEntry) {

        this.timeEntryId = timeEntryId;
        this.timeEntry = timeEntry;
    }

    public void delete(long timeEntryId) {
    }
}
