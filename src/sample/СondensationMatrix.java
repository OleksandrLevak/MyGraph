package sample;

import java.util.Arrays;

public class СondensationMatrix {

    public int copmlexCopmonents(int[][] matrix){

        int count = 0;
        for(int i = 0; i < matrix.length; i++){
            if(matrix[i][1] != 0){
                count++;
            }
        }
        return count;
    }

    public int[][] getCondentMat(int[][] matrix, int[][] matrixCoord, int[][] components){

        int substract = copmlexCopmonents(components);

        int size = matrix.length - substract;

        int compLen = components.length;
        int matrLen = matrixCoord.length;

        int[][] res = new int[size][size];

        boolean f, f2;

        for(int i = 0; i < compLen; i++){
            for(int j = 0; j < components[i].length; j++){
                int compel = components[i][j];
                if(compel == 0) break;
                for(int x = 0; x < matrLen; x++){
                    if(compel == matrixCoord[x][0]){
                            f = false;
                        for(int m = 0; m < components[i].length; m++){
                            if(components[i][m] == 0) break;
                            if(matrixCoord[x][1] == components[i][m]){
                                f = true;
                            }
                            if(f == false){
                                for(int v = 0; v < compLen; v++){
                                    if(Arrays.equals(components[v], components[i])) continue;
                                    for(int w = 0; w < components[v].length; w++){
                                        if(components[v][w] == 0) break;
                                        if(components[v][w] == matrixCoord[x][1]){
                                            res[i][v] = 1;
                                        }
                                    }
                                }
                            } else {
                                res[i][matrixCoord[x][1] - 1] = 0;
                            }
                        }
                    } else if(compel == matrixCoord[x][1]){
                            f2 = false;
                        for(int n = 0; n < components[i].length; n++){
                            if(components[i][n] == 0) break;
                            if(matrixCoord[x][0] == components[i][n]){
                                f2 = true;
                            }
                            if(f2 == false){
                                for(int v = 0; v < compLen; v++){
                                    if(Arrays.equals(components[v], components[i])) continue;
                                    for(int w = 0; w < components[v].length; w++){
                                        if(components[v][w] == 0) break;
                                        if(components[v][w] == matrixCoord[x][0]){
                                            res[i][v] = 1;
                                        }
                                    }
                                }
                            } else {
                                res[i][matrixCoord[x][0] - 1] = 0;
                            }
                        }
                    }
                }
            }
        }

        return res;

    }

}
