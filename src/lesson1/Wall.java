package lesson1;

public class Wall implements Obstruction {

    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void doTry(Contestant contestant) {
        if (contestant.jump() >= height) {
            System.out.println(contestant + " успешно перепрыгнул препятствие высотой " + height + ". Его максимум - " + contestant.getMaxJump());
        } else {
            System.out.println(contestant + " не сумел перепрыгнуть препятствие высотой " + height + ". Его максимум - " + contestant.getMaxJump());
        }
    }

}
