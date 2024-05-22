package ac.ke.rondavels.marverick.bookings;

import java.util.List;

public interface bookingServiceInter {
    public booking findBookingById(Long id);
    public void  saveBooking(booking booking);
    public List<booking> findAllBookings();

    public void saveMessage(String email, String message);

    public List<booking> findUpcomingBookings();


}
