package sample;

public class CoordToDrawEdges {

    public int[][] getCoord (int line, int diagonal){
        int[][] coordinates = new int[19][3];

        int oper_X = 300/diagonal;
        int oper_Y = 450/diagonal;

        for(int i = 0; i < diagonal; i++) {
            coordinates[i][0] = 170 + i*oper_X;
            coordinates[i][1] = 560 - i*oper_Y;
            coordinates[i][2] = i+1;

        }
        for(int i = 0; i < diagonal + 1; i++) {
            int top = diagonal + (i+1);

            coordinates[diagonal + i][0] = 470 + i*oper_X;
            coordinates[diagonal + i][1] = 110 + i*oper_Y;
            coordinates[diagonal + i][2] = top;
        }
        for(int i = 1; i < line; i++) {
            int top = diagonal * 2 + i + 1;

            coordinates[diagonal * 2 + i][0] = 770 - i*(600/line);
            coordinates[diagonal * 2 + i][1] = 560;
            coordinates[diagonal * 2 + i][2] = top;
        }
        return coordinates;
    }

}
