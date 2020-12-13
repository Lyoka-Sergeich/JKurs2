package Hwk5;

public class Main {

    public static void main(String[] args) {

        method1();
        method2();
        System.out.println("Worktime comparing");


    }


    static class MyRunnableClass1 implements Runnable {
        float[] a;
        int sz;
        @Override
        public void run() {
            method2_1(a,sz);
        }
    }
    static class MyRunnableClass2 implements Runnable {
        float[] a;
        int sz;
        @Override
        public void run() {
            method2_2(a,sz);
        }
    }


    //обработка массива
    static void method1(){
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        //подготовим
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        //начнём
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Worktime of method1 = " + (System.currentTimeMillis() - a));
    }
    //обработка разбитого массива
    static void method2(){
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        //подготовим
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }


//        Runnable r1 = Main::method2_1(a1,h);
//        Runnable r2 = Main::method2_2;
//        new Thread(r1, "Thread#0").start();
//        new Thread(r2, "Thread#1").start();
        //создаём потоки
        MyRunnableClass1 r1 = new MyRunnableClass1();
        r1.a = a1;
        r1.sz = h;
        MyRunnableClass2 r2 = new MyRunnableClass2();
        r2.a = a2;
        r2.sz = h;

        long a = System.currentTimeMillis();
        //начнём
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println("Worktime of method2 = " + (System.currentTimeMillis() - a));
    }


    //обработка разбитого массива
    public static void method2_1(float[] a, int sz){
        for (int i = 0; i < sz; i++) {
            a[i] = (float)(a[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
    public static void method2_2(float[] a, int sz){
        //поскольку начинаем не с начала исходника, то и применять формулу будем, учитывая смещение
        for (int i = 0; i < sz; i++) {
            a[i] = (float)(a[i] * Math.sin(0.2f + (i+sz) / 5) * Math.cos(0.2f + (i+sz) / 5) * Math.cos(0.4f + (i+sz) / 2));
        }
    }
}
