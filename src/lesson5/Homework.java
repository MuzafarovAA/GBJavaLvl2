package lesson5;

import java.util.Arrays;

public class Homework {

    static final int SIZE = 10_000_000;
    static final int H = SIZE / 2;

    public static void main(String[] args) {
        float[] arr1 = new float[SIZE];
        Arrays.fill(arr1, 1);
        firstMethod(arr1);

        float[] arr2 = new float[SIZE];
        Arrays.fill(arr2, 1);
        secondMethod(arr2);

    }

    private static void firstMethod(float[] arr) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время работы метода 1: " + (System.currentTimeMillis() - startTime) + "ms.");
    }

    private static void secondMethod(float[] arr) {
        long startTime = System.currentTimeMillis();
        float[] a1 = new float[H];
        float[] a2 = new float[H];
        System.arraycopy(arr, 0, a1, 0, H);
        System.arraycopy(arr, H, a2, 0, H);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < a1.length; i++) {
                a2[i] = (float)(arr[i + H] * Math.sin(0.2f + (i + H) / 5) * Math.cos(0.2f + (i + H) / 5) * Math.cos(0.4f + (i + H) / 2));
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, H);
        System.arraycopy(a2, 0, arr, H, H);

        System.out.println("Время работы метода 2: " + (System.currentTimeMillis() - startTime) + "ms.");
    }

}
