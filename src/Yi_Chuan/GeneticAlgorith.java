package Yi_Chuan;

import java.util.*;

//�Ŵ��㷨
public class GeneticAlgorith {
	private static final Chromosome Exception = null;
	long timeStart = System.currentTimeMillis();
    public List<Chromosome> population=new ArrayList<Chromosome>();//������е���Ⱥ����
    private int popSize; //��Ⱥ��ģN
    private int chromoLength;//ÿ��Ⱦɫ����Ŀm��
    private double mutationRate=0.01;//����ͻ�����pm
    private  double crossoverRate=0.6;//�������pc
    private int bagMax=1000;

    private int generation;//��Ⱥ����t
    private int iterNum=10000;//����������
   // private int stepmax=3;//�����

    private int [] charge={408,921,1329,11,998,1009,104,839,943,299,374,673,703,954,1657,425,950,1375,430,541,971,332,483,815,654,706,1360,956,992,1948};
    private int [] weight={508,1021,1321,111,1098,1196,204,939,1107,399,474,719,803,1054,1781,525,1050,1362,530,641,903,432,583,894,754,806,1241,1056,1092,1545};
    private double[]  density=new double[50];

    private Chromosome nowGenome;
    private Chromosome bestFit; //�����Ӧ�ȶ�Ӧ��Ⱦɫ��
    private Chromosome iterBestFit;//ȫ������Ⱦɫ��
    private double bestFitness;//��Ⱥ�����Ӧ��
    private double worstFitness;//��Ⱥ���Ӧ��
    private double averageFitness;


    private double x1;
    private double x2;
    private double y=0;

    public Chromosome getNowGenome() {
        return nowGenome;
    }


    public void setNowGenome(Chromosome nowGenome) {
        this.nowGenome = nowGenome;
    }

    public Chromosome getIterBestFit() {
        return iterBestFit;
    }

    public void setIterBestFit(Chromosome iterBestFit) {
        this.iterBestFit = iterBestFit;
    }

    public Chromosome getBestFit() {
        return bestFit;
    }

    public void setBestFit(Chromosome bestFit) {
        this.bestFit = bestFit;
    }

    public double getBestFitness() {
        return bestFitness;
    }

    public void setBestFitness(double bestFitness) {
        this.bestFitness = bestFitness;
    }

    public double getWorstFitness() {
        return worstFitness;
    }

    public void setWorstFitness(double worstFitness) {
        this.worstFitness = worstFitness;
    }

    public double getTotalFitness() {
        return totalFitness;
    }

    public void setTotalFitness(double totalFitness) {
        this.totalFitness = totalFitness;
    }

    private double totalFitness;//��Ⱥ����Ӧ��
    private Random random=new Random();

    public int sumCharge(){
        int sumone=0;
        for(int i=0;i<charge.length;i++){
            sumone+=charge[i];

        }
        return sumone;
    }
    public int sumWeight(){
        int sumtwo=0;
        for(int i=0;i<weight.length;i++){
            sumtwo+=weight[i];

        }
        return sumtwo;
    }


    //����Getting����

    public GeneticAlgorith(int popSize){
        this.popSize=popSize;

    }
    /*
    ��ʼ����Ⱥ
     */
    public void init(){
        for(int i=0;i<charge.length;i++){
            density[i]=charge[i]/weight[i];
        }

        for(int i=0;i<popSize;i++){
            Chromosome g=new Chromosome(50);
            changeGene(g);
            population.add(g);
            
//            changeGene(Exception e);
//            population.add(e);
        }
        caculteFitness();


    }
    /*
    ������Ⱥ��Ӧ��
     */
    public void caculteFitness(){

        bestFitness=population.get(0).getFitness();
        worstFitness=population.get(0).getFitness();
        totalFitness=0;
        for (Chromosome g:population) {
            //changeGene(g);
            setNowGenome(g);
            if(g.getFitness()>bestFitness){
                setBestFitness(g.getFitness());
                if(y<bestFitness){
                    y=g.getFitness();
                }
                setIterBestFit(g);

            }
            if(g.getFitness()<worstFitness){
                worstFitness=g.getFitness();
            }
            totalFitness+=g.getFitness();

        }
        averageFitness = totalFitness / popSize;
        //��Ϊ�������⵼�µ�ƽ��ֵ�������ֵ����ƽ��ֵ���ó����ֵ
        averageFitness = averageFitness > bestFitness ? bestFitness : averageFitness;


    }

    /*
    ���̶�ѡ���㷨
     */
    public Chromosome getChromoRoulette(){
        double db=random.nextDouble();
        double randomFitness=db*totalFitness;
        Chromosome choseOne=null;
        double sum=0.0;
        for(int i=0;i<population.size();i++){
            choseOne=population.get(i);
            sum+=choseOne.getFitness();
            if(sum>=randomFitness){
                break;
            }
        }
        return choseOne;

    }


