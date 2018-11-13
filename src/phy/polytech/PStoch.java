package phy.polytech;

import javax.swing.*;

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

    public static void main(String[] args) {
        JFrame frame = new JFrame("PStoch");
        frame.setContentPane(new PStoch().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
