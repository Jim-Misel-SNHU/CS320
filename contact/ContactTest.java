package contact;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    void testCreateContact_ValidInputs_Succeeds() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertNotNull(contact);
        assertEquals("12345", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    void testContactId_NullRejected() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact(null, "John", "Doe", "1234567890", "123 Main St")
        );
    }

    @Test
    void testContactId_Exactly10Accepted() {
        Contact contact = new Contact("1234567890", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("1234567890", contact.getContactId());
    }

    @Test
    void testContactId_LongerThan10Rejected() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St")
        );
    }

    @Test
    void testFirstName_NullRejected() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", null, "Doe", "1234567890", "123 Main St")
        );
    }

    @Test
    void testFirstName_TooLongRejected() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "12345678901", "Doe", "1234567890", "123 Main St")
        );
    }

    @Test
    void testFirstName_Exactly10Accepted() {
        Contact c = new Contact("1", "ABCDEFGHIJ", "Doe", "1234567890", "123 Main St");
        assertEquals("ABCDEFGHIJ", c.getFirstName());
    }

    @Test
    void testLastName_NullRejected() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "John", null, "1234567890", "123 Main St")
        );
    }

    @Test
    void testLastName_TooLongRejected() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "John", "12345678901", "1234567890", "123 Main St")
        );
    }

    @Test
    void testLastName_Exactly10Accepted() {
        Contact c = new Contact("1", "John", "ABCDEFGHIJ", "1234567890", "123 Main St");
        assertEquals("ABCDEFGHIJ", c.getLastName());
    }

    @Test
    void testPhone_NullRejected() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "John", "Doe", null, "123 Main St")
        );
    }

    @Test
    void testPhone_NotExactlyTenDigitsRejected_TooShort() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "John", "Doe", "12345", "123 Main St")
        );
    }

    @Test
    void testPhone_NotExactlyTenDigitsRejected_Letters() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "John", "Doe", "12345ABCDE", "123 Main St")
        );
    }

    @Test
    void testPhone_ExactlyTenDigitsAccepted() {
        Contact c = new Contact("1", "John", "Doe", "0123456789", "123 Main St");
        assertEquals("0123456789", c.getPhone());
    }

    @Test
    void testAddress_NullRejected() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "John", "Doe", "1234567890", null)
        );
    }

    @Test
    void testAddress_TooLongRejected() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("1", "John", "Doe", "1234567890", "1234567890123456789012345678901")
        );
    }

    @Test
    void testAddress_Exactly30Accepted() {
        String addr30 = "123456789012345678901234567890";
        Contact c = new Contact("1", "John", "Doe", "1234567890", addr30);
        assertEquals(addr30, c.getAddress());
    }

    @Test
    void testSetters_ValidUpdatesWork() {
        Contact c = new Contact("1", "John", "Doe", "1234567890", "123 Main St");

        c.setFirstName("Jim");
        c.setLastName("Misel");
        c.setPhone("1112223333");
        c.setAddress("456 Oak Street");

        assertEquals("Jim", c.getFirstName());
        assertEquals("Misel", c.getLastName());
        assertEquals("1112223333", c.getPhone());
        assertEquals("456 Oak Street", c.getAddress());
    }

    @Test
    void testSetters_InvalidUpdatesRejected() {
        Contact c = new Contact("1", "John", "Doe", "1234567890", "123 Main St");

        assertThrows(IllegalArgumentException.class, () -> c.setFirstName("12345678901"));
        assertThrows(IllegalArgumentException.class, () -> c.setLastName("12345678901"));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("12345"));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress("1234567890123456789012345678901"));
    }
}

