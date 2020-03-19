package sample;

public class NumInEdges {

    public int[][] TextToEdges (int line, int diagonal){

        int[][] numCoord = new int[400][2];

        int oper_X = 300/diagonal;
        int oper_Y = 450/diagonal;

        for(int i = 0; i < diagonal; i++) {

            numCoord[i][0] = 165 + i*oper_X;
            numCoord[i][1] = 565 - i*oper_Y;

        }
        for(int i = 0; i < diagonal + 1; i++) {

            int top = diagonal + (i+1);
            int k = 0;
            if(top >= 10) k = 3;

            numCoord[diagonal + i][0] = 465 + i*oper_X - k;
            numCoord[diagonal + i][1] = 115 + i*oper_Y;

        }
        for(int i = 1; i < line; i++) {

            int top = diagonal * 2 + i + 1;
            int k = 0;
            if(top >= 10) k = 3;

            numCoord[diagonal * 2 + i][0] = (765 - k) - i*(600/line);
            numCoord[diagonal * 2 + i][1] = 565;

        }
        return numCoord;
    }

}
