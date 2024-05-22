package ac.ke.rondavels.marverick.bookings;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public class bookingRepoImpl implements bookingRepoInter{

    JdbcTemplate jdbcTemplate;

    public RowMapper<booking> bookingRowMapper;

    public bookingRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        setBookingMapper();
    }
    public void setBookingMapper() {
        bookingRowMapper = (resultSet, i) -> new booking(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getString("country"),
                resultSet.getDate("check_in_date").toLocalDate(),
                resultSet.getDate("check_out_date").toLocalDate(),
                resultSet.getString("adults"),
                resultSet.getString("children"),
                resultSet.getString("room_type"),
                resultSet.getString("meal_plan"),
                resultSet.getString("comments"),
                resultSet.getString("total_price"),
                resultSet.getString("status")

        );
    }

    /**
     * find all bookings from the database and throw an exception if there is 0 bookings
     */

    public List<booking> findAllBookings() {
        String sql = "SELECT * FROM bookings";
        try {
            return jdbcTemplate.query(sql, bookingRowMapper);
        } catch (EmptyResultDataAccessException ex) {
            throw new RuntimeException("No bookings found");
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error while fetching bookings", ex);
        }}


    public booking findBookingById(Long id) {
        String sql = "SELECT * FROM bookings WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, bookingRowMapper, id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RuntimeException("booking not found");
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error while fetching booking by ID", ex);
        }
    }

//    public void addBooking(booking booking) {
//        String sql = "INSERT INTO bookings(first_name, last_name, email, phone, country,  check_in_date, check_out_date, adults, children, room, meal_plan, message, total_price, status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//        jdbcTemplate.update
//                (sql, booking.getFirstName(), booking.getLastName(), booking.getEmail(), booking.getPhone(), booking.getCountry(), booking.getCheckin(), booking.getCheckout(), booking.getAdults(), booking.getChildren(), booking.getRoom(), booking.getMealPlan(), booking.getMessage(), booking.getPrice(), booking.getStatus());
//    }


    public void addBooking(booking booking) {
        String sql = "INSERT INTO bookings(first_name, last_name, email, phone, country,  check_in_date, check_out_date, adults, children, room_type, meal_plan, comments, total_price, status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                booking.getFirstName(),
                booking.getLastName(),
                booking.getEmail(),
                booking.getPhone(),
                booking.getCountry(),
                booking.getCheckin(),
                booking.getCheckout(),
                booking.getAdults(),
                booking.getChildren(),
                booking.getRoom(),
                booking.getMealPlan(),
                booking.getMessage(),
                booking.getPrice(),
                booking.getStatus());
    }

    @Override
    public void updateBooking(booking booking) {
        String sql = "UPDATE bookings SET first_name = ?, last_name = ?, email = ?, phone = ?, country = ?,  check_in_date = ?, check_out_date = ?, adults = ?, children = ?, room = ?, meal_plan = ?, message = ?, total_price = ?, status = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                booking.getFirstName(),
                booking.getLastName(),
                booking.getEmail(),
                booking.getPhone(),
                booking.getCountry(),
                booking.getCheckin(),
                booking.getCheckout(),
                booking.getAdults(),
                booking.getChildren(),
                booking.getRoom(),
                booking.getMealPlan(),
                booking.getMessage(),
                booking.getPrice(),
                booking.getStatus(),
                booking.getId());
    }

public void saveMessage(String email, String message) {
        String sql = "INSERT INTO enquiries(email, message) VALUES(?,?)";
        jdbcTemplate.update(sql, email, message);
    }

    public List<booking> findUpcomingBookings() {
        String sql = "SELECT * FROM bookings WHERE check_in_date >= CURRENT_DATE";
        return jdbcTemplate.query(sql, bookingRowMapper);
    }




}