    /*
    clone
    */
    public static Chromosome clone(Chromosome c){
        if (c==null||c.gene==null){
            return null;
        }
        Chromosome copy=new Chromosome();
        copy.initSize(50);
        for (int i=0;i<c.gene.length;i++){
            copy.gene[i]=c.gene[i];
        }
        copy.setFitness(c.getFitness());
        return copy;

    }

    /*
    ���㽻��
     */
    public static List<Chromosome> genetic(Chromosome p1,Chromosome p2){
        if(p1==null||p2==null){
            return null;
        }
        if(p1.gene==null||p2.gene==null){
            return null;
        }
        if(p1.gene.length!=p2.gene.length){
            return null;
        }
        Chromosome c1=clone(p1);
        Chromosome c2=clone(p2);
        int size=c1.gene.length;
        int a=(int)(Math.random()*size)%size;
        int b=(int)(Math.random()*size)%size;
        int min=a>b?b:a;
        int max=a>b?a:b;
        if(max-min>15){
            max=min+15;
        }//��󲽳�Ϊ10

        for(int i=min;i<max;i++){
            boolean temp=c1.gene[i];
            c1.gene[i]=c2.gene[i];
            c2.gene[i]=temp;
        }

        c1.setFitness(c1.getFitness());
        c2.setFitness(c2.getFitness());

        List<Chromosome> listNew=new ArrayList<Chromosome>();
        listNew.add(c1);
        listNew.add(c2);
        return listNew;
    }
    /*
    ̰�ı任����
     */
    public void changeGene(Chromosome c){
        int flag=0;
        int fitnessNow=0;
        int weightNow=0;
        Map<Integer,Double> map=new TreeMap<Integer, Double>();
        for(int i=0;i<c.gene.length;i++){
            if(c.gene[i]==true){
                map.put(i,density[i]);
            }
        }
        Comparator<Map.Entry<Integer, Double>> valueComparator = new Comparator<Map.Entry<Integer, Double>>() {
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        };
        List<Map.Entry<Integer,Double>> list=new ArrayList<Map.Entry<Integer, Double>>(map.entrySet());
        Collections.sort(list,valueComparator);
        for (Map.Entry<Integer,Double> entry:list) {

            if(weightNow+weight[entry.getKey()]<=bagMax){
            //if(flag==0){
                fitnessNow+=charge[entry.getKey()];
                weightNow+=weight[entry.getKey()];
            }
            else{
                //flag=1;
                c.gene[entry.getKey()]=false;
            }
            c.setFitness(fitnessNow);
            
        }

    }
    /*
    �����㷨
     */
    public void evolve() {
        List<Chromosome> childrenGenome = new ArrayList<Chromosome>();

        for (int j = 0; j < popSize / 2; j++) {
            Chromosome g1 = getChromoRoulette();
            Chromosome g2 = getChromoRoulette();
            double r = random.nextDouble();

            if (r <= crossoverRate) {
                List<Chromosome> children = genetic(g1, g2);
                if (children != null) {
                    for (Chromosome g : children) {
                        changeGene(g);
                        childrenGenome.add(g);
                        g.mutation(50, mutationRate);
                        changeGene(g);
                        childrenGenome.add(g);
                    }
                }
            }
            childrenGenome.add(g1);
            childrenGenome.add(g2);
        }

        List<Chromosome> temGen = new ArrayList<Chromosome>();
        population.clear();
        for (int i = 0; i < popSize*0.2; i++) {
            int max = 0;

            for (Chromosome tempG : childrenGenome) {
                if (tempG.getFitness() > max) {
                    max = tempG.getFitness();
                    setBestFit(tempG);
                }

            }
            temGen.add(getBestFit());
            childrenGenome.remove(getBestFit());
            setBestFit(null);
        }

        population = childrenGenome;
        caculteFitness();

        while (temGen.size() < popSize) {
            Chromosome tp1 = getChromoRoulette();
            temGen.add(tp1);
        }

        population = temGen;

        //���¼�����Ⱥ��Ӧ��
        caculteFitness();


    }
    /*
    �Ŵ��㷨GA����
     */
    public void geneticAlgorithProcess(){
        generation=1;
        init();
        while(generation<iterNum){
            evolve();
            print();
            generation++;
        }
    }

    public void print(){
        System.out.println("���� "+generation+"��");
        System.out.println("����ʵ��� "+y);
        System.out.println("-----------------------------------------------");
        if(generation==iterNum-1){
            for(int i=0;i<50;i++){
                if(iterBestFit.gene[i]==true){
                    System.out.printf("1");
                }
                else{
                    System.out.printf("0");
                }
                System.out.printf(" ");
            }
            System.out.println("");
        }
    }

}
