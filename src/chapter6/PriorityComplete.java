package chapter6;



public class PriorityComplete {
    private volatile int count;
    private boolean yield;
    private Thread internalThread;
    private volatile boolean noStopRequested;

    public PriorityComplete(String name, int priority, boolean yield) {
        count = 0;
        this.yield = yield;

        noStopRequested = true;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    runWork();
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        };
        internalThread = new Thread(r, name);
        internalThread.setPriority(priority);
    }

    private void runWork() {
        Thread.yield();

        while (noStopRequested) {
            if (yield){
                Thread.yield();
            }

            count++;

            for (int i = 0; i < 1000; i++) {
                double x = i * Math.PI / Math.E;
            }
        }

    }

    public void startRequest() {
        internalThread.start();
    }

    public void stopRequest() {
        noStopRequested = false;
    }

    public int getCount() {
        return count;
    }

    public String getNameAndPriority() {
        return internalThread.getName() + ": priority=" + internalThread.getPriority();
    }

    private static void runSet(boolean yield) {
        PriorityComplete[] pc = new PriorityComplete[3];
        pc[0] = new PriorityComplete("PC0", 3, yield);
        pc[1] = new PriorityComplete("PC1", 6, yield);
        pc[2] = new PriorityComplete("PC2", 6, yield);

        //let the dust settle for a bit before starting them

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //ignore
        }

        for (PriorityComplete p: pc
             ) {
            p.startRequest();
        }

        long startTime = System.currentTimeMillis();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            //ignore
        }

        for (PriorityComplete p: pc
                ) {
            p.stopRequest();
        }

        long stopTime = System.currentTimeMillis();
        // let the things settle down again
        try {
            Thread.sleep(1000);
        } catch (InterruptedException x){
            //ignore
        }

        int totalCount = 0;
        for (PriorityComplete p: pc
                ) {
            totalCount += p.getCount();
        }

        System.out.println("totalCount = " + totalCount + ", count/ms="
         + ((double) totalCount) / (stopTime - startTime));

        for (PriorityComplete p: pc
                ) {
            double perc = (100.0 * p.getCount() / totalCount);
            System.out.println(p.getNameAndPriority() + ", " + perc + "%, count=" + p.getCount());
        }

    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("run without using yield()");
                System.out.println("=========================");
                runSet(false);

                System.out.println();
                System.out.println("Run using yield()");
                System.out.println("=================");
                runSet(true);
            }

        };

        Thread t = new Thread(r, "PriorityComplete");
        t.setPriority(Thread.MAX_PRIORITY - 1);
        t.start();
    }
}
