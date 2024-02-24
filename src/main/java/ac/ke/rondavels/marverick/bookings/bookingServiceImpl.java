package ac.ke.rondavels.marverick.bookings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class bookingServiceImpl  implements bookingServiceInter{


    bookingRepoInter bookingRepoInter;
    @Autowired
    public bookingServiceImpl(bookingRepoInter bookingRepoInter) {
        this.bookingRepoInter = bookingRepoInter;
    }
    @Override
    public booking findBookingById(Long id) {
        return bookingRepoInter.findBookingById(id);
    }

    public void saveBooking(booking booking) {
        bookingRepoInter.addBooking(booking);
    }
    @Override
    public List<booking> findAllBookings() {
        return bookingRepoInter.findAllBookings();
    }

    public void saveMessage(String email, String message) {
        bookingRepoInter.saveMessage(email, message);
    }



}
