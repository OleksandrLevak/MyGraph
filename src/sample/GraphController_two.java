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
import java.util.stream.IntStream;


public class GraphController_two {

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
    private Button drawArrows;

    @FXML
    private Button Searchtree;

    @FXML
    private Button drawTreeEdges;

    @FXML
    private Button bfs;

    @FXML
    private Pane grid;


    int topNum = 0;
    int n = 0;

    int[][] coordinates = new int[19][3];
    int[][] coordinates2 = new int[19][3];
    int[][] coorToDrawLine = new int[400][4];

    int[][] coordArrow = new int[100][4];
    int[][] coordArrow2 = new int[100][4];
    int[][] numCoord = new int[400][2];
    int[][] numCoord2 = new int[400][2];

    int[][] coordOfEdgesToDraw = new int[19][40];
    int[] coordOfEdgesToDrawinLine = new int[60];


    int radius = 12;
    int r = radius / 2 + 3;
    int line = 0;
    int diagonal = 0;
    int[] line_diagonal = new int[2];
    int[][] coordDrawLoopArrow = new int[19][2];
    int[][] matrix = new int[11][11];
    int[][] matrixRes = new int[19][19];
    int[][] coorde = new int[19][2];



    // Classes
    PositionOfEdges l_d = new PositionOfEdges();
    CoordToDrawEdges coord = new CoordToDrawEdges();
    CoordToDrawLines coord_ver_line = new CoordToDrawLines();
    IndentionFormula indention = new IndentionFormula();
    EdegeOnWay edgeCoord = new EdegeOnWay();
    NumInEdges inputText = new NumInEdges();
    CreateCrawlTree crawlTree = new CreateCrawlTree();
    BFS bfsearch = new BFS();


    //Graph settings

    int x_start = 60;
    int y_start = 580;
    int size = 10;

    int startVert = 2;

    int numOfVertex2 = 0;


