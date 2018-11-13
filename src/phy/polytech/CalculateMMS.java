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

        System.out.format("M | M | %d | %d, \nq%d = %f \n", input.getNbServer(), input.getNbMaxClient(), 0, q0);
        System.out.format("Lq = %f, \nL = %f, \nWq = %f, \nW = %f, \n", this.calculateLq(), this.calculateL(), this.calculateWq(), this.calculateW());
        System.out.format("P(T>%f) = %f", (float)1/6, this.calculatePSystem( (float)1/6));
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

    private float calculateLq(){
        float lq;
        lq = (float) (this.calculateQ0() * Math.pow(input.getNbServer(), input.getNbServer()) * Math.pow(input.getRho(), input.getNbServer()+1)/
                (this.factorial(input.getNbServer()) * Math.pow((1 - input.getRho()), 2)));
        //lq = (float) (this.calculateQ0() * Math.pow(input.getRho() * input.getNbServer(), input.getNbServer()) * input.getRho() /
        //        ( this.factorial(input.getNbServer()) * Math.pow((1 - input.getRho()), 2)));
        return lq;
    }

    private float calculateWq(){
        float wq;
        wq = this.calculateLq() / input.getLambda();
        return wq;
    }

    private float calculateW(){
        float w;
        w = this.calculateWq() + 1 / input.getMu();
        return w;
    }

    private float calculateL(){
        float l;
        l = this.calculateW() * input.getLambda();
        return l;
    }
    /**
     * Calculater la duree du sejour dans le systeme.
     * @return
     */
    private float calculatePSystem(float t){
        float ps;
        float temp;
        temp = (float)( 1 - Math.pow(Math.E, (-1)* (input.getMu() * t) * (input.getNbServer() - 1 - input.getRho() * input.getNbServer())) /
                (input.getNbServer() - 1 - input.getRho() * input.getNbServer()));
        ps = (float)( Math.pow(Math.E, (-1)* (input.getMu() * t)) * (1 + (this.calculateQ0() * Math.pow (input.getRho() * input.getRho(),input.getNbServer())) * temp /
                (this.factorial(input.getNbServer()) * (1 - input.getRho()))));
        return ps;
    }

    private float calculatePWait(float t){
        float p0;
        float pw;
        p0 = (float)(this.calculateQ0() * Math.pow(input.getRho() * input.getNbServer(), input.getNbServer()) /
                (this.factorial(input.getNbServer()) * (1 - input.getRho())));
        if(t > 0){
            pw = (float) (Math.pow(Math.E, (-1) * input.getNbServer() * input.getMu() * t * ( 1 - input.getRho())) * p0);
            return pw;
        }
        else{
            return p0;
        }

    }
}
