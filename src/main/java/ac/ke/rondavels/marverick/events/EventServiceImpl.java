package ac.ke.rondavels.marverick.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class EventServiceImpl implements EventServiceInter {
    @Autowired
    eventsRepoInter eventRepoInter;

    public void addEvent(Event event){
        eventRepoInter.addEvent(event);
    }

    public void deleteEvent(Long id){
        eventRepoInter.deleteEvent(id);
    }

    public void updateEvent(Event event){
        eventRepoInter.updateEvent(event);
    }

    public Event getEvent(Long id){
        return eventRepoInter.getEvent(id);
    }

    public Event[] getAllEvents(){
        return eventRepoInter.getAllEvents();
    }


    public List<String> getAllImages(){
        return eventRepoInter.getAllImages();
    }
    public void addParticipant(Long eventId, String attendee, String code, String PhoneNumber, String message_name){
        eventRepoInter.addParticipant(eventId, attendee, code, PhoneNumber, message_name);
    }



}
