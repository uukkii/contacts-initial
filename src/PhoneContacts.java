import java.util.HashMap;
import java.util.LinkedList;

public final class PhoneContacts {

    private static HashMap<String, LinkedList<Contact>> phonebook;

    public PhoneContacts() {
        phonebook = new HashMap<>();
    }

    public void createGroup(String title) {
        if (!phonebook.containsKey(title)) {
            phonebook.put(title, new LinkedList<>());
        } else {
            System.out.println("Такая группа уже есть! Введите другое название!");
        }
    }

    public void addContactToGroup(Contact contact, String[] groups) {
        for (String group : groups) {
            if (phonebook.containsKey(group)) {
                phonebook.get(group).add(contact);
            }
        }
    }

    public String[] getGroups() {
        return phonebook.keySet().toArray(new String[0]);
    }

    public Contact[] getContact(String group) { return phonebook.get(group).toArray(new Contact[0]); }

}