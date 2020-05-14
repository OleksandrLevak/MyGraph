package sample;

import java.util.ArrayList;
import java.util.Arrays;

public class AlgPrima {

    public int[][] vertPrima(int[][] w_vertex, int edge_start, int len) {

        ArrayList<ArrayList<Integer>> w_vert = new ArrayList<>(100);


        for (int i = 0; i < w_vertex.length; i++) {

            if (w_vertex[i][0] == 0) break;
            if (w_vertex[i][0] == w_vertex[i][1]) continue;

            int from = w_vertex[i][0];
            int to = w_vertex[i][1];
            int weight = w_vertex[i][2];
            w_vert.add(new ArrayList<Integer>(Arrays.asList(from, to, weight)));
        }

        ArrayList<ArrayList<Integer>> w_vert_result = new ArrayList<>(50);

        ArrayList<Integer> visited = new ArrayList<>(50);
        visited.add(edge_start);
        int size = visited.size();

        while (size < len) {
            int w = 10000;

            int from = 0;
            int to = 0;

            for (int i = 0; i < w_vert.size(); i++) {
                int f = w_vert.get(i).get(0);
                int s = w_vert.get(i).get(1);
                int t = w_vert.get(i).get(2);
                for (int j = 0; j < visited.size(); j++) {
                    if ((visited.contains(f) == false || visited.contains(s) == false) && (f == visited.get(j) || s == visited.get(j)) && t <= w) {
                        from = f;
                        to = s;
                        w = t;
                    }
                }
            }

            w_vert_result.add(new ArrayList<Integer>(Arrays.asList(from, to, w)));
            w_vert.remove(Arrays.asList(from, to, w));
            if(visited.contains(from) == false) visited.add(from);
            if(visited.contains(to) == false) visited.add(to);
            size = visited.size();
        }

        int[][] res = getArrfromList(w_vert_result);

        return res;

    }

    private int[][] getArrfromList(ArrayList<ArrayList<Integer>> list){

        int size = list.size();

        int[][] res = new int[size][3];

        for(int i = 0; i < size; i++){
            res[i][0] = list.get(i).get(0);
            res[i][1] = list.get(i).get(1);
            res[i][2] = list.get(i).get(2);
        }

        return res;

    }

    public int[][] primaMatrix(int[][] vert, int size){

        int[][] res = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                res[i][j] = 0;
            }
        }

        for(int k = 0; k < vert.length; k++){
            int i = vert[k][0] - 1;
            int j = vert[k][1] - 1;
            res[i][j] = 1;
        }

        return res;

    }
}



