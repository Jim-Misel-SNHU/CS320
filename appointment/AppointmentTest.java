package appointment;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

    private Date futureDate() {
        return new Date(System.currentTimeMillis() + 60_000);
    }

    @Test
    public void testCreateAppointment_ValidInputs_Succeeds() {
        Appointment appt = new Appointment("A123", futureDate(), "Checkup");
        assertEquals("A123", appt.getAppointmentId());
        assertEquals("Checkup", appt.getDescription());
        assertNotNull(appt.getAppointmentDate());
    }

    @Test
    public void testAppointmentId_Null_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
            new Appointment(null, futureDate(), "Desc")
        );
    }

    @Test
    public void testAppointmentId_Length10_Accepts() {
        String id10 = "1234567890";
        Appointment appt = new Appointment(id10, futureDate(), "Valid");
        assertEquals(id10, appt.getAppointmentId());
    }

    @Test
    public void testAppointmentId_Length11_Throws() {
        String id11 = "12345678901";
        assertThrows(IllegalArgumentException.class, () ->
            new Appointment(id11, futureDate(), "Valid")
        );
    }

    @Test
    public void testAppointmentDate_Null_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
            new Appointment("A123", null, "Desc")
        );
    }

    @Test
    public void testAppointmentDate_InPast_Throws() {
        Date past = new Date(System.currentTimeMillis() - 60_000);
        assertThrows(IllegalArgumentException.class, () ->
            new Appointment("A123", past, "Desc")
        );
    }

    @Test
    public void testDescription_Null_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
            new Appointment("A123", futureDate(), null)
        );
    }

    @Test
    public void testDescription_Length50_Accepts() {
        String desc50 = "12345678901234567890123456789012345678901234567890";
        Appointment appt = new Appointment("A123", futureDate(), desc50);
        assertEquals(desc50, appt.getDescription());
    }

    @Test
    public void testDescription_Length51_Throws() {
        String desc51 = "123456789012345678901234567890123456789012345678901";
        assertThrows(IllegalArgumentException.class, () ->
            new Appointment("A123", futureDate(), desc51)
        );
    }

    @Test
    public void testAppointmentDate_DefensiveCopyOnCreateAndGet() {
        Date future = new Date(System.currentTimeMillis() + 120_000);
        Appointment appt = new Appointment("COPY1", future, "Desc");

        future.setTime(0);
        assertNotEquals(0, appt.getAppointmentDate().getTime());

        Date returned = appt.getAppointmentDate();
        returned.setTime(0);
        assertNotEquals(0, appt.getAppointmentDate().getTime());
    }

    @Test
    public void testSetAppointmentDate_ValidUpdate_Succeeds() {
        Appointment appt = new Appointment("SET1", futureDate(), "Desc");
        Date later = new Date(System.currentTimeMillis() + 300_000);
        appt.setAppointmentDate(later);
        assertEquals(later.getTime(), appt.getAppointmentDate().getTime());
    }

    @Test
    public void testSetAppointmentDate_NullRejected() {
        Appointment appt = new Appointment("SET2", futureDate(), "Desc");
        assertThrows(IllegalArgumentException.class, () -> appt.setAppointmentDate(null));
    }

    @Test
    public void testSetAppointmentDate_PastRejected() {
        Appointment appt = new Appointment("SET3", futureDate(), "Desc");
        Date past = new Date(System.currentTimeMillis() - 60_000);
        assertThrows(IllegalArgumentException.class, () -> appt.setAppointmentDate(past));
    }

    @Test
    public void testSetDescription_ValidUpdate_Succeeds() {
        Appointment appt = new Appointment("SET4", futureDate(), "Desc");
        appt.setDescription("Updated");
        assertEquals("Updated", appt.getDescription());
    }

    @Test
    public void testSetDescription_InvalidRejected() {
        Appointment appt = new Appointment("SET5", futureDate(), "Desc");
        String tooLong = "123456789012345678901234567890123456789012345678901";
        assertThrows(IllegalArgumentException.class, () -> appt.setDescription(null));
        assertThrows(IllegalArgumentException.class, () -> appt.setDescription(tooLong));
    }

    @Test
    public void testAppointmentId_IsImmutable() {
        Appointment appt = new Appointment("IMMUTABLE", futureDate(), "Desc");
        appt.setDescription("New Desc");
        assertEquals("IMMUTABLE", appt.getAppointmentId());
    }
}
