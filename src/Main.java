import java.util.Scanner;

public class Main {

    public static Scanner console = new Scanner(System.in);
    public static String[] arrayContact = new String[1];

    public static void main(String[] args) {
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
                case 1 -> addGroup();
                case 2 -> addContact();
                case 3 -> showPhonebook();
                case 0 -> {
                    System.out.println("Спасибо за использование!");
                    break loop;
                }
                default -> System.out.println("Неправильный ввод!");
            }
        }
    }

    public static void addGroup() {
        String emptyLine = console.nextLine();
        while (true) {
            System.out.println("Для добавления новой группы введите ее название.\n" +
                    "Или введите 'нет' для возврата в главное меню.");
            String title = console.nextLine();
            if ("нет".equals(title)) {
                break;
            } else PhoneContacts.createGroup(title);
        }
    }

    public static void addContact() {
        String emptyLine = console.nextLine();
        while (true) {
            System.out.println("Для добавления нового контакта введите его имя и " +
                    "номер телефона через пробел (Пример: Name +7ХХХХХХХХХХ)" +
                    " или 'нет' возврата в главное меню");
            String inputContact = console.nextLine();
            if ("нет".equals(inputContact)) {
                break;
            } else {
                arrayContact = inputContact.split(" ");
                String name = arrayContact[0], number = arrayContact[1];
                Contact newContact = new Contact(name, number);

                System.out.println("Для добавления контакта в группу, введите названия групп через пробел (Пример: Семья Работа)"
                        + " или введите 'нет' для добавления нового контакта");
                String addToGroup = console.nextLine();
                if ("нет".equals(addToGroup)) {
                    break;
                } else {
                    String[] inputGroups = addToGroup.split(" ");
                    PhoneContacts.addContactToGroup(newContact, inputGroups);
                }
            }
        }
    }

    public static void showPhonebook() {
        System.out.println("Группы в справочнике: ");
        PhoneContacts pc = new PhoneContacts();
        String[] arrGroups = pc.getGroups();
        for (String group : arrGroups) {
            System.out.println("- " + group + ":");
            for (Contact contact : pc.getContact(group)) {
                System.out.println("\tИмя: " + contact.getName() + ", Телефон: " + contact.getNumber());
            }
            System.out.println();
        }
    }
}