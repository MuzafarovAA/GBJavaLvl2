package lesson1;

public class Robot implements Contestant {

    private final String name;
    private final int maxJump;
    private final int maxRun;

    public Robot(String name, int maxJump, int maxRun) {
        this.name = name;
        this.maxJump = maxJump;
        this.maxRun = maxRun;
    }

    public int getMaxJump() {
        return maxJump;
    }

    public int getMaxRun() {
        return maxRun;
    }

    @Override
    public int run() {
        System.out.println("Человек " + name + " пытается пробежать дистанцию");
        return maxRun;
    }

    @Override
    public int jump() {
        System.out.println("Человек " + name + " пытается перепрыгнуть препятствие");
        return maxJump;
    }

    @Override
    public void doActivity(Obstruction obstruction) {
        obstruction.doTry(this);
    }

    @Override
    public String toString() {
        return name;
    }

}
