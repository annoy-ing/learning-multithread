package chapter5.sleepInterrupt;

public class SleepInterrupt extends Object implements Runnable {

    public void run() {
        try {
            System.out.println("in run() - about to sleep around 20 secs");
            Thread.sleep(20000);
            System.out.println("in run() - woke up");
        } catch (InterruptedException e) {
            System.out.println("in run() - interrupted while sleeping");
            return;
        }

        System.out.println("in run() - doing stuff after nap");
        System.out.println("in run() - leaving normally");
    }

    public static void main(String[] args) {
        SleepInterrupt si = new SleepInterrupt();
        Thread t = new Thread(si);
        t.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("in main() - interrupting other thread");
        t.interrupt();
        System.out.println("in main() - leaving");
    }
}
