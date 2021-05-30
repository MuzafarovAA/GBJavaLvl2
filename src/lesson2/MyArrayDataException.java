package lesson2;

public class MyArrayDataException extends NumberFormatException {

    public MyArrayDataException(int i, int j) {
        super("Неверный формат данных в ячейке: [" + i + ", " + j + "]");
    }

}
