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
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                output1.setText(null);
                input1.setLambda(Float.valueOf(lambda1.getText()));
                input1.setMu(Float.valueOf(mu1.getText()));
                input1.setNbServer(Integer.valueOf(s1.getText()));
                input1.setT(Float.valueOf(tempsAtt1.getText()));
                input1.setJ(Integer.valueOf(nbClient1.getText()));

                calculation = new CalculateMMS(input1);
                OutPut outPut = calculation.calculate();
                String outString = String.format("M | M | %d | %d\n", input1.getNbServer(), input1.getNbMaxClient());
                outString = outString + String.format("Lq = %f, \nL = %f, \nWq = %f, \nW = %f, \n", outPut.getLq(), outPut.getL(), outPut.getWq(), outPut.getW());
                outString = outString + String.format("P(T>%f) = %f\n", input1.getT(), outPut.getPt());
                outString = outString + String.format("q(%d) = %f\n", input1.getJ(), outPut.getQj());
                output1.setText(outString);
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
