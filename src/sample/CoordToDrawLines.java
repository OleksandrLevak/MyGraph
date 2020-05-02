package sample;

public class CoordToDrawLines {

    public int[][] getVertex(int[][] matrix, int length){

        int size = matrix.length;

        int[][] vertex = new int[size*size][2];
        int n = 0;

        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                if(matrix[i][j] >= 1){
                    vertex[n][0] = j + 1;
                    vertex[n][1] = i + 1;
                    n++;
                }
            }
        }
        return vertex;
    }

    public int[][] getLineCoord(int[][] vertex, int[][] coordinates, int n){

        int len = coordinates.length;

        int[][] coorToDrawLine = new int[len*len][4];

        for(int i = 0; i < n; i++){                          //n - number of vertex
            int from = vertex[i][0];
            int to = vertex[i][1];

            coorToDrawLine[i][0] = coordinates[from - 1][0]; //x1
            coorToDrawLine[i][1] = coordinates[from - 1][1]; //y1

            coorToDrawLine[i][2] = coordinates[to - 1][0];   //x2
            coorToDrawLine[i][3] = coordinates[to - 1][1];   //y2

        }

        return coorToDrawLine;
    }

    public int numOfVertex(int[][] matrix, int topNum){

        int n = 0;

        for(int i = 0; i < topNum; i++){
            for(int j = 0; j < topNum; j++){
                if(matrix[i][j] >= 1){
                    n++;
                }
            }
        }
        return n;

    }

}
