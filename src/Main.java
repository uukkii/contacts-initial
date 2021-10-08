import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner console = new Scanner(System.in);
    private static final String equalCheck = "нет";
    private static final String defaultSwitchAnswer = "Неправильный ввод!";

    public static void main(String[] args) {
        PhoneContacts contacts = new PhoneContacts();
        System.out.println("Добро пожаловать в программу 'Телефонная книга'!");
        loop:
        while (true) {
            System.out.println("""
                    Введите номер нужной команды:\s
                    1. Добавить группу контактов.
                    2. Добавить контакт.
                    3. Показать телефонную книгу.
                    Или введите '0', чтобы выйти из программы.""");
            int input = console.nextInt();
            switch (input) {
                case 1 -> addGroup(contacts);
                case 2 -> addContact(contacts);
                case 3 -> {
                    System.out.println("""
                            Хотите отсортировать контакты по имени?:\s
                            1. Да.
                            2. Нет.
                            Или введите '0' для возврата в главное меню.""");
                    int secondInput = console.nextInt();
                    switch (secondInput) {
                        case 1 -> showSortedPhonebook(contacts);
                        case 2 -> showPhonebook(contacts);
                        case 0 -> {
                        }
                        default -> System.out.println(defaultSwitchAnswer);
                    }
                }
                case 0 -> {
                    System.out.println("Спасибо за использование!");
                    break loop;
                }
                default -> System.out.println(defaultSwitchAnswer);
            }
        }
    }

    private static void addGroup(PhoneContacts contacs) {
        console.nextLine();
        while (true) {
            System.out.println("Для добавления новой группы введите ее название.\n" +
                    "Или введите 'нет' для возврата в главное меню.");
            String inputTitle = console.nextLine();
            if (equalCheck.equals(inputTitle)) {
                break;
            } else contacs.createGroup(inputTitle);
        }
    }

    private static void addContact(PhoneContacts contacts) {
        console.nextLine();
        while (true) {
            System.out.println("Для добавления нового контакта введите его имя и " +
                    "номер телефона через пробел (Пример: Name +7ХХХХХХХХХХ)" +
                    " или 'нет' возврата в главное меню");
            String inputContact = console.nextLine();
            if (equalCheck.equals(inputContact)) {
                break;
            } else {
                String[] arrayContact = inputContact.split(" ");
                String name = arrayContact[0], number = arrayContact[1];
                Contact newContact = new Contact(name, number);

                System.out.println("Для добавления контакта в группу, введите названия групп через пробел (Пример: Семья Работа)"
                        + " или введите 'нет' для добавления нового контакта");
                String addToGroup = console.nextLine();
                if (equalCheck.equals(addToGroup)) {
                    break;
                } else {
                    String[] inputGroups = addToGroup.split(" ");
                    contacts.addContactToGroup(newContact, inputGroups);
                }
            }
        }
    }

    private static void showPhonebook(PhoneContacts contacts) {
        System.out.println("Группы в справочнике: ");
        String[] arrGroups = contacts.getGroups();
        for (String group : arrGroups) {
            System.out.println("- " + group + ":");
            for (Contact contact : contacts.getContact(group)) {
                System.out.println("\tИмя: " + contact.getName() + ", Телефон: " + contact.getNumber());
            }
            System.out.println();
        }
    }

    private static void showSortedPhonebook(PhoneContacts contacts) {
        System.out.println("Группы в справочнике: ");

        String[] arrGroups = contacts.getGroups();
        for (String group : arrGroups) {
            System.out.println("- " + group + ":");
            List<Contact> sortedContacts = new ArrayList<>();
            for (Contact contact : contacts.getContact(group)) {
                if (!sortedContacts.contains(contact)) {
                    sortedContacts.add(contact);
                }
            }
            sortedContacts.sort(Comparator.naturalOrder());
            for (Contact contactInList : sortedContacts) {
                System.out.println("\tИмя: " + contactInList.getName() + ", Телефон: " + contactInList.getNumber());
            }
            System.out.println();
        }
    }
}