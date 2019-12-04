package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements  TimeEntryRepository{
   private HashMap<Long,TimeEntry> timeEntryHashMap;
   private Long maxId = 0L;


    public InMemoryTimeEntryRepository() {
        this.timeEntryHashMap = new HashMap<>();
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry newTimeEntry = new TimeEntry(generateId(),timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(), timeEntry.getHours());
        timeEntryHashMap.put(newTimeEntry.getId(),newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(long id) {
      return timeEntryHashMap.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryHashMap.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry newTimeEntry = new TimeEntry(id,timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(), timeEntry.getHours());
        if(!timeEntryHashMap.containsKey(id)) return null;
        timeEntryHashMap.put(newTimeEntry.getId(),newTimeEntry);
        return newTimeEntry;
    }
    @Override
    public void delete(long id) {
        this.timeEntryHashMap.remove(id);
    }
    public Long generateId(){
        return ++maxId;
    }
}
