package de.yanaDanilova.mt;



public class MainApp {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {

        oneThread ();
        twoTread();

    }

    public static void oneThread (){

        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - a);
    }

    public static void twoTread(){
        float[] array = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            array[i] = 1;
        }
        long a = System.currentTimeMillis();

        float[] array1 = new float[HALF];
        System.arraycopy(array,0,array1,0,HALF);
        float[] array2 = new float[HALF];
        System.arraycopy(array,HALF,array2,0,HALF);

        Thread t1 = new Thread((new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < HALF; i++) {
                    array1[i] = (float)(array1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        }));

        Thread t2 = new Thread((new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < HALF; i++) {
                    array2[i] = (float)(array2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        }));

        t1.start();
        t2.start();

        System.arraycopy(array1,0,array,0,HALF);
        System.arraycopy(array2,0,array,HALF,HALF);

        System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - a);
    }
}


