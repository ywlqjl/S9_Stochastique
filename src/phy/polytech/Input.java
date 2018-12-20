package phy.polytech;

import java.util.Scanner;

public class Input {

    // nb server
    private int nbServer;
    // nb k
    private int nbMaxClient;

    private float lambda;

    private float mu;

    private float rho;

    private float t;

    private float t_wait;

    private int j;

    public void getInput() {

        System.out.println("Please enter lambda, mu, s and k, separate with space:");
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        if (input != null) {
            String[] inputs = input.split(" ");
            this.lambda = Float.parseFloat(inputs[0]);
            this.mu = Float.parseFloat(inputs[1]);
            this.nbServer = Integer.parseInt(inputs[2]);
            this.nbMaxClient = Integer.parseInt(inputs[3]);
        }
    }

    public float getRho() {
//        if (this.nbMaxClient == 0 && this.lambda / (this.mu * this.nbServer)>1){
//
//            try {
//                throw new Exception("The work Can't be done, cause rho > 1");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
        return this.lambda / (this.mu * this.nbServer);
    }

    public int getNbServer() {
        return nbServer;
    }

    public void setNbServer(int nbServer) {
        this.nbServer = nbServer;
    }

    public int getNbMaxClient() {
        return nbMaxClient;
    }

    public void setNbMaxClient(int nbMaxClient) {
        this.nbMaxClient = nbMaxClient;
    }

    public float getLambda() {
        return lambda;
    }

    public void setLambda(float lambda) {
        this.lambda = lambda;
    }

    public float getMu() {
        return mu;
    }

    public void setMu(float mu) {
        this.mu = mu;
    }

    public float getT() {
        return t;
    }

    public void setT(float t) {
        this.t = t;
    }
    public float getT_wait() {
        return t_wait;
    }

    public void setT_wait(float tWait) {
        this.t_wait = tWait;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
