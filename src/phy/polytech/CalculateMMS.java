package phy.polytech;

import org.omg.CORBA.MARSHAL;

public class CalculateMMS implements Calculation {

    Input input;

    public CalculateMMS(Input input) {
        this.input = input;
    }

    @Override
    public float calculate() {
        float q0 = this.calculateQ0();

        System.out.format("M | M | %d | %d, q%d = %f \n", input.getNbServer(), input.getNbMaxClient(), 0, q0);
        return 0;
    }


    private float sum() {
        float result = 0;
        for (int j = 0; j <= input.getNbServer() - 1; j++) {
            result += Math.pow(input.getRho() * input.getNbServer(), j) / this.factorial(j);
        }
        return result;
    }

    private long factorial(int n) {
        long result = 1;
        long temp[] = new long[100];
        temp[0] = 1;
        for (int i = 1; i <= n; i++) {
            result = temp[i - 1] * i;
            temp[i] = result;
        }
        return result;
    }

    private float calculateQ0() {
        float q0 = (float) (1 /
                (this.sum() + Math.pow(input.getRho() * input.getNbServer(), input.getNbServer()) / (this.factorial(input.getNbServer()) * (1 - input.getRho()))));
        return q0;
    }

    private float calculateQj(int j) {
        float qj;
        if (j >= 0 && j < input.getNbServer()) {
            qj = (float) (Math.pow(input.getRho() * input.getNbServer(), j) * this.calculateQ0() / this.factorial(j));
        } else {
            qj = (float) (Math.pow(input.getNbServer(), input.getNbServer()) * Math.pow(input.getRho(), j) * this.calculateQ0() / this.factorial(input.getNbServer()));
        }
        return qj;
    }

}
