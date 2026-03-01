package appointment;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class AppointmentServiceTest {

    private Date futureDate() {
        return new Date(System.currentTimeMillis() + 60_000);
    }

    @Test
    public void testAddAppointment_UniqueId_Succeeds() {
        AppointmentService service = new AppointmentService();
        Appointment appt = new Appointment("A1", futureDate(), "Desc");

        service.addAppointment(appt);

        assertEquals(1, service.getAppointmentCount());
        assertNotNull(service.getAppointment("A1"));
        assertEquals("A1", service.getAppointment("A1").getAppointmentId());
    }

    @Test
    public void testAddAppointment_NullRejected() {
        AppointmentService service = new AppointmentService();
        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(null));
    }

    @Test
    public void testAddAppointment_DuplicateId_Throws() {
        AppointmentService service = new AppointmentService();

        Appointment appt1 = new Appointment("DUP1", futureDate(), "Desc1");
        Appointment appt2 = new Appointment("DUP1", futureDate(), "Desc2");

        service.addAppointment(appt1);

        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(appt2));
    }

    @Test
    public void testDeleteAppointment_ExistingId_Succeeds() {
        AppointmentService service = new AppointmentService();
        Appointment appt = new Appointment("DEL1", futureDate(), "Desc");

        service.addAppointment(appt);
        assertEquals(1, service.getAppointmentCount());

        service.deleteAppointment("DEL1");

        assertEquals(0, service.getAppointmentCount());
        assertNull(service.getAppointment("DEL1"));
    }

    @Test
    public void testDeleteAppointment_UnknownId_Throws() {
        AppointmentService service = new AppointmentService();
        assertThrows(IllegalArgumentException.class, () -> service.deleteAppointment("NOPE"));
    }

    @Test
    public void testDeleteAppointment_NullRejected() {
        AppointmentService service = new AppointmentService();
        assertThrows(IllegalArgumentException.class, () -> service.deleteAppointment(null));
    }

    @Test
    public void testService_DoesNotChangeAppointmentId() {
        AppointmentService service = new AppointmentService();
        String id = "FIXEDID10";

        Appointment appt = new Appointment(id, futureDate(), "Original");
        service.addAppointment(appt);

        appt.setDescription("Updated");
        assertEquals(id, appt.getAppointmentId());

        assertNotNull(service.getAppointment(id));
        assertEquals("Updated", service.getAppointment(id).getDescription());
    }
}
