package lesson3;

import java.util.*;

public class PhoneBookV2 {

    private final Map<String, Set<String>> phoneBook = new TreeMap<>();

    public void add(String name, String phone) {
        Set<String> phones = new TreeSet<>();
        if (get(name) != null) {
            phones = get(name);
        }
        phones.add(phone);
        phoneBook.put(name, phones);
    }

    public Set<String> get(String name) {
        return phoneBook.get(name);
    }

    public void getPhoneBook() {
        for (String s : phoneBook.keySet()) {
            System.out.println(s + ": " + phoneBook.get(s));
        }
    }

}
