package sample;

public class TypeOfVertexes {
    int[] isolVert = new int[19];
    int[] hangVert = new int[19];
    int count1 = 0;
    int count2 = 0;

    public int[] isoleted (int[][] degreeMatrix){
        for(int i = 0; i<degreeMatrix.length; i++){
            if(degreeMatrix[i][1] == 0 && degreeMatrix[i][2] == 0){
                isolVert[count1] = degreeMatrix[i][0];
                count1++;
            }
        }
        return isolVert;
    }

    public int[] hanging(int[][] degreeMatrix) {
        for (int i = 0; i < degreeMatrix.length; i++) {
            if ((degreeMatrix[i][1] + degreeMatrix[i][2]) == 1) {
                hangVert[count2] = degreeMatrix[i][0];
                count2++;
            }
        }
        return hangVert;
    }

}
