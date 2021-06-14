package lesson3;

import java.util.*;

public class HomeworkPt1 {
    public static void main(String[] args) {

        String[] array = {"Яблоко", "Пони", "Орех", "Паста", "Кофе", "Яблоко", "Часы", "Чайник", "Паста", "Яблоко", "Вишня", "Вишня", "Молоко", "Яблоко", "Перец", "Часы", "Кружка", "Ложка", "Мышка", "Кофе"};

        FindUnique(array);

        System.out.println("-----");

        CountRepeat(array);

    }

    private static void FindUnique(String[] array) {

        Set<String> set = new TreeSet<>();
        for (String s : array) {
            set.add(s);
        }

        System.out.println("Перечень уникальных слов:");
        System.out.println(set);

    }

    private static void CountRepeat(String[] array) {

        Map<String, Integer> hm = new TreeMap<>();

        for (String str : array) {
            int count = 1;
            if (hm.containsKey(str)) {
                count += hm.get(str);
            }
            hm.put(str, count);
        }

        System.out.println("Количество повторений слов:");
        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }
}
