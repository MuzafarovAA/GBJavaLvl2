package lesson3;

import java.util.*;

public class PhoneBook {

    private final Map<String, String> phoneBook = new TreeMap<>();

    public void add(String name, String phone) {
        if (get(name) != null) {
            phone = get(name) + ", " + phone;
        }
        phoneBook.put(name, phone);
    }

    public String get(String name) {
        return phoneBook.get(name);
    }

    public void getPhoneBook() {
        for (String s : phoneBook.keySet()) {
            System.out.println(s + ": " + phoneBook.get(s));
        }
    }

}
