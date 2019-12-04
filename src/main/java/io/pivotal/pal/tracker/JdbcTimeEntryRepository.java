package io.pivotal.pal.tracker;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class JdbcTimeEntryRepository  implements  TimeEntryRepository{
    private GeneratedKeyHolder generatedKeyHolder;
    private final RowMapper<TimeEntry> rowMapper = new RowMapper<>() {
        @Override
        public TimeEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new TimeEntry(
                    rs.getLong("id"),
                    rs.getLong("project_id"),
                    rs.getLong("user_id"),
                    rs.getDate("date").toLocalDate(),
                    rs.getInt("hours")
            );
        }
    };
    private JdbcTemplate jdbcTemplate;

    public JdbcTimeEntryRepository(DataSource dataSource) {
        this.generatedKeyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public java.sql.PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement statement = con.prepareStatement("INSERT INTO time_entries (project_id, user_id, date, hours) " +
                        "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

                statement.setLong(1, timeEntry.getProjectId());
                statement.setLong(2, timeEntry.getUserId());
                statement.setDate(3, Date.valueOf(timeEntry.getDate()));
                statement.setInt(4,timeEntry.getHours());

                return statement;
            }
        }, generatedKeyHolder);

        return find(generatedKeyHolder.getKey().longValue());

    }

    @Override
    public TimeEntry find(long timeEntryId) {
        List<TimeEntry> timeEntries = jdbcTemplate.query("SELECT * FROM time_entries where id = ?", rowMapper, timeEntryId);
        if (timeEntries.isEmpty()) {
            return null;
        }
        return timeEntries.get(0);
    }

    @Override
    public List<TimeEntry> list() {
       return jdbcTemplate.query("SELECT * FROM time_entries", rowMapper);
    }

    @Override
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {
        String updateQuery = "UPDATE time_entries set project_id =? , user_id =? , date=?, hours=? where id =?";
        jdbcTemplate.update(updateQuery,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours(),
                timeEntryId
        );
        return find(timeEntryId);
    }

    @Override
    public void delete(long timeEntryId) {
        String deleteSql = "DELETE from time_entries where id =?";
        jdbcTemplate.update(deleteSql, timeEntryId);
    }
}
