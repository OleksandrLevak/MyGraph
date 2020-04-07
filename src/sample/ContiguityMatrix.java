package sample;

import java.util.ArrayList;
import java.util.List;

public class ContiguityMatrix {

    CreateArrayOfWays creatMatrixWays = new CreateArrayOfWays();

    public int[][] getConMatrix(int[][] matrix){

        int size = matrix.length;

        int[][] arrSum = new int[size][size];

        List<int[][]> listOfMatrix = new ArrayList<>(19);

        int [][] matrix2 = creatMatrixWays.createWayMatrixLen2(matrix, true);
        int [][] matrix3 = creatMatrixWays.createWayMatrixLen3(matrix, matrix2, true);
        int [][] matrix4 = creatMatrixWays.createWayMatrixLen3(matrix, matrix3, true);
        int [][] matrix5 = creatMatrixWays.createWayMatrixLen3(matrix, matrix4, true);
        int [][] matrix6 = creatMatrixWays.createWayMatrixLen3(matrix, matrix5, true);
        int [][] matrix7 = creatMatrixWays.createWayMatrixLen3(matrix, matrix6, true);
        int [][] matrix8 = creatMatrixWays.createWayMatrixLen3(matrix, matrix7, true);
        int [][] matrix9 = creatMatrixWays.createWayMatrixLen3(matrix, matrix8, true);
        int [][] matrix10 = creatMatrixWays.createWayMatrixLen3(matrix, matrix9, true);
        int [][] matrix11 = creatMatrixWays.createWayMatrixLen3(matrix, matrix10, true);

        listOfMatrix.add(matrix);
        listOfMatrix.add(matrix2);
        listOfMatrix.add(matrix3);
        listOfMatrix.add(matrix4);
        listOfMatrix.add(matrix5);
        listOfMatrix.add(matrix6);
        listOfMatrix.add(matrix7);
        listOfMatrix.add(matrix8);
        listOfMatrix.add(matrix9);
        listOfMatrix.add(matrix10);
        listOfMatrix.add(matrix11);



        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                for(int k = 0; k < size - 1; k++){
                    arrSum[i][j] += listOfMatrix.get(k)[i][j];
                }
            }
        }


        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                    if(i == j) arrSum[i][j] = 1;
                    if(arrSum[i][j] >= 1) arrSum[i][j] = 1;
            }
        }


        return  arrSum;



    }


}
