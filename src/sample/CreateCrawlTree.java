package sample;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateCrawlTree {

    public int[][] getArrBranch (int coord[][], int topNum, int startVert){

        int[][] stack = new int[topNum][topNum];

        ArrayList <Integer> queue = new ArrayList<>();

        queue.add(startVert);

        stack[0][0] = startVert;

        int count;
        int k = 0;
        while (queue.size() < topNum){
            count = 0;

            for(int i = 0; i < coord.length; i++){
                if(queue.get(k) == coord[i][0] && queue.contains(coord[i][1]) == false){
                    queue.add(coord[i][1]);
                    stack[k + 1][count] = coord[i][1];
                    count++;
                } else if(queue.get(k) == coord[i][1] && queue.contains(coord[i][0]) == false){
                    queue.add(coord[i][0]);
                    stack[k + 1][count] = coord[i][0];
                    count++;
                }

            }
            k++;
        }
        return arrWithoutZeros(stack);
    }

    public int[][] getCoord(int[][] ArrBranch){

        int size = ArrBranch.length;

        int[][] coord = new int[size*size][2];
        int c = 0;

        for(int i = 0; i < size; i++){
            if(ArrBranch[i][0] == 0) break;
            for(int j = 0; j < size; j++){
                if(ArrBranch[i][j] == 0) break;
                boolean flag = true;
                for(int k = i + j + 1; k < size - 1; k++){
                    if(ArrBranch[k][0] == 0 || flag == false) break;
                    for(int l = 0; l < size; l++){
                        if(ArrBranch[k][l] == 0) {
                            flag = false;
                            break;
                        }
                        coord[c][0] = ArrBranch[i][j];
                        coord[c][1] = ArrBranch[k][l];
                        c++;
                    }

                }
            }
        }
        return coord;


    }
    public int[][] getMatrix(int[][] coord, int topNum){

        int[][] res = new int[topNum][topNum];

        for(int i = 0; i < topNum; i++){
            for(int j = 0; j < topNum; j++){
                res[i][j] = 0;
            }
        }


        for(int i = 0; i < coord.length; i++){
            if(coord[i][0] == 0) break;
            res[coord[i][0] - 1][coord[i][1] - 1] = 1;
        }
        return res;

    }



    public int[][] arrWithoutZeros(int[][] arr){

    int bigNum = 100;
    int size = arr.length;

    for(int i = 0; i < size; i++){
        for(int j = 0; j < size; j++){
            if(arr[i][j] == 0) arr[i][j] = bigNum;
        }

        Arrays.sort(arr[i]);
    }

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(arr[i][j] == bigNum) arr[i][j] = 0;
            }

        }

    return arr;

}
}