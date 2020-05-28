package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;


public class GraphController_four {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public Pane pane;

    @FXML
    private Button draw;

    @FXML
    private TextField num;

    @FXML
    private Button inputMatrix;

    @FXML
    private Button drawVertex;

    @FXML
    private Button weigth;

    @FXML
    private Button dijkstraBtn;

    @FXML
    private Button dijkstraGraph;

    @FXML
    private Pane grid;


    int topNum = 0;
    int n = 0;
    int numOfVertex2 = 0;
    int startVert = 1;

    int[][] coordinates = new int[19][3];
    int[][] coordinates2 = new int[19][3];
    int[][] coorToDrawLine = new int[400][4];
    int[][] coorToDrawWeightLine = new int[400][5];


    int[][] coordArrow  = new int[100][5];
    int[][] coordArrow2 = new int[100][5];
    int[][] numCoord = new int[400][2];
    int[][] numCoord2 = new int[400][2];


    int radius = 12;
    int r = radius/2 + 3;
    int line = 0;
    int diagonal = 0;
    int [] line_diagonal = new int[2];
    int[][] coordWeightDrawLoopArrow = new int[19][3];
    int[][] matrix = new int[11][11];
    int[][] matrixW = new int[11][11];
    int[][] checkMatrix = new int[19][19];


    int[][] w_vertex = new int[190][3];
    int[][] dijkstra_vertex = new int[400][3];
    int[][] dijkstraTable = new int[200][19];



    // Classes
    PositionOfEdges l_d = new PositionOfEdges();
    CoordToDrawEdges coord = new CoordToDrawEdges();
    CoordToDrawLines coord_ver_line = new CoordToDrawLines();
    IndentionFormula indention = new IndentionFormula();
    EdegeOnWay edgeCoord = new EdegeOnWay();
    NumInEdges inputText = new NumInEdges();
    CheckDirect check = new CheckDirect();
    Helpers helpfun = new Helpers();
    Weight w_fun = new Weight();
    AlgDijkstra dijkstra = new AlgDijkstra();


    //Graph settings

    int x_start = 60;
    int y_start = 610;
    int size = 12;

    @FXML
    void buttonClicked(ActionEvent event) {

        topNum = Integer.parseInt(num.getText());

        line_diagonal = l_d.getEdgePos(topNum);

        line = line_diagonal[0];
        diagonal = line_diagonal[1];


        coordinates = coord.getCoord(line, diagonal, x_start, y_start, size);

        for(int i = 0; i<=coordinates.length; i++){
            if(coordinates[i][0] == 0 &&  coordinates[i][1] == 0) break;
            Circle edge = new Circle(coordinates[i][0], coordinates[i][1], radius);
            edge.setFill(Color.WHITE);
            edge.setStroke(Color.BLACK);
            pane.getChildren().add(edge);
        }

        numCoord = inputText.TextToEdges(line, diagonal, x_start, y_start, size);

        for(int i = 0; i <= numCoord.length; i++){
            if(numCoord[i][0] == 0 && numCoord[i][1] == 0) break;
            String snum = Integer.toString(i+1);
            Text num = new Text(numCoord[i][0], numCoord[i][1], snum);
            pane.getChildren().add(num);
        }


        matr.setPrefWidth(300);
        matr.setPrefHeight(360);
        matr.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        matr.setVisible(true);

        grid.getChildren().add(matr);
    }

    TextArea matr = new TextArea();



