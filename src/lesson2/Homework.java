package lesson2;

public class Homework {

    public static void main(String[] args) {

        String[][] array1 = {{"1", "2", "3", "4"}, {"5", "6", "7", "8"}, {"9", "10", "11", "12"}, {"13", "14", "15", "16"}};
        String[][] array2 = {{"1", "2", "3", "4"}, {"5", "6", "7"}, {"9", "10", "11", "12"}, {"13", "14", "15", "16"}};
        String[][] array3 = {{"1", "2", "3", "4"}, {"5", "6", "7", "8"}, {"9", "10", "wasd", "12"}, {"13", "14", "15", "16"}};

        try {
            System.out.println("Сумма = " + autoSum(array1));
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Сумма = " + autoSum(array2));
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Сумма = " + autoSum(array3));
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        }

    }

    private static int autoSum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        checkSize(array);
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }

    private static void checkSize(String[][] array) {
        int CORRECT_SIZE = 4;
        if (array.length != CORRECT_SIZE) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != CORRECT_SIZE) {
                throw new MyArraySizeException();
            }
        }
    }

}
