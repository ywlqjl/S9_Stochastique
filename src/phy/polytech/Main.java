package phy.polytech;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // write your code here
        Input input = new Input();
        input.getInput();
        Calculation calculation;
        if(input.getNbMaxClient()!=-1){
            if(input.getNbServer()==1)
                calculation = new CalculateMMOneK(input);
            else{
                throw new Exception("Error input");
            }
        }else{
            calculation = new CalculateMMS(input);
        }
        calculation.calculate();
    }
}
