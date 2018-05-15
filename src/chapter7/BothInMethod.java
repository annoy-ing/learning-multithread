package chapter7;

public class BothInMethod {
    private String objID;

    public BothInMethod(String objID) {
        this.objID = objID;
    }

    public void doStuff (int val) {
        print("entering doStuff()");
        int num = val * 2 + objID.length();
        print("in doStuff() - local variable num=" + num);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException x) {
            //ignore
        }

        print("leaving doStuff()");
    }

    public void print(String msg) {
        threadPrint("objID=" + objID + " - " + msg);
    }

    public void threadPrint(String msg) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ": " + msg);
    }

    public static void main(String[] args) {
        final BothInMethod bim = new BothInMethod("obj1");

        Runnable runA = new Runnable() {

            public void run() {
                bim.doStuff(3);
            }
        };

        Thread threadA = new Thread(runA, "threadA");
        threadA.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException x) {
            //ignore
        }

        Runnable runB = new Runnable() {
            @Override
            public void run() {
                bim.doStuff(7);
            }
        };

        Thread threadB = new Thread(runB, "threadB");
        threadB.start();

        Runnable runC = new Runnable() {

            public void run() {
                bim.doStuff(9);
            }
        };

        Thread threadC = new Thread(runC, "threadC");
        threadC.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException x) {
            //ignore
        }

        Runnable runD = new Runnable() {
            @Override
            public void run() {
                bim.doStuff(11);
            }
        };

        Thread threadD = new Thread(runB, "threadD");
        threadD.start();


    }

}
