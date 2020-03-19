package sample;

public class CheckDirect {
    public int[][] NonDirect(int[][] mat) {
        int ones = 0;
        int pair = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (i == j) continue;
                if (mat[i][j] == 1) ones++;
                if (mat[i][j] == 1 && mat[j][i] == 1) {
                    pair++;
                }
            }
        }
        if (pair == ones){
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length; j++) {
                    if (i == j) continue;
                    if (mat[i][j] == 1 && mat[j][i] == 1) {
                        mat[j][i] = 0;
                    }
                }
            }
        }

        return mat;
    }
    public boolean isDirect(int[][] mat) {
        int ones = 0;
        int pair = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (i == j) continue;
                if (mat[i][j] == 1) ones++;
                if (mat[i][j] == 1 && mat[j][i] == 1) {
                    pair++;
                }
            }
        }
        if (pair == ones){
            return false;
        }

        return true;
    }
}
