package chapter5.sleepInterrupt.PinInterrupt;

public class PinInterrupt implements Runnable {
    private double latestPiEstimate;



    public void run() {
        try {
            System.out.println("for comparison, Math.PI =       " + Math.PI);
            calcPi(0.00001);
            System.out.println("within accuracy, latest PI =    " + latestPiEstimate);
        } catch (InterruptedException e) {
            System.out.println("Interrupted! Latest PI =        " + latestPiEstimate);
        }
    }

    private void calcPi(double accuracy) throws InterruptedException {

        latestPiEstimate = 0.0;
        long iteration = 0;
        int sign = -1;

        while (Math.abs(latestPiEstimate - Math.PI) > accuracy) {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }

            iteration++;
            sign = -sign;
            latestPiEstimate += sign * 4.0 / ((2 * iteration) - 1);
        }
    }

    public static void main(String[] args) {
        PinInterrupt pi = new PinInterrupt();
        Thread t = new Thread(pi);
        t.start();

        try {
            Thread.sleep(10000);
            t.interrupt();
        } catch (InterruptedException e) {
            //ignore
        }
    }

}
