package Yi_Chuan;

import java.util.Random;

//染色体类
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
    构造染色体
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
    染色体变异
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

