import java.util.*;

public class PhoneBook {
    private Map<String, Set<String>> contacts;

    public PhoneBook() {
        contacts = new HashMap<>();
    }

    // Добавление контакта
    public void addContact(String name, String phoneNumber) {
        contacts.computeIfAbsent(name, k -> new HashSet<>()).add(phoneNumber);
    }

    // Получение всех телефонов для указанного имени
    public Set<String> getPhoneNumbers(String name) {
        return contacts.getOrDefault(name, Collections.emptySet());
    }

    // Получение отсортированного списка контактов по убыванию числа телефонов
    public List<String> getSortedContacts() {
        List<Map.Entry<String, Set<String>>> sortedList = new ArrayList<>(contacts.entrySet());

        // Сортируем по убыванию числа телефонов
        sortedList.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : sortedList) {
            String name = entry.getKey();
            Set<String> phoneNumbers = entry.getValue();
            result.add(name + ": " + phoneNumbers.toString());
        }

        return result;
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addContact("John", "123456789");
        phoneBook.addContact("John", "987654321");
        phoneBook.addContact("Alice", "111111111");
        phoneBook.addContact("Bob", "999999999");
        phoneBook.addContact("Bob", "555555555");

        // Получаем телефоны для имени "John"
        Set<String> johnPhones = phoneBook.getPhoneNumbers("John");
        System.out.println("John's phones: " + johnPhones);

        // Получаем отсортированный список контактов
        List<String> sortedContacts = phoneBook.getSortedContacts();
        System.out.println("Sorted Contacts:");
        for (String contact : sortedContacts) {
            System.out.println(contact);
        }
    }
}
