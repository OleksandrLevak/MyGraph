package sample;

public class Weight {

    public int[][] getWeightVertex(int[][] matrix, int length){

        int size = matrix.length;

        int[][] w_vertex = new int[size*size][3];

        int n = 0;

        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                if(matrix[i][j] >= 1){
                    w_vertex[n][0] = j + 1;
                    w_vertex[n][1] = i + 1;
                    w_vertex[n][2] = matrix[i][j];
                    n++;
                }
            }
        }
        return w_vertex;

    }

    public int[][] getWeightLineCoord(int[][] w_vertex, int[][] coordinates, int n){

        int len = coordinates.length;

        int[][] coorWeightToDrawLine = new int[len*len][5];

        for(int i = 0; i < n; i++){
            //n - number of vertex
            int from = w_vertex[i][0];
            int to = w_vertex[i][1];
            int weight = w_vertex[i][2];

            if(from == 0) break;

            coorWeightToDrawLine[i][0] = coordinates[from - 1][0]; //x1
            coorWeightToDrawLine[i][1] = coordinates[from - 1][1]; //y1

            coorWeightToDrawLine[i][2] = coordinates[to - 1][0];   //x2
            coorWeightToDrawLine[i][3] = coordinates[to - 1][1];   //y2

            coorWeightToDrawLine[i][4] = weight;                   //weight
        }

        return coorWeightToDrawLine;
    }

}
