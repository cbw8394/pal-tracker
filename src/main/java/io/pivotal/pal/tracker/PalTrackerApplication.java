package io.pivotal.pal.tracker;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class PalTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class,args);
    }

    @Bean
    public JdbcTimeEntryRepository getTimeEntryRepository(DataSource dataSource){

        return new JdbcTimeEntryRepository(dataSource);
    }
}