    @FXML
    void buttonClicked(ActionEvent event) {

        topNum = Integer.parseInt(num.getText());

        line_diagonal = l_d.getEdgePos(topNum);

        line = line_diagonal[0];
        diagonal = line_diagonal[1];


        coordinates = coord.getCoord(line, diagonal, x_start, y_start, size);

        for (int i = 0; i <= coordinates.length; i++) {
            if (coordinates[i][0] == 0 && coordinates[i][1] == 0) break;
            Circle edge = new Circle(coordinates[i][0], coordinates[i][1], radius);
            edge.setFill(Color.WHITE);
            edge.setStroke(Color.BLACK);
            pane.getChildren().add(edge);
        }

        numCoord = inputText.TextToEdges(line, diagonal, x_start, y_start, size);

        for (int i = 0; i <= numCoord.length; i++) {
            if (numCoord[i][0] == 0 && numCoord[i][1] == 0) break;
            String snum = Integer.toString(i + 1);
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

        for (int i = 0; i < matrText2.length; i++) {
            nums[i] = Integer.parseInt(matrText2[i]);
        }

        // Create a 2d matrix from an array


        int count = 0;
        for (int i = 0; i < topNum; i++) {
            for (int j = 0; j < topNum; j++) {
                matrix[i][j] = nums[count];
                count++;
            }
        }


        // Create an array of vertices numbers, which are connected

        int[][] vertex = coord_ver_line.getVertex(matrix, topNum);

        n = coord_ver_line.numOfVertex(matrix, topNum);

        // Create an array of coordinates to draw vertices

        coorToDrawLine = coord_ver_line.getLineCoord(vertex, coordinates, n);

        int[][] elem = crawlTree.getArrBranch(vertex, topNum, startVert);
        coorde = crawlTree.getCoord(elem);
        matrixRes = crawlTree.getMatrix(coorde, topNum);


        int[] bfs = bfsearch.getArrOfVert(elem);
        coordOfEdgesToDraw = bfsearch.getCoordDrawBFS(elem, coordinates);
        coordOfEdgesToDrawinLine = bfsearch.getCoordDrawBFSinLine(elem, coordinates);




        System.out.println("Input matrix: " + Arrays.toString(nums));
        System.out.println("Matrix to draw: " + Arrays.deepToString(matrix));
        System.out.println("Coordinates of edges: " + Arrays.deepToString(coordinates));
        System.out.println("Coordinates of vertex: " + Arrays.deepToString(vertex));
        System.out.println("Coordinates of lines: " + Arrays.deepToString(coorToDrawLine));
        System.out.println("Number of vertex: " + n);
        System.out.println();
        System.out.println("Branches: " + Arrays.deepToString(elem));
        System.out.println("Coord: " + Arrays.deepToString(coorde));
        System.out.println("Matrix: " + Arrays.deepToString(matrixRes));
        System.out.println();
        System.out.println("ArrOfVertBFS: " + Arrays.toString(bfs));
        System.out.println("Coord of branches: " + Arrays.deepToString(coordOfEdgesToDraw));
        System.out.println("Coord of branches in line: " + Arrays.toString(coordOfEdgesToDrawinLine));


    }


    @FXML
    void drawVertex(ActionEvent event) {

        int k = 0;

        // Draw vertices in loop

        for (int i = 0; i < n; i++) {

            int x1 = coorToDrawLine[i][0];
            int y1 = coorToDrawLine[i][1];

            int x2 = coorToDrawLine[i][2];
            int y2 = coorToDrawLine[i][3];

            if (x1 == x2 && y1 == y2) {
                coordDrawLoopArrow[k][0] = x1;
                coordDrawLoopArrow[k][1] = y1;
                drawLoop(x1, y1);
                k++;
            } else {
                drawLines(x1, y1, x2, y2, coordinates, r, i, false);
            }

        }

        for (int i = 0; i <= coordinates.length; i++) {
            if (coordinates[i][0] == 0 && coordinates[i][1] == 0) break;
            Circle edge = new Circle(coordinates[i][0], coordinates[i][1], radius);
            edge.setFill(Color.WHITE);
            edge.setStroke(Color.BLACK);
            pane.getChildren().add(edge);
        }
        numCoord = inputText.TextToEdges(line, diagonal, x_start, y_start, size);

        for (int i = 0; i <= numCoord.length; i++) {
            if (numCoord[i][0] == 0 && numCoord[i][1] == 0) break;
            String snum = Integer.toString(i + 1);
            Text num = new Text(numCoord[i][0], numCoord[i][1], snum);
            num.setFont(Font.font("Arial Black", 14));
            pane.getChildren().add(num);
        }
    }


    int count = 0;
    int count2 = 0;

    public void drawLines(int x1, int y1, int x2, int y2, int[][] masuvTochoc, int r, int i, boolean f) {

        if (edgeCoord.findCoord(x1, y1, x2, y2, masuvTochoc, r, i, topNum) == null) {

            if (f == true) {
                lined(x1, y1, x2, y2, count, true);
                count2++;
            } else {
                lined(x1, y1, x2, y2, count, false);
                count++;
            }

        } else {
            int[] foundCoor = new int[2];
            foundCoor = edgeCoord.findCoord(x1, y1, x2, y2, masuvTochoc, r, i, topNum);

            int x3 = foundCoor[0];
            int y3 = foundCoor[1];

            int[] form = new int[2];
            form = indention.formula(x1, y1, x2, y2, x3, y3, 8);

            int x4 = form[0];
            int y4 = form[1];

            drawLines(x1, y1, x4, y4, masuvTochoc, r, i, true);
            drawLines(x4, y4, x2, y2, masuvTochoc, r, i, true);
        }
    }


    int count3 = 0;

    public void lined(int x1, int y1, int x2, int y2, int count, boolean f) {

        int[] ser = new int[2];
        int x = (x2 + x1) / 2;
        int y = (y2 + y1) / 2;
        ser = indention.formula(x1, y1, x2, y2, x, y, radius * 2);
        int x3 = ser[0];
        int y3 = ser[1];


        Line line3 = new Line(x1, y1, x3, y3);
        pane.getChildren().add(line3);
        Line line4 = new Line(x3, y3, x2, y2);
        pane.getChildren().add(line4);


        if (f == false) {
            coordArrow[count][0] = x1;
            coordArrow[count][1] = y1;
            coordArrow[count][2] = x3;
            coordArrow[count][3] = y3;
        }
        if (count2 % 2 == 0 && f == true) {
            coordArrow2[count3][0] = x1;
            coordArrow2[count3][1] = y1;
            coordArrow2[count3][2] = x3;
            coordArrow2[count3][3] = y3;
            count3++;
        }

    }


    @FXML
    void drawArrows(ActionEvent event) {
        for (int i = 0; i < coordArrow.length; i++) {

            int x1 = coordArrow[i][2];
            int y1 = coordArrow[i][3];

            int x2 = coordArrow[i][0];
            int y2 = coordArrow[i][1];

            if (x1 == 0 && x2 == 0) break;

            drawArrow(x1, y1, x2, y2);

        }
        for (int i = 0; i < coordArrow2.length; i++) {

            int x1 = coordArrow2[i][2];
            int y1 = coordArrow2[i][3];

            int x2 = coordArrow2[i][0];
            int y2 = coordArrow2[i][1];

            if (x1 == 0 && x2 == 0) break;

            drawArrow(x1, y1, x2, y2);

        }
        for (int i = 0; i < coordDrawLoopArrow.length; i++) {

            int x1 = coordDrawLoopArrow[i][0];
            int y1 = coordDrawLoopArrow[i][1];
            drawLoopArrow(x1, y1);

        }
        System.out.println(" ");
        System.out.println("Coords of simple arrows: " + Arrays.deepToString(coordArrow));
        System.out.println("Coords of indetional arrows: " + Arrays.deepToString(coordArrow2));

    }

    public void drawArrow(int x1, int y1, int x2, int y2) {
        int[] res = new int[2];
        int[] res2 = new int[2];

        int arrowLength = 16;

        double len = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        int x3 = (int) Math.ceil(((x1 - x2) / len * radius + x2));
        int y3 = (int) Math.ceil(((y1 - y2) / len * radius + y2));

        int x = (x3 + x1) / 2;
        int y = (y3 + y1) / 2;


        res = indention.formula(x3, y3, x1, y1, x, y, 16);
        res2 = indention.formula(x1, y1, x3, y3, x, y, 16);

        double lenArrow1 = Math.sqrt(Math.pow(x3 - res[0], 2) + Math.pow(y3 - res[1], 2));

        int x4 = (int) Math.ceil(((res[0] - x3) / lenArrow1 * arrowLength + x3));
        int y4 = (int) Math.ceil(((res[1] - y3) / lenArrow1 * arrowLength + y3));

        int x5 = (int) Math.ceil(((res2[0] - x3) / lenArrow1 * arrowLength + x3));
        int y5 = (int) Math.ceil(((res2[1] - y3) / lenArrow1 * arrowLength + y3));


        Line line4 = new Line(x4, y4, x3, y3);
        pane.getChildren().add(line4);

        Line line5 = new Line(x5, y5, x3, y3);
        pane.getChildren().add(line5);


    }


    public void drawLoop(int x, int y) {
        int x2 = x - radius;
        int y2 = y + radius;

        Circle circle = new Circle(x2, y2, radius);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle);
    }

