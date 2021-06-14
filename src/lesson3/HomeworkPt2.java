package lesson3;

public class HomeworkPt2 {

    public static void main(String[] args) {

        PhoneBook phonebook = new PhoneBook();
        phonebook.add("Иванов", "89194102561");
        phonebook.add("Петров", "89194105555");
        phonebook.add("Сидоров", "89194107777");
        phonebook.add("Николаев", "89194108888");
        phonebook.add("Сидоров", "89194108821");

        phonebook.getPhoneBook();

        System.out.println("-----");

        PhoneBookV2 phonebook2 = new PhoneBookV2();
        phonebook2.add("Иванов", "89194102561");
        phonebook2.add("Петров", "89194105555");
        phonebook2.add("Сидоров", "89194107777");
        phonebook2.add("Николаев", "89194108888");
        phonebook2.add("Сидоров", "89194108821");

        phonebook2.getPhoneBook();

    }

}
