package sample;

public class NumInEdges {


    public int[][] TextToEdges (int line, int diagonal, int x_start, int y_start, int size){

        y_start += 5;
        x_start -= 5;

        int[][] numCoord = new int[400][2];

        int oper_X = (30 * size)/diagonal;
        int oper_Y = (45 * size)/diagonal;

        for(int i = 0; i < diagonal; i++) {

            numCoord[i][0] = x_start + i*oper_X;
            numCoord[i][1] = y_start - i*oper_Y;

        }
        for(int i = 0; i < diagonal + 1; i++) {

            int top = diagonal + (i+1);
            int k = 0;
            if(top >= 10) k = 3;

            numCoord[diagonal + i][0] = x_start + oper_X*3 + i*oper_X - k;
            numCoord[diagonal + i][1] = y_start - 3*oper_Y + i*oper_Y;

        }
        for(int i = 1; i < line; i++) {

            int top = diagonal * 2 + i + 1;
            int k = 0;
            if(top >= 10) k = 3;

            numCoord[diagonal * 2 + i][0] = x_start + oper_X*6 - i*(oper_X*6/line) - k;
            numCoord[diagonal * 2 + i][1] = y_start;

        }
        return numCoord;
    }

}