    public void drawLoopArrow(int x, int y) {
        int x2 = x - radius;
        int y2 = y + radius;

        int x3 = (x2 + x) / 2;
        int y3 = (y2 + y) / 2;

        int n1 = y2 - y;
        int n2 = x - x2;

        int x4 = n1 / 2 + x3;
        int y4 = n2 / 2 + y3;

        double len = Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2));

        int x5 = (int) ((x2 - x) / len * radius + x4);
        int y5 = (int) ((y2 - y) / len * radius + y4);

        Line line12 = new Line(x4, y4, x5 + 8, y5 + 2);
        pane.getChildren().add(line12);

        Line line13 = new Line(x4, y4, x5 + 4, y5);
        pane.getChildren().add(line13);
    }

    int bfsClick = 0;
    int k1 = 0;

    @FXML
    void drawBFS(ActionEvent event) {

        int k = 0;

        if(bfsClick < topNum){
            for(int j = 0; j < coordOfEdgesToDraw.length; j++){
                if(coordOfEdgesToDraw[bfsClick][j] == 0) break;
                int x = coordOfEdgesToDraw[bfsClick][j + k];
                int y = coordOfEdgesToDraw[bfsClick][j + 1 + k];

                if (coordOfEdgesToDraw[bfsClick][j + k] != 0) {
                    Circle edge = new Circle(x, y, radius);
                    edge.setFill(Color.YELLOW);
                    edge.setStroke(Color.BLACK);
                    pane.getChildren().add(edge);
                }

                k++;
            }
        }


        if(bfsClick >= 1) {

            int count = bfsClick - 1;

            int x2 = coordOfEdgesToDrawinLine[count + k1];
            int y2 = coordOfEdgesToDrawinLine[count + 1 + k1];

            if (coordOfEdgesToDrawinLine[count + k1] != 0 && coordOfEdgesToDrawinLine[count + 1 + k1] != 0) {
                Circle edge2 = new Circle(x2, y2, radius);
                edge2.setFill(Color.RED);
                edge2.setStroke(Color.BLACK);
                pane.getChildren().add(edge2);
            }

            k1++;
        }


            for(int i = 0; i <= numCoord.length; i++){
                if(numCoord[i][0] == 0 && numCoord[i][1] == 0) break;
                String snum = Integer.toString(i+1);
                Text num = new Text(numCoord[i][0], numCoord[i][1], snum);
                num.setFont(Font.font ("Arial Black", 14));
                pane.getChildren().add(num);
                if(i == startVert - 1){
                    Text num2 = new Text(numCoord[i][0], numCoord[i][1], snum);
                    num2.setFont(Font.font ("Arial Black", 16));
                    pane.getChildren().add(num2);
                }
            }

        bfsClick++;
    }

    @FXML
    void drawSearchtree(ActionEvent event) {

        int size = 7;
        int x = 790;
        int y = 420;

        Text titleTree = new Text(x + 145, 45, "Search tree");
        titleTree.setFont(Font.font ("Arial Black", 24));
        pane.getChildren().add(titleTree);

        int[]line_diagonal2 = l_d.getEdgePos(topNum);

        int line2 = line_diagonal2[0];
        int diagonal2 = line_diagonal2[1];

        coordinates2 = coord.getCoord(line2, diagonal2, x, y, size);

        numOfVertex2 = coord_ver_line.numOfVertex(matrixRes, topNum);

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

    int click = 0;

    @FXML
    void drawSearchtreeEdges(ActionEvent event) {
        // Draw vertices in loop

        int[][] coorToDrawLine2 = coord_ver_line.getLineCoord(coorde, coordinates2, numOfVertex2);

        int x1 = coorToDrawLine2[click][0];
        int y1 = coorToDrawLine2[click][1];

        int x2 = coorToDrawLine2[click][2];
        int y2 = coorToDrawLine2[click][3];

        drawLines(x1, y1, x2, y2, coordinates2, r, click, false);

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


}

