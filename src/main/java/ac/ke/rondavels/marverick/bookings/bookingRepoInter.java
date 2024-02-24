package ac.ke.rondavels.marverick.bookings;


import java.util.List;

public interface bookingRepoInter {
    public booking findBookingById(Long id);

    public List<booking> findAllBookings();

    public void addBooking(booking booking);

    public void updateBooking(booking booking);

    public void saveMessage(String email, String message);



}
