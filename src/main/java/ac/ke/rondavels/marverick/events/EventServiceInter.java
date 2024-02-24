package ac.ke.rondavels.marverick.events;

import java.util.List;

public interface EventServiceInter {
    public void addEvent(Event event);
    public void deleteEvent(Long id);
    public void updateEvent(Event event);
    public Event getEvent(Long id);
    public Event[] getAllEvents();

    public List<String> getAllImages();

    public void addParticipant(Long eventId, String attendee, String code, String PhoneNumber, String message_name);
}
