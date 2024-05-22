package ac.ke.rondavels.marverick.events;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository

public class EventRepoImol  implements eventsRepoInter{

    JdbcTemplate jdbcTemplate;
    private RowMapper<Event> rowMapper = (resultSet, i) -> {
        Event event = new Event();
        event.setId(resultSet.getLong("id"));
        event.setTitle(resultSet.getString("title"));
        event.setDescription(resultSet.getString("description"));
        event.setLocation(resultSet.getString("location"));
        event.setItinerary(resultSet.getString("itinerary"));
        event.setCost(resultSet.getLong("cost"));
        event.setStartDateTime(resultSet.getTimestamp("start_date_time").toLocalDateTime());
        event.setEndDateTime(resultSet.getTimestamp("end_date_time").toLocalDateTime());
        event.setImage(resultSet.getString("image"));
        event.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());

        return event;
    };

    public EventRepoImol(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void addEvent(Event event) {
        String sql = "INSERT INTO events(title, description, location, itinerary, cost, start_date_time, end_date_time, image) VALUES(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, event.getTitle(), event.getDescription(), event.getLocation(), event.getItinerary(), event.getCost(), event.getStartDateTime(), event.getEndDateTime(), event.getImage());
    }


    @Override

    public void deleteEvent(Long id) {
        String sql = "uPDATE EVENTS SET is_showing = false WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override

    public void updateEvent(Event event) {
        String sql = "UPDATE events SET title = ?, description = ?, location = ?, itinerary = ?, cost = ?, start_date_time = ?, end_date_time = ?, image = ? WHERE id = ?";
        jdbcTemplate.update(sql, event.getTitle(), event.getDescription(), event.getLocation(), event.getItinerary(), event.getCost(), event.getStartDateTime(), event.getEndDateTime(), event.getImage(), event.getId());
    }

    @Override

    public Event getEvent(Long id) {
        String sql = "SELECT * FROM events WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override

    public Event[] getAllEvents() {
        String sql = "SELECT * FROM events WHERE is_showing = true";
        return jdbcTemplate.query(sql, rowMapper).toArray(new Event[0]);
    }


    /**
     * gett all the image lnks
     */


    public List<String> getAllImages() {
        try {
            String sql = "SELECT image FROM gallery";
            return jdbcTemplate.queryForList(sql, String.class);

        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
    }


    public void addParticipant(Long eventId, String attendee, String code, String PhoneNumber, String message_name){
        String sql = "INSERT INTO Event_Participants(event_id, attendee, mpesa_code, phone_number, message_name) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, eventId, attendee, code, PhoneNumber, message_name);
    }

}