    @FXML
    void draw(ActionEvent event) {

        // Create matrix from string

        String inputMatrix = new String();
        inputMatrix = matr.getText();

        String[] matrText = new String[topNum];
        matrText = inputMatrix.split("\\n");

        String m = new String();
        m = String.join(" ", matrText);

        String[] matrText2 = new String[topNum];
        matrText2 = m.split(" ");

        // Create an array

        int[] nums = new int[matrText2.length];

        for(int i = 0; i < matrText2.length; i++){
            nums[i] = Integer.parseInt(matrText2[i]);
        }

        // Create a 2d matrix from an array


        int count = 0;
        for(int i = 0; i < topNum; i++){
            for(int j = 0; j < topNum; j++){
                matrix[i][j] = nums[count];
                matrixW[i][j] = nums[count];
                count++;
            }
        }

        checkMatrix = check.NonDirect(matrix);

        // Create an array of vertices numbers, which are connected

        int[][] vertex = coord_ver_line.getVertex(matrix, topNum);
        int[][] w_vertex = w_fun.getWeightVertex(matrix, topNum);

        n = coord_ver_line.numOfVertex(matrix, topNum);

        // Create an array of coordinates to draw vertices

        coorToDrawLine = coord_ver_line.getLineCoord(vertex, coordinates, n);
        coorToDrawWeightLine = w_fun.getWeightLineCoord(w_vertex, coordinates, n);


        dijkstraTable = dijkstra.vertDijkstraTable(w_vertex, startVert, topNum);
        int[] sel = dijkstra.getCorrOrderOfVert(w_vertex, startVert, topNum);
        int[] dijkstraTableFinal = dijkstra.vertDijkstraTableFinal(dijkstraTable, topNum);
        int[] shortWayDijkstra = dijkstra.shortWayDijkstra(matrixW, dijkstraTableFinal, 1);
        dijkstra_vertex = dijkstra.shortWaysDijkstra(matrixW, dijkstraTableFinal, sel);




        System.out.println("Input matrix: " + Arrays.toString(nums));
        System.out.println("Matrix to draw: " + Arrays.deepToString(matrix));
        System.out.println("MatrixW to draw: " + Arrays.deepToString(matrixW));
        System.out.println("Coordinates of edges: " + Arrays.deepToString(coordinates));
        System.out.println("Coordinates of vertex: " + Arrays.deepToString(vertex));
        System.out.println("Coordinates of weight_vertex: " + Arrays.deepToString(w_vertex));
        System.out.println("Coordinates of lines: " + Arrays.deepToString(coorToDrawLine));
        System.out.println("Weight coordinates of lines: " + Arrays.deepToString(coorToDrawWeightLine));
        System.out.println("Number of vertex: " + n);

        System.out.println("-----------------------");
        //System.out.println("Coordinates of Dijkstra_vertex: " + Arrays.toString(dijkstra_vertex));

        System.out.println("DijkstraTable: " + Arrays.deepToString(dijkstraTable));
        System.out.println("DijkstraTable final: " + Arrays.toString(dijkstraTableFinal));
        System.out.println("ShortWayDijkstra: " + Arrays.toString(shortWayDijkstra));
        System.out.println("ShortWaysDijkstra: " + Arrays.deepToString(dijkstra_vertex));


        System.out.println("Selected: " + Arrays.toString(sel));






    }


    @FXML
    void drawVertex(ActionEvent event) {

        int k = 0;

        // Draw vertices in loop

        for(int i = 0; i < n; i++){

            int x1 = coorToDrawWeightLine[i][0];
            int y1 = coorToDrawWeightLine[i][1];

            int x2 = coorToDrawWeightLine[i][2];
            int y2 = coorToDrawWeightLine[i][3];

            int weight = coorToDrawWeightLine[i][4];

            if(x1 == x2 && y1 == y2){
                coordWeightDrawLoopArrow[k][0] = x1;
                coordWeightDrawLoopArrow[k][1] = y1;
                coordWeightDrawLoopArrow[k][2] = 0;
                drawLoop(x1, y1);
                k++;
            } else {
                drawLines(x1, y1, x2, y2, weight, coordinates, r, i, false);
            }

        }

        for(int i = 0; i<=coordinates.length; i++){
            if(coordinates[i][0] == 0 &&  coordinates[i][1] == 0) break;
            Circle edge = new Circle(coordinates[i][0], coordinates[i][1], radius);
            edge.setFill(Color.WHITE);
            edge.setStroke(Color.BLACK);
            pane.getChildren().add(edge);
        }
        numCoord = inputText.TextToEdges(line, diagonal, x_start, y_start, size);

        for(int i = 0; i <= numCoord.length; i++){
            if(numCoord[i][0] == 0 && numCoord[i][1] == 0) break;
            String snum = Integer.toString(i+1);
            Text num = new Text(numCoord[i][0], numCoord[i][1], snum);
            num.setFont(Font.font ("Arial Black", 14));
            pane.getChildren().add(num);
        }
    }


