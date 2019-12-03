package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository {
   private HashMap<Long,TimeEntry> timeEntryHashMap;
   private Long maxId = 0L;


    public InMemoryTimeEntryRepository() {
        this.timeEntryHashMap = new HashMap<>();
    }

    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry newTimeEntry = new TimeEntry(generateId(),timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(), timeEntry.getHours());
        timeEntryHashMap.put(newTimeEntry.getId(),newTimeEntry);
        return newTimeEntry;
    }

    public TimeEntry find(Long id) {
      return timeEntryHashMap.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryHashMap.values());
    }

    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry newTimeEntry = new TimeEntry(id,timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(), timeEntry.getHours());
        if(!timeEntryHashMap.containsKey(id)) return null;
        timeEntryHashMap.put(newTimeEntry.getId(),newTimeEntry);
        return newTimeEntry;
    }

    public void delete(Long id) {
        this.timeEntryHashMap.remove(id);
    }
    public Long generateId(){
        return ++maxId;
    }
}
