package sample;

public class IndentionFormula {

    public int[] formula (int x1, int y1, int x2, int y2, int x3, int y3, int ots){
        int[] result = new int[2];

        int n1 = y2 - y1;
        int n2 = x1 - x2;

        result[0] = n1/ots + x3;
        result[1] = n2/ots + y3;
        return result;
    }

}
