package chapter4.runable;

import com.sun.org.apache.xml.internal.utils.ThreadControllerWrapper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SecondCounterRunnableMain extends JPanel {
    private SecondCounterRunnable sc;
    private JButton startB;
    private JButton stopB;

    public SecondCounterRunnableMain() {
        sc = new SecondCounterRunnable();
        startB = new JButton("Start");
        stopB = new JButton("Stop");

        stopB.setEnabled(false);  // begin with this disabled

        startB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // disable to stop more "start" requests
                startB.setEnabled(false);

                // Run the counter --watch out big trouble here!
                Thread counterThread = new Thread(sc, "SecondCounter");
                counterThread.start();

                stopB.setEnabled(true);
                stopB.requestFocus();
            }
        });

        stopB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stopB.setEnabled(false);
                sc.stopClock();
                startB.setEnabled(true);
                startB.requestFocus();
            }
        });

        JPanel innerButtonP = new JPanel();
        innerButtonP.setLayout(new GridLayout(0, 1, 0, 3));
        innerButtonP.add(startB);
        innerButtonP.add(stopB);

        JPanel buttonP = new JPanel();
        buttonP.setLayout(new BorderLayout());
        buttonP.add(innerButtonP, BorderLayout.NORTH);

        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.add(buttonP, BorderLayout.WEST);
        this.add(sc, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SecondCounterRunnableMain scm = new SecondCounterRunnableMain();

        JFrame f = new JFrame("Second Counter Lockup");
        f.setContentPane(scm);
        f.setSize(320, 200);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}