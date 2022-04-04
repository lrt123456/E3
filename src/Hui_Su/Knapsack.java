package Hui_Su;


//����һ����Ʒ���󣬷ֱ���ڼ�ֵ�������Լ���λ������ֵ�������ԡ�
public class Knapsack implements Comparable<Knapsack> {
  /** ��Ʒ���� */
  private int weight;
  /** ��Ʒ��ֵ */
  private int value;
  /** ��λ������ֵ */
  private int unitValue;

  public Knapsack(int weight, int value) {
      this.weight = weight;
      this.value = value;
      this.unitValue = (weight == 0) ? 0 : value / weight;
  }

  public int getWeight() {
      return weight;
  }

  public void setWeight(int weight) {
      this.weight = weight;
  }

  public int getValue() {
      return value;
  }

  public void setValue(int value) {
      this.value = value;
  }

  public int getUnitValue() {
      return unitValue;
  }

  public int compareTo(Knapsack snapsack) {
      int value = snapsack.unitValue;
      if (unitValue > value)
          return 1;
      if (unitValue < value)
          return -1;
      return 0;
  }

}

