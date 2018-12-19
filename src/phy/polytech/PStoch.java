package phy.polytech;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PStoch {
    private JTabbedPane tabbedPane1;
    private JPanel contentPane;
    private JTextField lambda2;
    private JTextField mu2;
    private JTextField a1TextField;
    private JTextField k;
    private JTextArea output2;
    private JTextField lambda1;
    private JTextField mu1;
    private JTextField s1;
    private JButton calculer1;
    private JTextArea output1;
    private JButton calculer2;
    private JTextField tempsAtt1;
    private JTextField nbClient1;
    private JTextField tempsAtt2;
    private JTextField nbClient2;
    private Calculation calculation;
    private Input input1; // MMS
    private Input input2;

    // 输入等待时间超过t（求超过t的概率）， 人数i（系统内i个人的概率），

    public PStoch() {
        input1 = new Input();
        input2 = new Input();
        calculer1.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                input1.setLambda(Float.valueOf(lambda1.getText()));
                input1.setMu(Float.valueOf(mu1.getText()));
                input1.setNbServer(Integer.valueOf(s1.getText()));
                calculation = new CalculateMMS(input1);
                calculation.calculate();
            }
        });
        calculer2.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                input2.setLambda(Float.valueOf(lambda2.getText()));
                input2.setMu(Float.valueOf(mu2.getText()));
                input2.setNbServer(1);
                input2.setNbMaxClient(Integer.valueOf(k.getText()));
                calculation = new CalculateMMOneK(input2);
                calculation.calculate();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PStoch");
        frame.setContentPane(new PStoch().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
