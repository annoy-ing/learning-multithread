package chapter4;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class SecondCounterLockup extends JComponent {

    private boolean keepRunning;
    private Font paintFont;
    private String timeMsg;
    private int arcLen;

    public SecondCounterLockup() {
        paintFont = new Font("SansSerif", Font.BOLD, 14);
        timeMsg = "never started";
        arcLen = 0;
    }

    public void runClock() {
        System.out.println("thread running runClock() is " + Thread.currentThread().getName());
        DecimalFormat fmt = new DecimalFormat("0.000");
        long normalSleepTime = 100;
        int counter = 0;
        keepRunning = true;

        while (keepRunning){
            try {
                Thread.sleep(normalSleepTime);
            } catch (InterruptedException e) {
                //ignore
            }

            counter++;
            double counterSecs = counter / 10.0;
            timeMsg = fmt.format(counterSecs);
            arcLen = (((int)counterSecs)%60) * 360 / 60;

            repaint();
        }
    }

    public void stopClock(){
        keepRunning = false;
    }

    public void paint(Graphics g){
        System.out.println("THread that invoked paint() is " + Thread.currentThread().getName());

        g.setColor(Color.BLACK);
        g.setFont(paintFont);
        g.drawString(timeMsg, 0, 15);

        g.fillOval(0,20,100,100);


        g.setColor(Color.WHITE);

        g.fillOval(3,23,94,94);

        g.setColor(Color.BLUE);
        g.fillArc(2,22,96,96,90, -arcLen);


    }




}