    int count = 0;
    int count2 = 0;
    public void drawLines(int x1, int y1, int x2, int y2, int weight, int[][] masuvTochoc, int r, int i, boolean f){

        if(edgeCoord.findCoord(x1, y1, x2, y2, weight, masuvTochoc, r, i, topNum) == null){

            if(f == true){
                lined(x1, y1, x2, y2, weight, count, true);
                count2++;
            } else {
                lined(x1, y1, x2, y2, weight, count, false);
                count++;
            }

        } else {
            int[] foundCoor = new int[2];
            foundCoor = edgeCoord.findCoord(x1, y1, x2, y2, weight, masuvTochoc, r, i, topNum);

            int x3 = foundCoor[0];
            int y3 = foundCoor[1];

            int[] form = new int[2];
            form = indention.formula(x1, y1, x2, y2, x3, y3, 8);

            int x4 = form[0];
            int y4 = form[1];

            drawLines(x1, y1, x4, y4, weight, masuvTochoc, r, i, true);
            drawLines(x4, y4, x2, y2, weight, masuvTochoc, r, i, true);
        }
    }


    int count3 = 0;
    public void lined (int x1, int y1, int x2, int y2, int weight, int count, boolean f) {

        int[] ser = new int[2];
        int x = (x2 + x1)/2;
        int y = (y2 + y1)/2;
        ser = indention.formula(x1, y1, x2, y2, x, y, radius*2);
        int x3 = ser[0];
        int y3 = ser[1];


        Line line3 = new Line(x1, y1, x3, y3);
        pane.getChildren().add(line3);
        Line line4 = new Line(x3, y3, x2, y2);
        pane.getChildren().add(line4);



        if (f == false){
            coordArrow[count][0] = x1;
            coordArrow[count][1] = y1;
            coordArrow[count][2] = x3;
            coordArrow[count][3] = y3;
            coordArrow[count][4] = weight;
        }
        if(count2%2 == 0 && f == true){
            coordArrow2[count3][0] = x1;
            coordArrow2[count3][1] = y1;
            coordArrow2[count3][2] = x3;
            coordArrow2[count3][3] = y3;
            coordArrow2[count3][4] = weight;
            count3++;
        }

    }

    @FXML
    void drawWeigth(ActionEvent event) {

        int x,y;

        int[][] concat = helpfun.concat2D(coordArrow2, coordArrow, coordWeightDrawLoopArrow);

        for(int i = 0; i < concat.length; i++){

            if(concat[i][0] == 0 && concat[i][1] == 0) break;

            if(concat[i][0] == concat[i][2]){
                x = concat[i][2] - radius*2;
                y = concat[i][3] + radius*2;
            } else {
                x = concat[i][2];
                y = concat[i][3];
            }



            Circle edge = new Circle(x, y, radius - 3);
            edge.setFill(Color.WHITE);
            edge.setStroke(Color.CORAL);
            pane.getChildren().add(edge);

            int weight = concat[i][4];

            String snum = Integer.toString(weight);
            Text num = new Text(x - 4, y + 4, snum);
            num.setFont(Font.font ("Arial Black", 10));
            pane.getChildren().add(num);

        }

        System.out.println("Concat " + Arrays.deepToString(concat));


    }

