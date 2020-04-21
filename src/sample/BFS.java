package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BFS {
    public int[] getArrOfVert(int[][] branches) {

        int[] res = new int[19];
        int n = 0;
        for (int i = 0; i < branches.length; i++) {
            for (int j = 0; j < branches.length; j++) {
                if (branches[i][j] == 0) break;
                res[n] = branches[i][j];
                n++;
            }
        }
        return res;
    }

    public int[][] getCoordDrawBFS(int[][] branches, int[][] coordOfEdges) {

        int size = branches.length;

        int[][] res = new int[size][size*2];


        for (int i = 0; i < branches.length; i++) {
            if(branches[i][0] == 0) continue;
            int count2 = 0;
            for (int j = 0; j < branches.length; j++) {
                int check = branches[i][j];
                if(check == 0) break;
                for(int l = 0; l < coordOfEdges.length; l++){
                    if(check == coordOfEdges[l][2]){
                        res[i][count2] = coordOfEdges[l][0];
                        res[i][count2 + 1] = coordOfEdges[l][1];
                        count2 += 2;
                    }
                }

            }
        }
        return res;
    }

    public int[] getCoordDrawBFSinLine(int[][] branches, int[][] coordOfEdges) {

        int size = branches.length;

        int[] res = new int[size*size];

        int count2 = 0;
        for (int i = 0; i < branches.length; i++) {
            if(branches[i][0] == 0) continue;
            for (int j = 0; j < branches.length; j++) {
                int check = branches[i][j];
                if(check == 0) break;
                for(int l = 0; l < coordOfEdges.length; l++){
                    if(check == coordOfEdges[l][2]){
                        res[count2] = coordOfEdges[l][0];
                        res[count2 + 1] = coordOfEdges[l][1];
                        count2 += 2;
                    }
                }

            }
        }
        return res;
    }
}