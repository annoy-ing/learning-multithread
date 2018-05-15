package chapter7;

public class OnlyOneInMethod {
    private String objID;

    public OnlyOneInMethod(String obj) {
        this.objID = obj;
    }

    public synchronized void doStuff(int val) {
        print("entering doStuff()");
        int num = val * 2 + objID.length();
        print("in doStuff() - local variable num=" + num);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        print("leaving doStuff()");
    }

    public void print(String msg){
        threadPrint("objID=" + objID + " - " + msg);
    }

    public static void threadPrint(String msg) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ": " + msg);

    }

    public static void main(String[] args) {
        final OnlyOneInMethod ooim = new OnlyOneInMethod("obj1");

        Runnable runA = new Runnable() {
            @Override
            public void run() {
                ooim.doStuff(3);
            }
        };

        Thread threadA = new Thread(runA, "threadA");
        threadA.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            //ignore
        }

        Runnable runB = new Runnable() {
            @Override
            public void run() {
                ooim.doStuff(8);
            }
        };

        Thread threadB = new Thread(runB, "threadB");
        threadB.start();

    }



}
