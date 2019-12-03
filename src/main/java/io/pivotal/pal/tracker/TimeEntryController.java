package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController()
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {

        return new ResponseEntity( this.timeEntryRepository.create(timeEntryToCreate),HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        TimeEntry timeEntry = this.timeEntryRepository.find(id);
        if( timeEntry== null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(timeEntry,HttpStatus.OK);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {

        return new ResponseEntity(this.timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updateTimeEntry = this.timeEntryRepository.update(id,timeEntry);

        if(updateTimeEntry == null)  return new ResponseEntity(updateTimeEntry,HttpStatus.NOT_FOUND);
        return new ResponseEntity(updateTimeEntry,HttpStatus.OK);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
         this.timeEntryRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
