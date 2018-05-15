package chapter5.sleepInterrupt.DaemonThread;

public class DaemonThread implements Runnable {
    @Override
    public void run() {
        System.out.println("entering run()");

        try {
            System.out.println("in run() - currentThread()=" + Thread.currentThread());

            while (true) {
                try {
                    Thread.sleep(200);
                } catch(InterruptedException x) {
                    //ignore
                }
                System.out.println("in run() - woke up again");
            }

        } finally {
            System.out.println("leaving run()");
        }

    }
}
