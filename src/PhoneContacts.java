import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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

    public static void showContacts() {
        for (Map.Entry<String, LinkedList<Contact>> entry : phonebook.entrySet()) {
            System.out.println("Контакты в группе: " + entry.getKey() + "\n" + entry.getValue() + "\n");
        }
    }
}