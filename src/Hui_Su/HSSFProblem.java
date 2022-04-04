package Hui_Su;

import java.util.Arrays;
import java.util.Collections;

//���ݷ�
public class HSSFProblem {
    // ��ѡ�����Ʒ
    private Knapsack[] bags;
    // �������ܳ���
    private int totalWeight;
    // �����ĵ�ǰ����
    private int currWeight;
    // ��ѡ����Ʒ����
    private int n;
    // ������Ʒ�󱳰������ż�ֵ
    private int bestValue;
    // ������Ʒ�ͱ����ĵ�ǰ��ֵ
    private int currValue;

    public HSSFProblem(Knapsack[] bags, int totalWeight) {
        this.bags = bags;
        this.totalWeight = totalWeight;
        this.n = bags.length;

        // ��Ʒ���ݵ�λ������ֵ�Ӵ�С��������
        Arrays.sort(bags, Collections.reverseOrder());
    }

    public int solve(int i) {
        // ��û����Ʒ���Է��뱳��ʱ����ǰ��ֵΪ���ż�ֵ
        if (i >= n) {
            bestValue = currValue;
            return bestValue;
        }

        // ��Ҫ���������뵱ǰ��Ʒ���ж���Ʒ���뱳�����Ƿ�С�ڱ������ܳ���
        if (currWeight + bags[i].getWeight() <= totalWeight) {
            // ����Ʒ���뱳���е�״̬
            currWeight += bags[i].getWeight();
            currValue += bags[i].getValue();

            // ѡ����һ����Ʒ�����ж�
            bestValue = solve(i + 1);

            // ����Ʒ�ӱ�����ȡ����״̬
            currWeight -= bags[i].getWeight();
            currValue -= bags[i].getValue();
        }

        // ��Ҫ�����������뵱ǰ��Ʒ��������һ����Ʒ���ܻ�������ŵļ�ֵ�������һ����Ʒ�����ж�
        // ��ǰ��ֵ+ʣ���ֵ<=���ż�ֵ�����迼��������������������ż�ֵ�Ľ������С������㷵�أ�
        // Ϊ�˷�ֹ����Ľ���λ������ֵ�����Ʒ������޳�����Ҫ����Ʒ���յ�λ������ֵ�Ӵ�С��������
        if (currValue + getSurplusValue(i + 1) > bestValue) {
            // ѡ����һ����Ʒ�����ж�
            bestValue = solve(i + 1);
        }
        return bestValue;
    }

    // �����Ʒ��ʣ���ܼ�ֵ
    public int getSurplusValue(int i) {
        int surplusValue = 0;
        for (int j = i; j < n; j++)
            surplusValue += bags[i].getValue();
        return surplusValue;
    }

}