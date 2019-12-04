package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class TimeEntryHealthIndicator implements HealthIndicator {
    private TimeEntryRepository timeEntryRepository;
    private static final int MAX_TIME_ENTRIES = 5;
    public TimeEntryHealthIndicator(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @Override
    public Health health() {
        Health.Builder builder = new Health.Builder();
        int size = timeEntryRepository.list().size();
        builder.withDetail("numEntries",size).withDetail("maxAllowed",MAX_TIME_ENTRIES);
        if(size < MAX_TIME_ENTRIES){
            builder.up();
        }
        else{
            builder.down();
        }
        return builder.build();
    }
}
