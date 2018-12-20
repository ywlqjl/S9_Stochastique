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
    private JTextField nbClient2;
    private Calculation calculation;
    private Input input1; // MMS
    private Input input2;

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


                input1.setT(Float.valueOf(tempsAtt1.getText().isEmpty() ? "0" : tempsAtt1.getText()));
                input1.setJ(Integer.valueOf(nbClient1.getText().isEmpty() ? "0" : nbClient1.getText()));

                calculation = new CalculateMMS(input1);

                if (input1.getRho() > 1) {
                    output1.setText("Erreur! Il faut que lambda/(s*mu) soit inférieur à 1.");
                    return ;
                }
                OutPut outPut = calculation.calculate();
                String outString = String.format("Modèle: M | M | %d \n", input1.getNbServer());
                outString = outString + String.format("Nb moyen de clients dans la file: Lq = %f, \n" +
                        "Nb moyen de clients dans le système: L = %f, \n" +
                        "La durée moyenne d'attente dans la file: Wq = %f, \n" +
                        "La durée moyenne d'attente dans le système: W = %f, \n", outPut.getLq(), outPut.getL(), outPut.getWq(), outPut.getW());
                outString = outString + String.format("La probabilité que la durée de séjour > %f: P(T>%f) = %f\n", input1.getT(), input1.getT(), outPut.getPt());
                outString = outString + String.format("La probabilité de %d clients dans le système: q(%d) = %f\n", input1.getJ(), input1.getJ(), outPut.getQj());
                output1.setText(outString);
            }
        });
        calculer2.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                output2.setText(null);
                input2.setLambda(Float.valueOf(lambda2.getText()));
                input2.setMu(Float.valueOf(mu2.getText()));
                input2.setNbServer(1);
                input2.setNbMaxClient(Integer.valueOf(k.getText()));

                input2.setJ(Integer.valueOf(nbClient2.getText().isEmpty() ? "0" : nbClient2.getText()));

                calculation = new CalculateMMOneK(input2);
                OutPut outPut = calculation.calculate();
                String outString = String.format("Modèle: M | M | %d | %d\n", input2.getNbServer(), input2.getNbMaxClient());
                outString = outString + String.format("Nb moyen de clients dans la file: Lq = %f, \n" +
                        "Nb moyen de clients dans le système: L = %f, \n" +
                        "La durée moyenne d'attente dans la file: Wq = %f, \n" +
                        "La durée moyenne d'attente dans le système: W = %f, \n", outPut.getLq(), outPut.getL(), outPut.getWq(), outPut.getW());
                outString = outString + String.format("La probabilité de %d clients dans le système: q(%d) = %f\n", input2.getJ(), input2.getJ(), outPut.getQj());
                output2.setText(outString);
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
