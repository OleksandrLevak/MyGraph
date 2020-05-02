package sample;

public class Helpers {

    public int[][] concat2D(int[][] arr1, int[][] arr2){

        int size = arr1.length + arr2.length;

        int[][] res = new int[size][5];

        int pos = 0;
        for (int[] element : arr1)  {

            if(element[0] == 0) break;
            res[pos][0] = element[0];
            res[pos][1] = element[1];
            res[pos][2] = element[2];
            res[pos][3] = element[3];
            res[pos][4] = element[4];
            pos++;
        }

        for (int[] element : arr2) {

            if(element[0] == 0) break;
            res[pos][0] = element[0];
            res[pos][1] = element[1];
            res[pos][2] = element[2];
            res[pos][3] = element[3];
            res[pos][4] = element[4];
            pos++;
        }

        return res;

    }

    public int[][] concat2D(int[][] arr1, int[][] arr2, int[][] arr3){

        int size = arr1.length + arr2.length;

        int[][] res = new int[size][5];

        int pos = 0;
        for (int[] element : arr1)  {

            if(element[0] == 0) break;
            res[pos][0] = element[0];
            res[pos][1] = element[1];
            res[pos][2] = element[2];
            res[pos][3] = element[3];
            res[pos][4] = element[4];
            pos++;
        }

        for (int[] element : arr2) {

            if(element[0] == 0) break;
            res[pos][0] = element[0];
            res[pos][1] = element[1];
            res[pos][2] = element[2];
            res[pos][3] = element[3];
            res[pos][4] = element[4];
            pos++;
        }

        for (int[] element : arr3) {

            if(element[0] == 0) break;
            res[pos][0] = element[0];
            res[pos][1] = element[1];
            res[pos][2] = element[0];
            res[pos][3] = element[1];
            res[pos][4] = element[2];
            pos++;
        }

        return res;

    }

}
