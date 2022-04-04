package Yi_Chuan;

import java.util.Random;

//Ⱦɫ����
public class Chromosome {
    public boolean[] gene;
    private int fitness;
    private int bag=1000;

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
    /*
    ����Ⱦɫ��
     */
    public Chromosome(int n){
        if (n<0){
            return;
        }
        initSize(n);
        for(int i=0;i<n;i++){
            gene[i]=Math.random()>=0.5;
        }
        getFitness();
    }
    
    public Chromosome(){
    }

    public void initSize(int n){
        if (n<0){
            return;
        }
        this.gene=new boolean[n];
    }

    /*
    Ⱦɫ�����
     */

    public void mutation(int size,double rate){
        Random random=new Random();
        for(int i=0;i<size;i++){
            if(random.nextDouble()<rate){
                boolean t=gene[i];
                t=!t;
                gene[i]=t;
            }
        }
    }

}

