import java.util.HashMap;
import java.util.LinkedList;

public class PhoneContacts {

    public static HashMap<String, LinkedList<Contact>> phonebook = new HashMap<>();

    public static void createGroup(String title) {
        if (!phonebook.containsKey(title)) {
            phonebook.put(title, new LinkedList<>());
        } else {
            System.out.println("Такая группа уже есть! Введите другое название!");
        }
    }

    public static void addContactToGroup(Contact contact, String[] groups) {
        for (String group : groups) {
            if (phonebook.containsKey(group)) {
                phonebook.get(group).add(contact);
            }
        }
    }

    public static String[] getGroups() {
        return phonebook.keySet().toArray(new String[0]);
    }

    public static Contact[] getContact(String group) { return phonebook.get(group).toArray(new Contact[0]); }

}