    @FXML
    void drawDijkstraGraph(ActionEvent event) {

        int size = 7;
        int x = 880;
        int y = 430;

        Text titleTree = new Text(x + 100, 45, "The shortest ways");
        titleTree.setFont(Font.font ("Arial Black", 24));
        pane.getChildren().add(titleTree);

        int[]line_diagonal2 = l_d.getEdgePos(topNum);

        int line2 = line_diagonal2[0];
        int diagonal2 = line_diagonal2[1];

        coordinates2 = coord.getCoord(line2, diagonal2, x, y, size);

        numOfVertex2 = coord_ver_line.numOfVertex(matrix, topNum);

        for(int i = 0; i<=coordinates2.length; i++){
            if(coordinates2[i][0] == 0 &&  coordinates2[i][1] == 0) break;
            Circle edge = new Circle(coordinates2[i][0], coordinates2[i][1], radius);
            edge.setFill(Color.WHITE);
            edge.setStroke(Color.BLACK);
            pane.getChildren().add(edge);
        }

        numCoord2 = inputText.TextToEdges(line2, diagonal2, x,y,size);

        for(int i = 0; i <= numCoord2.length; i++){
            if(numCoord2[i][0] == 0 && numCoord2[i][1] == 0) break;
            String snum = Integer.toString(i+1);
            Text num = new Text(numCoord2[i][0], numCoord2[i][1], snum);
            pane.getChildren().add(num);
            if(i == startVert - 1){
                Text num2 = new Text(numCoord2[i][0], numCoord2[i][1], snum);
                num2.setFont(Font.font ("Arial Black"));
                pane.getChildren().add(num2);
            }
        }

        Text titleTable = new Text(x + 160, y + 90, "Dijkstra table");
        titleTable.setFont(Font.font ("Arial Black", 22));
        pane.getChildren().add(titleTable);

        for(int i = 0; i < topNum; i++){

            String snum = Integer.toString(i+1);
            String text = snum + "  | ";

            if(i >= 9) text = snum + "|";

            Text vert = new Text(x - 10 + (i*45), y + 130, text);
            vert.setFont(Font.font ("Arial Black", 20));
            pane.getChildren().add(vert);

        }

    }

    int click = 0;
    int c = 0;

    @FXML
    void drawDijkstra(ActionEvent event) {

        int[][] coorToDrawWeightLineSDijkstra = w_fun.getWeightLineCoord(dijkstra_vertex, coordinates2, dijkstra_vertex.length);

        int x1 = coorToDrawWeightLineSDijkstra[click][0];
        int y1 = coorToDrawWeightLineSDijkstra[click][1];

        int x2 = coorToDrawWeightLineSDijkstra[click][2];
        int y2 = coorToDrawWeightLineSDijkstra[click][3];

        int weight = coorToDrawWeightLineSDijkstra[click][4];

        drawLines(x1, y1, x2, y2, weight, coordinates2, r, click, false);

        click++;

        for(int i = 0; i<=coordinates2.length; i++){
            if(coordinates2[i][0] == 0 &&  coordinates2[i][1] == 0) break;
            Circle edge = new Circle(coordinates2[i][0], coordinates2[i][1], radius);
            edge.setFill(Color.WHITE);
            edge.setStroke(Color.BLACK);
            pane.getChildren().add(edge);
        }

        if(x1 != 0){
            Circle edge = new Circle(x1, y1, radius);
            edge.setFill(Color.RED);
            edge.setStroke(Color.BLACK);
            pane.getChildren().add(edge);


            Circle edge2 = new Circle(x2, y2, radius);
            edge2.setFill(Color.ORANGE);
            edge2.setStroke(Color.BLACK);
            pane.getChildren().add(edge2);
        }



        for(int i = 0; i <= numCoord2.length; i++){
            if(numCoord2[i][0] == 0 && numCoord2[i][1] == 0) break;
            String snum = Integer.toString(i+1);
            Text num = new Text(numCoord2[i][0], numCoord2[i][1], snum);
            pane.getChildren().add(num);
            if(i == startVert - 1){
                Text num2 = new Text(numCoord2[i][0], numCoord2[i][1], snum);
                num2.setFont(Font.font ("Arial Black"));
                pane.getChildren().add(num2);
            }
        }


        if(weight == 0  || c == 0){
            for(int j = 0; j < dijkstraTable[0].length; j++) {

                int textSize = 20;
                int num = dijkstraTable[c][j];

                String snum = Integer.toString(num);

                if(num == 10000) snum = "ꝏ ";

                String text = snum + "  | ";

                if(num >= 10 || num == 10000) {
                    textSize = 14;
                    text = snum + "  |";
                }

                if(num >= 100 && num != 10000) {
                    textSize = 12;
                    text = snum + " |";
                }


                Text vert = new Text(880 - 10 + (j * 45), 430 + 150 + (c * 20), text);
                vert.setFont(Font.font("Arial Black", textSize));
                pane.getChildren().add(vert);
            }
            c++;
        }




    }


    public void drawLoop (int x, int y){
        int x2 = x - radius;
        int y2 = y + radius;

        Circle circle = new Circle(x2, y2, radius);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle);
    }


}
