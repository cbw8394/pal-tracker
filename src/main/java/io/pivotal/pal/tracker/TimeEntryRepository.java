package io.pivotal.pal.tracker;

import java.util.List;

public class TimeEntryRepository {

InMemoryTimeEntryRepository inMemoryTimeEntryRepository = new InMemoryTimeEntryRepository();
    public TimeEntry create(TimeEntry timeEntry) {
       return inMemoryTimeEntryRepository.create(new TimeEntry(inMemoryTimeEntryRepository.generateId() ,timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours()));

    }

    public TimeEntry find(long timeEntryId) {
        return inMemoryTimeEntryRepository.find(timeEntryId);

    }

    public List<TimeEntry> list() {
        return inMemoryTimeEntryRepository.list();
    }

    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {

        return inMemoryTimeEntryRepository.update(timeEntryId,timeEntry);
    }

    public void delete(long timeEntryId) {
        inMemoryTimeEntryRepository.delete(timeEntryId);
    }
}
