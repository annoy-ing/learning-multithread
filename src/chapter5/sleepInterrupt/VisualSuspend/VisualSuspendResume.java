package chapter5.sleepInterrupt.VisualSuspend;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VisualSuspendResume extends JPanel implements Runnable {

    private static final String[] symbolList = {"|", "/", "-", "\\", "|", "/", "-", "\\"};
    private Thread runThread;
    private JTextField symbolTF;

    public VisualSuspendResume() {
        symbolTF = new JTextField();
        symbolTF.setEditable(false);
        symbolTF.setFont(new Font("Monospaced", Font.BOLD, 26));
        symbolTF.setHorizontalAlignment(JTextField.CENTER);

        final JButton suspendB = new JButton("suspend");
        final JButton resumeB = new JButton("resume");

        suspendB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                suspendNow();
            }
        });

        resumeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                resumeNow();
            }
        });

        JPanel innerStackP = new JPanel();
        innerStackP.setLayout(new GridLayout(0,1,3,3));

        innerStackP.add(symbolTF);
        innerStackP.add(suspendB);
        innerStackP.add(resumeB);

        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(innerStackP);

    }

    private void suspendNow() {
        if (runThread != null) runThread.suspend();
    }

    private void resumeNow() {
        if (runThread != null) runThread.resume();
    }


    public void run() {

        try {
            runThread = Thread.currentThread();
            int count = 0;

            while (true){
                symbolTF.setText(symbolList[count % symbolList.length]);
                Thread.sleep(200);
                count++;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            runThread = null;
        }

    }


    public static void main(String[] args) {
        VisualSuspendResume vsr = new VisualSuspendResume();
        Thread t = new Thread(vsr);
        t.start();

        JFrame j = new JFrame("Visual Suspend resume");
        j.setContentPane(vsr);
        j.setSize(320,200);
        j.setVisible(true);

        j.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

    }
}
