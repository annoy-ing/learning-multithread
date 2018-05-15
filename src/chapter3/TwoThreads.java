package chapter3;

import Chapter2.TwoThread;

public class TwoThreads extends Thread {

    private Thread creatorThread;

    public TwoThreads(){
        creatorThread = Thread.currentThread();
    }


    public void run() {

        for (int i = 0; i < 10; i++) {
            printMsg();
        }


    }

    public void printMsg(){
        Thread t = Thread.currentThread();
        String name = t.getName();
        System.out.println("name = " + name);


    }

    public static void main(String[] args) {
        TwoThreads tt = new TwoThreads();
        tt.setName("My worker thread");
        tt.start();

        for (int i = 0; i < 10; i++) {
            tt.printMsg();
        }
    }

}
