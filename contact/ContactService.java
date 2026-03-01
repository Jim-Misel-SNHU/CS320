package contact;

import java.util.HashMap;
import java.util.Map;

public class ContactService {

    private Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("Contact ID already exists");
        }
        contacts.put(contact.getContactId(), contact);
    }

    public void deleteContact(String contactId) {
        contacts.remove(contactId);
    }

    // Helpful for testing "adding and retrieving a contact"
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }

    public void updateFirstName(String contactId, String firstName) {
        contacts.get(contactId).setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        contacts.get(contactId).setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        contacts.get(contactId).setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        contacts.get(contactId).setAddress(address);
    }
}

