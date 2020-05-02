package sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.lang.Math;


public class GraphController_three {

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
    private Button skeleton;

    @FXML
    private Button prima_btn;

    @FXML
    private Pane grid;


    int topNum = 0;
    int n = 0;
    int numOfVertex2 = 0;
    int startVert = 2;

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
    int[][] checkMatrix = new int[19][19];


    int[][] w_vertex = new int[190][3];
    int[][] prima_vertex = new int[20][3];



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
    AlgPrima prima = new AlgPrima();


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

        prima_vertex = prima.vertPrima(w_vertex, startVert, topNum);

        int[][] primMatrix = prima.primaMatrix(prima_vertex, topNum);




        System.out.println("Input matrix: " + Arrays.toString(nums));
        System.out.println("Matrix to draw: " + Arrays.deepToString(matrix));
        System.out.println("Coordinates of edges: " + Arrays.deepToString(coordinates));
        System.out.println("Coordinates of vertex: " + Arrays.deepToString(vertex));
        System.out.println("Coordinates of weight_vertex: " + Arrays.deepToString(w_vertex));
        System.out.println("Coordinates of lines: " + Arrays.deepToString(coorToDrawLine));
        System.out.println("Weight coordinates of lines: " + Arrays.deepToString(coorToDrawWeightLine));

        System.out.println("Number of vertex: " + n);

        System.out.println("Coordinates of prima_vertex: " + Arrays.deepToString(prima_vertex));
        System.out.println("Prima Matrix: " + Arrays.deepToString(primMatrix));


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
    void drawSkeleton(ActionEvent event) {

        int size = 7;
        int x = 880;
        int y = 430;

        Text titleTree = new Text(x + 150, 45, "Skeleton");
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


    int click = 0;

    @FXML
    void drawPrima(ActionEvent event) {

        int[][] coorToDrawWeightLineSkeleton = w_fun.getWeightLineCoord(prima_vertex, coordinates2, prima_vertex.length);

        int x1 = coorToDrawWeightLineSkeleton[click][0];
        int y1 = coorToDrawWeightLineSkeleton[click][1];

        int x2 = coorToDrawWeightLineSkeleton[click][2];
        int y2 = coorToDrawWeightLineSkeleton[click][3];

        int weight = coorToDrawWeightLineSkeleton[click][4];

        drawLines(x1, y1, x2, y2, weight, coordinates2, r, click, false);

        click++;

        for(int i = 0; i<=coordinates2.length; i++){
            if(coordinates2[i][0] == 0 &&  coordinates2[i][1] == 0) break;
            Circle edge = new Circle(coordinates2[i][0], coordinates2[i][1], radius);
            edge.setFill(Color.WHITE);
            edge.setStroke(Color.BLACK);
            pane.getChildren().add(edge);
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
