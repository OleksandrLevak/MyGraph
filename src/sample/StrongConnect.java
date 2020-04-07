package sample;

import java.util.Arrays;
import java.util.Collections;

public class StrongConnect {

    public int[][] getMatrix(int[][] matrix){

        int size = matrix.length;

        int[][] res = new int[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                res[i][j] = matrix[i][j] * matrix[j][i];
            }
        }
        return res;

    }

    public int[][] getComponet(int[][] matr){

        int size = matr.length;

        int[] elincomp = new int[size*2];
        int[] eloutcomp = new int[size];

        int[][] allres = new int[size][size];
        int[][] res = new int[size][size];

        for(int i = 0; i < size - 1; i++){
            int[] a = matr[i];
            int n = 0;
            for(int j = 0 + i; j < size; j++){
                if(Arrays.equals(a, matr[j])){
                    allres[i][n] = j + 1;
                    n++;
                }
            }
        }

        //Get components len > 1

        int k = 0;
        int count = 0;
        for(int i = 0; i < allres.length; i++){
                if(allres[i][1] != 0){
                    count++;
                    for(int j = 0; j < size; j++){
                        res[k][j] = allres[i][j];
                    }
                    k++;
            }  
        }

        // Get elements in components
        int c = 0;
        for(int i = 0; i < count; i++){
            for(int j = 0; j < size; j++){
                if(res[i][j] == 0) break;
                elincomp[c] = res[i][j];
                c++;
            }
        }

        for(int i = 1; i <= size + 1; i++){
            elincomp[c + i - 1] = i;
        }

        Arrays.sort(elincomp);

        int m = 0;
        for (int i = 1; i < size*2 - 1; i++){
                if(elincomp[i] == elincomp[i+1] || elincomp[i] == elincomp[i-1]){
                    continue;
                } else {
                    eloutcomp[m] = elincomp[i];
                    m++;
                }

        }

        for(int i = 0; i < eloutcomp.length; i++){
            if(eloutcomp[i] == 0) break;
            res[count + i][0] = eloutcomp[i];
        }

        return res;
}


    }


