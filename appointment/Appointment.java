package appointment;
import java.util.Date;

public class Appointment {

    private final String appointmentId;
    private Date appointmentDate;
    private String description;

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        if (appointmentId == null || appointmentId.length() > 10) {
            throw new IllegalArgumentException("Appointment ID must not be null and must be <= 10 characters.");
        }
        if (appointmentDate == null) {
            throw new IllegalArgumentException("Appointment date must not be null.");
        }
        if (appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past.");
        }
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Description must not be null and must be <= 50 characters.");
        }

        this.appointmentId = appointmentId;
        this.appointmentDate = new Date(appointmentDate.getTime()); // defensive copy
        this.description = description;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        return new Date(appointmentDate.getTime()); // defensive copy
    }

    public String getDescription() {
        return description;
    }

    // Not required by this milestone, but safe and validated if you need it later
    public void setAppointmentDate(Date appointmentDate) {
        if (appointmentDate == null) {
            throw new IllegalArgumentException("Appointment date must not be null.");
        }
        if (appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past.");
        }
        this.appointmentDate = new Date(appointmentDate.getTime());
    }

    // Not required by this milestone, but safe and validated if you need it later
    public void setDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Description must not be null and must be <= 50 characters.");
        }
        this.description = description;
    }
}

