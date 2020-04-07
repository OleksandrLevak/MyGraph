package sample;

public class CoordToDrawEdges {


    public int[][] getCoord (int line, int diagonal, int x_start, int y_start, int size){


        int[][] coordinates = new int[19][3];

        int oper_X = (30 * size)/diagonal;
        int oper_Y = (45 * size)/diagonal;



        for(int i = 0; i < diagonal; i++) {
            coordinates[i][0] = x_start + i*oper_X;
            coordinates[i][1] = y_start - i*oper_Y;
            coordinates[i][2] = i+1;

        }
        for(int i = 0; i < diagonal + 1; i++) {
            int top = diagonal + (i+1);
            coordinates[diagonal + i][0] = x_start + oper_X*3 + i*oper_X;
            coordinates[diagonal + i][1] = y_start - 3*oper_Y + i*oper_Y;
            coordinates[diagonal + i][2] = top;
        }
        for(int i = 1; i < line; i++) {
            int top = diagonal * 2 + i + 1;

            coordinates[diagonal * 2 + i][0] = x_start + oper_X*6 - i*(oper_X*6/line);
            coordinates[diagonal * 2 + i][1] = y_start;
            coordinates[diagonal * 2 + i][2] = top;
        }
        return coordinates;
    }

}
