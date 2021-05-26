package lesson2;

public class MyArraySizeException extends RuntimeException{

    public MyArraySizeException() {
        super("Массив некорректного размера");
    }

}
