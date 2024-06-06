package ac.ke.rondavels.marverick.events;

import java.util.List;
import java.util.Map;

public interface EventServiceInter {
    public void addEvent(Event event);
    public void deleteEvent(Long id);
    public void updateEvent(Event event);
    public Event getEvent(Long id);
    public Event[] getAllEvents();

    public List<String> getAllImages();

    public void addParticipant(Long eventId, String attendee, String code, String PhoneNumber, String message_name, String email);

    public List<Map<String, Object>> getParticipantsForEvent(int eventId);
    public void confirmParticipant(Long id);
}
