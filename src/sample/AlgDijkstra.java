package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class AlgDijkstra {

    public int[] shortWayDijkstra(int[][] graph, int[] dijkstraTable, int endEge) {

        int[] resInf = new int[1];

        resInf[0] = 1000;

        int endNodeWeight = dijkstraTable[endEge];
        if (endNodeWeight == 10000) return resInf;
        ArrayList<Integer> path = new ArrayList<>();
        int pos = endEge;
        while (endNodeWeight != 0) {
            for (int i = 0; i < graph[pos].length; i++) {
                if (endNodeWeight == (graph[pos][i] + dijkstraTable[i])) {
                    path.add(0,i + 1);
                    endNodeWeight = dijkstraTable[i];
                    pos = i;
                }
            }
        }
        if (!path.contains(endEge + 1)) path.add(endEge + 1);

       int[] res = new int[path.size()*2];

       for(int i = 0; i < path.size(); i++){
           res[i] = path.get(i);
       }

       return res;

    }

    public int[][] shortWaysDijkstra(int[][] graph, int[] dijkstraTable) {

        int size = graph.length;

        int[][] res = new int[size][size];

        for(int i = 0; i < size; i++){
            int[] getWay = shortWayDijkstra(graph, dijkstraTable, i);
            for(int j = 0; j < getWay.length; j++){
                if(getWay[j] == 0) break;
                res[i][j] = getWay[j];
            }
        }

        return shortWaysCorrectForm(res);
    }

    private int[][] shortWaysCorrectForm(int[][] shortWaysDijkstra) {

        int size = shortWaysDijkstra.length;
        int[][] oneWayInOneArr = new int[size - 1][size*2 - 2];

        for (int i = 1; i < size; i++){
            for (int j = 0; j < size; j++){
                if(shortWaysDijkstra[i][j + 1] == 0) break;

                oneWayInOneArr[i - 1][j + j] = shortWaysDijkstra[i][j];
                oneWayInOneArr[i - 1][j + j + 1] = shortWaysDijkstra[i][j + 1];

            }
        }

        int[][] shortVert = new int[size*size][3];

        int count = 0;

        for(int i = 0; i < oneWayInOneArr.length; i++){
            int flag = 1;
            for(int j = 0; j < oneWayInOneArr[i].length; j ++){

                if(oneWayInOneArr[i][j + j] == 0) break;

                shortVert[count][0] = oneWayInOneArr[i][j + j];
                shortVert[count][1] = oneWayInOneArr[i][j + j + 1];
                shortVert[count][2] = flag++;
                count++;

            }

            shortVert[count][0] = 1;
            shortVert[count][1] = 1;
            shortVert[count][2] = 0;
            count++;
        }

        return shortVert;



    }




    public int[][] vertDijkstraTable(int[][] w_vertex, int edge_start, int len) {

        ArrayList<ArrayList<Integer>> w_vert = new ArrayList<>(100);

        w_vert = getListfromArr(w_vertex);

        ArrayList<Integer> selected = new ArrayList<>();

        selected.add(0, edge_start);

        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<ArrayList<Integer>> result2 = new ArrayList<>();

        //Input elemment value to result_list

        int inf = 10000;
        for(int i = 0; i < len-1; i++){
            result.add(i, inf);
        }

        result.add(0, 0);


        int j = 0;

        int[] weigths = new int[len];
        int[][] vert_weight = new int[len][2];

        int[] coorectOrder = new int[len];


        while (j < len-1) {
            int w = 10000;

            int from = 0;
            int to = 0;
            int count = 0;


            for (int i = 0; i < w_vert.size(); i++) {
                int f = w_vert.get(i).get(0);
                int s = w_vert.get(i).get(1);
                int t = w_vert.get(i).get(2);
                if ((f == selected.get(j) || s == selected.get(j)) && t <= w) {
                    from = f;
                    to = s;

                    if(selected.contains(from) == false) {
                        weigths[count] = t;
                        vert_weight[count][0] = from;
                        vert_weight[count][1] = t;
                        count++;
                    }
                    if(selected.contains(to) == false) {
                        weigths[count] = t;
                        vert_weight[count][0] = to;
                        vert_weight[count][1] = t;
                        count++;
                    }

                    if(f == selected.get(j)){
                        if(result.get(s - 1) >= (result.get(f - 1) + t)){
                            result.set(s - 1, result.get(f - 1) + t);
                        }
                    } else {
                        if(result.get(f - 1) >= (result.get(s - 1) + t)){
                            result.set(f - 1, result.get(s - 1) + t);
                        }
                    }
                }

            }

            coorectOrder = getCorrectOrder(weigths, vert_weight);

            // add vertex to list of visited

            for (int i = 0; i < coorectOrder.length; i++) {
                if(coorectOrder[i] == 0) break;
                selected.add(coorectOrder[i]);
            }

            Arrays.fill(weigths, 0);

            ArrayList<Integer> arr = new ArrayList<>();

            for(int i = 0; i < len; i++){
                arr.add(i, result.get(i));
            }

            result2.addAll(Arrays.asList(arr));
            j++;
        }

        return getArrfromListOfWays(result2);


    }

    public int[] vertDijkstraTableFinal(int[][] vertDijkstraTable, int len) {

        int[] res = new int[len];
        int size = vertDijkstraTable.length;

        for (int i = 0; i < len; i++){
            res[i] = vertDijkstraTable[size-1][i];
        }

        return res;

    }

    private int[][] getArrfromList(ArrayList<ArrayList<Integer>> list){

        int size = list.size();

        int[][] res = new int[size*size][3];

        for(int i = 0; i < size; i++){
            if(list.get(i).get(2) == 10000) break;
            res[i][0] = list.get(i).get(0);
            res[i][1] = list.get(i).get(1);
            res[i][2] = list.get(i).get(2);
        }

        return res;

    }

    private int[][] getArrfromListOfWays(ArrayList<ArrayList<Integer>> list){

        int size = list.size();

        int[][] res = new int[size][size + 1];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size + 1; j++){
                res[i][j] = list.get(i).get(j);
            }
        }

        return res;

    }

    private ArrayList<ArrayList<Integer>> getListfromArr(int[][] w_vertex){

        ArrayList<ArrayList<Integer>> w_vert = new ArrayList<>(100);


        for (int i = 0; i < w_vertex.length; i++) {

            if (w_vertex[i][0] == 0) break;
            if (w_vertex[i][0] == w_vertex[i][1]) continue;

            int from = w_vertex[i][0];
            int to = w_vertex[i][1];
            int weight = w_vertex[i][2];
            w_vert.add(new ArrayList<Integer>(Arrays.asList(from, to, weight)));
        }

        return w_vert;

    }


    private int[] getCorrectOrder(int[] weights, int[][] vert_weight){

        int len = weights.length;

        int[] res = new int[len];

        Arrays.sort(weights);

        int count = 0;

        for(int i = 0; i < len; i++){
            if(weights[i] == 0) continue;
            int selectWeigth = weights[i];
            for (int j = 0; j < vert_weight.length; j++) {
                if(selectWeigth == vert_weight[j][1]) {
                    res[count] = vert_weight[j][0];
                    count++;
                }
            }
        }

        return res;

    }

}




