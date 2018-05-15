package chapter5.sleepInterrupt;

public class InterruptReset {
    public static void main(String[] args) {
        System.out.println("Point x: Thread.interrupted() = " + Thread.interrupted());
        Thread.currentThread().interrupt();
        System.out.println("Point y: Thread.interrupted() = " + Thread.interrupted());
        System.out.println("Point z: Thread.interrupted() = " + Thread.interrupted());
    }
}
