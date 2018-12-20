package phy.polytech;

import java.util.Scanner;

public class CalculateMMOneK implements Calculation {

    Input input;

    CalculateMMOneK(Input input) {
        this.input = input;
    }

    @Override
    public OutPut calculate() {
        OutPut outPut = new OutPut();
        outPut.setL(this.calculateL());
        outPut.setLq(this.calculateLq());
        outPut.setW(this.calculateW());
        outPut.setWq(this.calculateWq());
        outPut.setQj(this.calculateQi(this.input.getJ()));
        return outPut;
    }

    private float calculateQi(int state) {
        float qi;
        if (input.getRho() == 1) {
            qi = 1 / (float) (input.getNbMaxClient() + 1);
        } else {
            qi = (float) ((1 - input.getRho()) * Math.pow(input.getRho(), state)
                    / (1 - Math.pow(input.getRho(), input.getNbMaxClient() + 1)));
        }
        return qi;
    }

    private float calculateL() {
        float L;
        if (input.getRho() == 1) {
            L = input.getNbMaxClient() / 2;
        } else {
            L = (float) (input.getRho() * (1 - (input.getNbMaxClient() + 1) * Math.pow(input.getRho(), input.getNbMaxClient()) + input.getNbMaxClient() * Math.pow(input.getRho(), input.getNbMaxClient() + 1))
                    / ((1 - input.getRho()) * (1 - Math.pow(input.getRho(), input.getNbMaxClient() + 1))));
        }
        return L;
    }

    private float calculateLq() {
        return this.calculateL() - (1 - this.calculateQi(0));
    }

    private float calculateW(){
        return calculateL()/input.getLambda();
    }

    private float calculateWq(){
        return calculateLq()/input.getLambda();
    }

    //int clientMaxAccepted: s'il y a plus de clients que ce nombre dans le système, les clients suivants seront refusés.
    private float calculateRefuse(int clientMaxAccepted){

        float probRefuse = 0;
        float probAccept = 0;

        for(int i = 0; i < clientMaxAccepted; i++){
            probAccept += calculateQi(i);
        }

        probRefuse = 1 - probAccept;

        return probRefuse;
    }

}
