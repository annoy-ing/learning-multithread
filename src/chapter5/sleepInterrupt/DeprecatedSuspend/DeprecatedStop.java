package chapter5.sleepInterrupt.DeprecatedSuspend;

public class DeprecatedStop implements Runnable {



    public void run() {
        int count = 0;

        while (true) {
            System.out.println("Running ... count = " + count++);

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                //ignore
            }



        }

    }

    public static void main(String[] args) {
        DeprecatedStop ds = new DeprecatedStop();
        Thread t = new Thread(ds);
        t.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //ignore
        }

        t.stop();
    }
}
