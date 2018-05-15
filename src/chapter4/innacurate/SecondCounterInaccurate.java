package chapter4.innacurate;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class SecondCounterInaccurate extends JComponent implements Runnable {
    private volatile boolean keepRunning;
    private Font paintFont;
    private volatile String timeMsg;
    private volatile int arcLen;

    public SecondCounterInaccurate() {
        paintFont = new Font("SansSerif", Font.BOLD, 14);
        timeMsg = "never started";
        arcLen = 0;
    }

    public void run() {
        runClock();
    }

    public void runClock() {
        DecimalFormat ftm = new DecimalFormat("0.000");
        long normalSleepTime = 100;
        long nextSleepTime = normalSleepTime;

        int counter = 0;
        long startTime = System.currentTimeMillis();
        keepRunning = true;

        while (keepRunning) {
            try {
                Thread.sleep(nextSleepTime);
            } catch (InterruptedException e) {
                //ignoore
            }


        counter++;
        double counterSecs = counter / 10.0;
        double elapsedSecs = (System.currentTimeMillis() - startTime) / 1000.0;

        double diffSecs = counterSecs - elapsedSecs;
        nextSleepTime = normalSleepTime + ((long) (diffSecs * 1000.0));

        if (nextSleepTime < 0) {
            nextSleepTime = 0;
        }

        timeMsg = ftm.format(counterSecs) + " - " + ftm.format(elapsedSecs) + " = " + ftm.format(diffSecs);

        arcLen = (((int) counterSecs) % 60) * 360 / 60;
        repaint();
        }
    }

    public void stopClock() {
        keepRunning = false;
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(paintFont);
        g.drawString(timeMsg, 0, 15);

        g.fillOval(0,20,100,100);

        g.setColor(Color.WHITE);
        g.fillOval(3,23,94,94);

        g.setColor(Color.PINK);
        g.fillArc(2,22,96,96, 90, -arcLen);
    }



}


