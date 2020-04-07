package sample;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class CreateArrayOfWays {

    public int[][] diagonalNull(int[][] matrix){

        int size = matrix.length;

        int[][] matrdiagnull = new int[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(i == j) continue;
                matrdiagnull[i][j] = matrix[i][j];
            }
        }
        return matrdiagnull;
    }

    public int[][] createWayMatrixLen2 (int[][] matrix, boolean contiguity){

        int size = matrix.length;
        int calculate;

        int[][] result = new int[size][size];

        int[][] matrdiagnull = matrix;

        if (contiguity == false){
            matrdiagnull = diagonalNull(matrix);
        }

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                calculate = 0;
                for(int k = 0; k < size; k++){
                    calculate += matrdiagnull[i][k] * matrdiagnull[k][j];
                }

                result[i][j] = calculate;
            }
        }


        return result;
    }

    public int[][] createWayMatrixLen3 (int[][] matrix, int[][] matrix2, boolean contiguity){

        int size = matrix.length;
        int calculate;

        int[][] result = new int[size][size];

        int[][] matrdiagnull = matrix;
        int[][] matrdiagnull2 = matrix2;

        if (contiguity == false){
            matrdiagnull = diagonalNull(matrix);
            matrdiagnull2 = diagonalNull(matrix2);
        }

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                calculate = 0;
                for(int k = 0; k < size; k++){
                    calculate += matrdiagnull[i][k] * matrdiagnull2[k][j];
                }

                result[i][j] = calculate;
            }
        }

        return result;
    }



    public int[][] createCoordFromMatr(int[][] matrix){

        int size = matrix.length;

        int[][] matr = new int[size*size][2];

        int k = 0;

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(matrix[i][j] == 1){
                    matr[k][0] = i + 1;
                    matr[k][1] = j + 1;
                    k += 1;
                }

            }
        }

        return matr;

    }

    public int[][] createCoordInputMatrix(int[][] matrix){

        int size = matrix.length;

        int[][] matr = new int[size*size][2];

        int k = 0;

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(i == j) continue;
                if(matrix[i][j] == 1){
                    matr[k][0] = i + 1;
                    matr[k][1] = j + 1;
                    k += 1;
                }

            }
        }

        return matr;

    }

    public int[][] getWaysLen2(int[][] first, int[][] second){

        int size = second.length;
        int start, end;

        int[][] startendArr = new int[size][size];

        int[] res = new int[size];

        int[][] ways = new int[size][3];

        for(int i = 0; i < size; i++){
            int k = 0;
            start = second[i][0];
            end = second[i][1];
            if(start == 0 && end == 0) break;
            for(int j = 0; j < size; j++){
                if(first[j][0] == 0 && first[j][1] == 0) break;
                if(start == first[j][0]){
                    startendArr[i][k] = first[j][1];
                    k++;
                }
                if(end == first[j][1]){
                    startendArr[i][k] = first[j][0];
                    k++;
                }
            }
        }

        for (int i = 0; i < size; i++){
            Arrays.sort(startendArr[i]);
        }
        int c = 0;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size - 1; j++){
                if(startendArr[i][j] == startendArr[i][j + 1] && startendArr[i][j] != 0){
                    res[c] = startendArr[i][j];
                    c++;
                    break;
                }
            }

        }

        for(int i = 0; i < size; i++){
                    ways[i][0] = second[i][0];
                    ways[i][1] = res[i];
                    ways[i][2] = second[i][1];

            }

        return ways;
        }

    public int[][] getWaysLen3(int[][] first, int[][] second, int[][] third, int[][] waysLen2){

        int size = second.length;
        int start, end;

        int[][] ways = new int[size][4];
        int n = 0;

        for(int i = 0; i < size; i++){
            start = third[i][0];
            end = third[i][1];
            if(start == 0 && end == 0) break;
            for(int j = 0; j < size; j++){
                if(waysLen2[j][0] == 0) break;
                if(start == waysLen2[j][0]){
                    for (int k = 0; k < size; k++){
                        if(first[k][1] == 0) break;
                        if(end == first[k][1] && waysLen2[j][2] == first[k][0]){
                            ways[n][0] = start;
                            ways[n][1] = waysLen2[j][1];
                            ways[n][2] = waysLen2[j][2];
                            ways[n][3] = end;
                            n++;
                        }
                    }
                }

            }
        }

        return ways;


    }


    }


