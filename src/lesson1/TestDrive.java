package lesson1;

public class TestDrive {

    public static void main(String[] args) {

        Contestant human1 = new Human("Борис", 5, 150);
        Contestant human2 = new Human("Антон", 1, 15);
        Contestant human3 = new Human("Алексей", 0, 2);
        Contestant cat1 = new Cat("Барсик", 10, 200);
        Contestant cat2 = new Cat("Мурка", 7, 150);
        Contestant cat3 = new Cat("Васька", 11, 105);
        Contestant robot1 = new Robot("Сущность001", 8, 200);
        Contestant robot2 = new Robot("Сущность010", 11, 1000);
        Contestant robot3 = new Robot("Сущность011", 15, 10000);

        Obstruction treadmill1 = new Treadmill(10);
        Obstruction treadmill2 = new Treadmill(100);
        Obstruction treadmill3 = new Treadmill(1000);
        Obstruction wall1 = new Wall(1);
        Obstruction wall2 = new Wall(5);
        Obstruction wall3 = new Wall(10);

        Contestant[] contestants = {human1, human2, human3, cat1, cat2, cat3, robot1, robot2, robot3};
        Obstruction[] obstructions = {wall1, wall2, wall3, treadmill1, treadmill2, treadmill3};

        for (int i = 0; i < contestants.length; i++) {
            for (int j = 0; j < obstructions.length; j++) {
                contestants[i].doActivity(obstructions[j]);
            }

        }

    }

}
