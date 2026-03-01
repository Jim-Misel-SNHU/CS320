package contact;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    @Test
    void testAddSingleContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");

        service.addContact(contact);

        assertNotNull(service.getContact("1"));
        assertEquals("John", service.getContact("1").getFirstName());
    }

    @Test
    void testAddMultipleContacts() {
        ContactService service = new ContactService();
        Contact c1 = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        Contact c2 = new Contact("2", "Jane", "Smith", "0987654321", "456 Oak St");

        service.addContact(c1);
        service.addContact(c2);

        assertNotNull(service.getContact("1"));
        assertNotNull(service.getContact("2"));
        assertEquals("Jane", service.getContact("2").getFirstName());
    }

    @Test
    void testAddNullContactRejected() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    @Test
    void testDuplicateContactIdRejected() {
        ContactService service = new ContactService();
        Contact c1 = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        Contact c2 = new Contact("1", "Jane", "Smith", "0987654321", "456 Oak St");

        service.addContact(c1);

        assertThrows(IllegalArgumentException.class, () -> service.addContact(c2));
    }

    @Test
    void testUpdateContactFields() {
        ContactService service = new ContactService();
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);

        service.updateFirstName("1", "Jim");
        service.updateLastName("1", "Misel");
        service.updatePhone("1", "1112223333");
        service.updateAddress("1", "789 Pine Road");

        Contact updated = service.getContact("1");
        assertEquals("Jim", updated.getFirstName());
        assertEquals("Misel", updated.getLastName());
        assertEquals("1112223333", updated.getPhone());
        assertEquals("789 Pine Road", updated.getAddress());
    }

    @Test
    void testUpdateInvalidPhoneRejected() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "John", "Doe", "1234567890", "123 Main St"));

        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1", "12345"));
    }

    @Test
    void testUpdateNonexistentContactThrowsNullPointer() {
        ContactService service = new ContactService();
        assertThrows(NullPointerException.class, () -> service.updateFirstName("MISSING", "Jim"));
    }

    @Test
    void testDeleteContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);

        service.deleteContact("1");

        assertNull(service.getContact("1"));
    }

    @Test
    void testDeleteNonexistentContactDoesNothing() {
        ContactService service = new ContactService();
        service.deleteContact("NOPE");
        assertNull(service.getContact("NOPE"));
    }
}
