package lesson5;

public class Main {
    public static void main(String[] args) {

        System.out.printf("Время работы первого метода: " + firstMethod(), size);
        System.out.printf("\nВремя работы второго метода: " + secondMethod(), size);
    }

    static final int size = 1000000;
    static final int h = size / 2;

    public static long firstMethod() {
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f + (float) i / 5) * Math.cos(0.4f + (float) i / 2));
        }
        return (System.currentTimeMillis() - a);
    }

    public static long secondMethod() {
        final float[] arr = new float[size];
        final float[] arr1 = new float[h];
        final float[] arr2 = new float[h];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                arr1[i] = (float) (arr1[i] * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f + (float) i / 5) * Math.cos(0.4f + (float) i / 2));
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                arr2[i] = (float) (arr2[i] * Math.sin(0.2f + (float) (i + h) / 5) * Math.cos(0.2f + (float) (i + h) / 5) * Math.cos(0.4f + (float) (i + h) / 2));
            }
        });

        t1.start();
        t2.start();

        try {
            t1.sleep(200);
            t2.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return (System.currentTimeMillis() - a);
    }
}
