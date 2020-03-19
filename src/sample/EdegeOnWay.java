package sample;

public class EdegeOnWay {

    public int[] findCoord (int x1, int y1, int x2, int y2, int[][] masuvTochoc, int r, int i, int topNum){

        for(int j = 0; j < topNum; j++){
            int[] result = new int[2];
            int x3 = masuvTochoc[j][0];
            int y3 = masuvTochoc[j][1];

            if((x1 == x3 && y1 == y3) || (x2 == x3 && y2 == y3)) continue;

            if((((x1 - x2)*(y3 - y2)) - ((y1 - y2)*(x3 - x2))) == 0 && ((x2 < x3 && x1 > x3) || (x2 > x3 && x1 < x3))){
                result[0] = x3;
                result[1] = y3;
                return result;
            }

        }
        return  null;
    }
}
