package cinema;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CinemaTest {

    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2030, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test
    public void whenPlaceIsAlreadyPicked() {
        Account account = new AccountCinema();
        Account account2 = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket1 = cinema.buy(account, 1, 1, date);
        try {
            Ticket ticket2 = cinema.buy(account2, 1, 1, date);
            Assert.fail("Expected PlaceAlreadyPickedEx");
        } catch (Exception thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
    }

    @Test
    public void whenTimeHasPassed() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        try {
            date.set(1999, 10, 10, 23, 00);
            Ticket ticket = cinema.buy(account, 1, 1, date);
            Assert.fail("Expected TimeHasPassedEx");
        } catch (Exception thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
    }
}