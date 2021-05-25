package lesson1;

public class Treadmill implements Obstruction {

    private final int length;

    public Treadmill(int length) {
        this.length = length;
    }

    @Override
    public void doTry(Contestant contestant) {
        if (contestant.run() >= length) {
            System.out.println(contestant + " успешно пробежал дистанцию " + length + ". Его максимум - " + contestant.getMaxRun());
        } else {
            System.out.println(contestant + " не сумел пробежать дистанцию " + length + ". Его максимум - " + contestant.getMaxRun());
        }
    }

}
