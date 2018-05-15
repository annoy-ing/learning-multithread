package chapter5.sleepInterrupt.DeprecatedSuspend;

public class AlternativeStop implements Runnable {

    private volatile boolean stopRequested;
    private Thread runThread;



    public void run() {
        runThread = Thread.currentThread();
        stopRequested = false;

        int count = 0;

        while (!stopRequested) {
            System.out.println("Running ... count = " + count++);

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }



        }

    }

    public void stopRequest() {
        stopRequested = true;

        if (runThread != null) {
            runThread.interrupt();
        }
    }

    public static void main(String[] args) {
        AlternativeStop as = new AlternativeStop();
        Thread t = new Thread(as);
        t.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //ignore
        }

        as.stopRequest();
    }
}
