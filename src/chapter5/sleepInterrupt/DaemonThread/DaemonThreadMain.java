package chapter5.sleepInterrupt.DaemonThread;

public class DaemonThreadMain extends Object {
    public static void main(String[] args) {
        System.out.println("entering main()");

        Thread t = new Thread(new DaemonThread());
        t.setDaemon(true);

        t.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("leaving main()");
    }

}
