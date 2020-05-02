package sample;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.lang.Math;


public class GraphController {

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
    private Button powerEdge;

    @FXML
    private Button showWays;

    @FXML
    private Button components;

    @FXML
    private Button сondensation;

    @FXML
    private Pane grid;


    int topNum = 0;
    int n = 0;

    int[][] coordinates = new int[19][3];
    int[][] coorToDrawLine = new int[400][4];

    int[][] coordArrow  = new int[100][4];
    int[][] coordArrow2 = new int[100][4];
    int[][] numCoord = new int[400][2];


    int radius = 12;
    int r = radius/2 + 3;
    int line = 0;
    int diagonal = 0;
    boolean isDirect = false;
    int [] line_diagonal = new int[2];
    int[][] coordDrawLoopArrow = new int[19][2];
    int[][] checkMatrix = new int[19][19];
    int[][] powersDirect = new int[19][3];
    int[][] matrix = new int[11][11];

    int[] isolatedVertexes = new int[15];
    int[] hangingVertexes = new int[15];

    int[][] waysLen2 = new int[200][3];
    int[][] waysLen3 = new int[200][4];

    int[][] component = new int[400][19];
    int[][] condens = new int[400][19];



    // Classes
    PositionOfEdges l_d = new PositionOfEdges();
    CoordToDrawEdges coord = new CoordToDrawEdges();
    CoordToDrawLines coord_ver_line = new CoordToDrawLines();
    IndentionFormula indention = new IndentionFormula();
    EdegeOnWay edgeCoord = new EdegeOnWay();
    NumInEdges inputText = new NumInEdges();
    CheckDirect check = new CheckDirect();
    TypeOfVertexes type = new TypeOfVertexes();
    CreateArrayOfWays arrWays = new CreateArrayOfWays();
    ContiguityMatrix conMatrix = new ContiguityMatrix();
    StrongConnect strongConn = new StrongConnect();
    СondensationMatrix condensation = new СondensationMatrix();


    @FXML
    void buttonClicked(ActionEvent event) {

        topNum = Integer.parseInt(num.getText());

        line_diagonal = l_d.getEdgePos(topNum);

        line = line_diagonal[0];
        diagonal = line_diagonal[1];


        coordinates = coord.getCoord(line, diagonal, 690, 560, 10);

        for(int i = 0; i<=coordinates.length; i++){
            if(coordinates[i][0] == 0 &&  coordinates[i][1] == 0) break;
            Circle edge = new Circle(coordinates[i][0], coordinates[i][1], radius);
            edge.setFill(Color.WHITE);
            edge.setStroke(Color.BLACK);
            pane.getChildren().add(edge);
        }

        numCoord = inputText.TextToEdges(line, diagonal, 690, 560, 10);

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
        isDirect = check.isDirect(matrix);
        checkMatrix = check.NonDirect(matrix);



        // Create an array of vertices numbers, which are connected

       int[][] vertex = coord_ver_line.getVertex(matrix, topNum);

        n = coord_ver_line.numOfVertex(matrix, topNum);

        // Create an array of coordinates to draw vertices

        coorToDrawLine = coord_ver_line.getLineCoord(vertex, coordinates, n);

        // Create array of edges power

        for(int i = 0; i < topNum; i++){
            powersDirect[i][0] = i + 1;
            powersDirect[i][1] = 0;
            powersDirect[i][2] = 0;
        }

        for(int j = 0; j < n; j++){
            int in = vertex[j][1] - 1;
            int out = vertex[j][0] - 1;

            powersDirect[in][1]++;
            powersDirect[out][2]++;

        }

        isolatedVertexes = type.isoleted(powersDirect);
        hangingVertexes = type.hanging(powersDirect);

        //int[][] array = {{0, 1, 0, 1, 0}, {0, 0, 0, 0, 1}, {1, 0, 0, 0, 0}, {0, 0, 1, 0, 1}, {0, 1, 0, 0, 0}};

        int[][] arrLen2  = arrWays.createWayMatrixLen2(matrix, false);
        int[][] arrLen3 = arrWays.createWayMatrixLen3(matrix, arrLen2, false);

        int[][] arrCordeInputMat = arrWays.createCoordInputMatrix(matrix);
        int[][] arrCordeLen2 = arrWays.createCoordFromMatr(arrLen2);
        int[][] arrCordeLen3 = arrWays.createCoordFromMatr(arrLen3);


        waysLen2 = arrWays.getWaysLen2(arrCordeInputMat, arrCordeLen2);
        waysLen3 = arrWays.getWaysLen3(arrCordeInputMat, arrCordeLen2, arrCordeLen3, waysLen2);

        int[][] reachMat = conMatrix.getConMatrix(matrix);
        int[][] reachMat2 = arrWays.createWayMatrixLen2(reachMat, true);

        int[][] strongCon = strongConn.getMatrix(reachMat);

        int numsub = condensation.copmlexCopmonents(matrix);

        component = strongConn.getComponet(strongCon);

        condens = condensation.getCondentMat(matrix, arrCordeInputMat, component);





        System.out.println("Input matrix: " + Arrays.toString(nums));
        System.out.println("Matrix to draw: " + Arrays.deepToString(matrix));
        System.out.println("Coordinates of edges: " + Arrays.deepToString(coordinates));
        System.out.println("Coordinates of vertex: " + Arrays.deepToString(vertex));
        System.out.println("Coordinates of lines: " + Arrays.deepToString(coorToDrawLine));
        System.out.println("Isolated: " + Arrays.toString(isolatedVertexes));
        System.out.println("Hanging: " + Arrays.toString(hangingVertexes));
        System.out.println("Number of vertex: " + n);
        System.out.println(numsub);

        System.out.println();

        System.out.println("WaysLen2: " + Arrays.deepToString(arrLen2));
        System.out.println("WaysLen3: " + Arrays.deepToString(arrLen3));

        System.out.println("InputMatrixCoord: " + Arrays.deepToString(arrCordeInputMat));
        System.out.println("CoordLen2: " + Arrays.deepToString(arrCordeLen2));
        System.out.println("CoordLen3: " + Arrays.deepToString(arrCordeLen3));

        System.out.println();

        System.out.println("Arr of len2: " + Arrays.deepToString(waysLen2));
        System.out.println("Arr of len3: " + Arrays.deepToString(waysLen3));
        System.out.println("Reachability Matrix: " + Arrays.deepToString(reachMat));
        System.out.println("Reachability Matrix^2: " + Arrays.deepToString(reachMat2));
        System.out.println("Strong connection Matrix: " + Arrays.deepToString(strongCon));
        System.out.println("Strong connection components: " + Arrays.deepToString(component));
        System.out.println("Condensation Matrix: " + Arrays.deepToString(condens));



    }


    @FXML
    void drawVertex(ActionEvent event) {

        int k = 0;

        // Draw vertices in loop

        for(int i = 0; i < n; i++){

         int x1 = coorToDrawLine[i][0];
         int y1 = coorToDrawLine[i][1];

         int x2 = coorToDrawLine[i][2];
         int y2 = coorToDrawLine[i][3];

         if(x1 == x2 && y1 == y2){
             coordDrawLoopArrow[k][0] = x1;
             coordDrawLoopArrow[k][1] = y1;
             drawLoop(x1, y1);
             k++;
         } else {
             drawLines(x1, y1, x2, y2, coordinates, r, i, false);
         }

        }

        for(int i = 0; i<=coordinates.length; i++){
            if(coordinates[i][0] == 0 &&  coordinates[i][1] == 0) break;
            Circle edge = new Circle(coordinates[i][0], coordinates[i][1], radius);
            edge.setFill(Color.WHITE);
            edge.setStroke(Color.BLACK);
            pane.getChildren().add(edge);
        }
        numCoord = inputText.TextToEdges(line, diagonal, 690, 560, 10);

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
    public void drawLines(int x1, int y1, int x2, int y2, int[][] masuvTochoc, int r, int i, boolean f){

        if(edgeCoord.findCoord(x1, y1, x2, y2, masuvTochoc, r, i, topNum) == null){

            if(f == true){
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
    public void lined (int x1, int y1, int x2, int y2, int count, boolean f) {

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
            }
            if(count2%2 == 0 && f == true){
                coordArrow2[count3][0] = x1;
                coordArrow2[count3][1] = y1;
                coordArrow2[count3][2] = x3;
                coordArrow2[count3][3] = y3;
                count3++;
            }

    }


    @FXML
    void drawArrows(ActionEvent event) {
        for(int i = 0; i < coordArrow.length; i++){

            int x1 = coordArrow[i][2];
            int y1 = coordArrow[i][3];

            int x2 = coordArrow[i][0];
            int y2 = coordArrow[i][1];

            if(x1 == 0 && x2 == 0) break;

            drawArrow(x1, y1, x2, y2);

        }
        for(int i = 0; i < coordArrow2.length; i++){

            int x1 = coordArrow2[i][2];
            int y1 = coordArrow2[i][3];

            int x2 = coordArrow2[i][0];
            int y2 = coordArrow2[i][1];

            if(x1 == 0 && x2 == 0) break;

            drawArrow(x1, y1, x2, y2);

        }
        for(int i = 0; i < coordDrawLoopArrow.length; i++){

            int x1 = coordDrawLoopArrow[i][0];
            int y1 = coordDrawLoopArrow[i][1];
            drawLoopArrow(x1, y1);

        }
        System.out.println(" ");
        System.out.println("Coords of simple arrows: "  + Arrays.deepToString(coordArrow));
        System.out.println("Coords of indetional arrows: "  + Arrays.deepToString(coordArrow2));

    }
    public void drawArrow (int x1, int y1, int x2, int y2){
        int[] res  = new int[2];
        int[] res2 = new int[2];

        int arrowLength = 16;

        double len = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        int x3 = (int) Math.ceil(((x1-x2)/len*radius + x2));
        int y3 = (int) Math.ceil(((y1-y2)/len*radius + y2));

        int x = (x3 + x1)/2;
        int y = (y3 + y1)/2;


        res =  indention.formula(x3, y3, x1, y1, x, y, 16);
        res2 = indention.formula(x1, y1, x3, y3, x, y, 16);

        double lenArrow1 = Math.sqrt(Math.pow(x3 - res[0], 2)  + Math.pow(y3 - res[1], 2));

        int x4 = (int) Math.ceil(((res[0]-x3)/lenArrow1*arrowLength + x3));
        int y4 = (int) Math.ceil(((res[1]-y3)/lenArrow1*arrowLength + y3));

        int x5 = (int) Math.ceil(((res2[0]-x3)/lenArrow1*arrowLength + x3));
        int y5 = (int) Math.ceil(((res2[1]-y3)/lenArrow1*arrowLength + y3));


            Line line4 = new Line(x4, y4, x3, y3);
            pane.getChildren().add(line4);

            Line line5 = new Line(x5, y5, x3, y3);
            pane.getChildren().add(line5);


    }


    public void drawLoop (int x, int y){
        int x2 = x - radius;
        int y2 = y + radius;

        Circle circle = new Circle(x2, y2, radius);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle);
    }

    public void drawLoopArrow (int x, int y){
        int x2 = x - radius;
        int y2 = y + radius;

        int x3 = (x2 + x)/2;
        int y3 = (y2 + y)/2;

        int n1 = y2 - y;
        int n2 = x - x2;

        int x4 = n1/2 + x3;
        int y4 = n2/2 + y3;

        double len = Math.sqrt(Math.pow(x2 - x, 2) + Math.pow(y2 - y, 2));

        int x5 = (int) ((x2-x)/len*radius + x4);
        int y5 = (int) ((y2-y)/len*radius + y4);

        Line line12 = new Line(x4, y4, x5 + 8, y5 + 2);
        pane.getChildren().add(line12);

        Line line13 = new Line(x4, y4, x5 + 4, y5);
        pane.getChildren().add(line13);
    }

    @FXML
    void drawPower(ActionEvent event) {
        int k = 0;
        int l = 0;
        int flag = 0;

        int y_start = 670;
        int y_indent = 310;

        int x_start = 1065;

        for(int i = 0; i < topNum; i++){
            if(powersDirect[0][1] + powersDirect[0][2] == powersDirect[i][1] + powersDirect[i][2]){
                flag++;
            }
            if((i+1)%3 == 1) {
                k += 30;
                l = i*160;
            }
            String edgenum = Integer.toString(i + 1);
            if(isDirect == true){
                String pos = Integer.toString(powersDirect[i][1]);
                String neg = Integer.toString(powersDirect[i][2]);
                String res = "Vertex" + edgenum + ": " + "+ " + pos + ", - " + neg;
                Text text = new Text(x_start + i*160 - l, y_start + k, res);
                text.setFont(Font.font ("Verdana", 15));
                pane.getChildren().add(text);
            } else {
                int sumInt = powersDirect[i][1] + powersDirect[i][2];
                String sum = Integer.toString(sumInt);
                String res = "Vertex" + edgenum + ": " + sum;
                Text text = new Text(x_start + i*160 - l, y_start + k, res);
                text.setFont(Font.font ("Verdana", 15));
                pane.getChildren().add(text);
            }
        }

        if(flag == topNum){
            Text text = new Text(x_start, y_start + y_indent, "Regular: yes");
            text.setFont(Font.font ("Verdana", 15));
            pane.getChildren().add(text);
        } else {
            Text text = new Text(x_start, y_start + y_indent, "Regular: no");
            text.setFont(Font.font ("Verdana", 15));
            pane.getChildren().add(text);
        }



        Text text = new Text(x_start, y_start + y_indent - 50, "Isolated vertices:");
        text.setFont(Font.font ("Verdana", 15));
        pane.getChildren().add(text);

        Text text3 = new Text(x_start, y_start + y_indent - 25, "Hanging vertices:");
        text3.setFont(Font.font ("Verdana", 15));
        pane.getChildren().add(text3);

        for(int i = 0; i<topNum; i++){
            if(isolatedVertexes[i] == 0) break;
            String isolated = Integer.toString(isolatedVertexes[i]);
            Text text2 = new Text(x_start + 140 + i*20, y_start + y_indent - 50 , isolated);
            text2.setFont(Font.font ("Verdana", 15));
            pane.getChildren().add(text2);
        }

        for(int i = 0; i<topNum; i++){
            if(hangingVertexes[0] == 0){
                Text text2 = new Text(x_start + 140 + i*20, y_start + y_indent - 25, "None");
                text2.setFont(Font.font ("Verdana", 15));
                pane.getChildren().add(text2);
            }
            if(hangingVertexes[i] == 0) break;
            String isolated = Integer.toString(hangingVertexes[i]);
            Text text2 = new Text(x_start + 140 + i*20, y_start + y_indent - 25 , isolated);
            text2.setFont(Font.font ("Verdana", 15));
            pane.getChildren().add(text2);
        }

        System.out.println("Powers of edges: " + Arrays.deepToString(powersDirect));

    }


    @FXML
    void showWays(ActionEvent event) {

        //Output ways length 2

        Text titleLen2 = new Text(875, 685, "Length 2");
        titleLen2.setFont(Font.font ("Verdana", 18));
        pane.getChildren().add(titleLen2);

        int k = 0;

        for(int i = 0; i < waysLen2.length;  i++){
            int formul_y = 715 + i*20;
            if(i >= 15) {
                k = 150;
                formul_y = 715 - (15 - i)*20;
            }
            if(waysLen2[i][0] == 0) break;
            String wayToPrint = Integer.toString(waysLen2[i][0]) + " -> " +
                                Integer.toString(waysLen2[i][1]) + " -> " +
                                Integer.toString(waysLen2[i][2]);

            Text ways = new Text(800 + k, formul_y, wayToPrint);
            ways.setFont(Font.font ("Verdana", 14));
            pane.getChildren().add(ways);
        }

        //Output ways length 3

        Text titleLen3 = new Text(600, 685, "Length 3");
        titleLen3.setFont(Font.font ("Verdana", 18));
        pane.getChildren().add(titleLen3);

        int c = 0;

        for(int i = 0; i < waysLen3.length;  i++){
            int formul_y = 715 + i*20;
            if(i >= 15) {
                c = 150;
                formul_y = 715 + (i - 15)*20;
            }
            if(waysLen3[i][0] == 0) break;
            String wayToPrint = Integer.toString(waysLen3[i][0]) + " -> " +
                    Integer.toString(waysLen3[i][1]) + " -> " +
                    Integer.toString(waysLen3[i][2]) + " -> " +
                    Integer.toString(waysLen3[i][3]);



            Text ways = new Text(490 + c, formul_y, wayToPrint);
            ways.setFont(Font.font ("Verdana", 14));
            pane.getChildren().add(ways);


        }


    }

    @FXML
    void components(ActionEvent event) {

        //Output components

        Text titleCompon = new Text(315, 685, "Components");
        titleCompon.setFont(Font.font ("Verdana", 18));
        pane.getChildren().add(titleCompon);

        int l = 0;

        for(int i = 0; i < component.length;  i++){
            int formul_y = 715 + i*25;
            if(i >= 13) {
                l = 150;
                formul_y = 715 + (i - 13)*20;
            }
            if(component[i][0] == 0) break;
            String elOfComp = "K" + Integer.toString(i + 1) + " {" ;

            for(int j = 0; j < component[i].length; j++){
                if(component[i][j] == 0) {
                    elOfComp += "}";
                    break;
                }
                if(component[i][j+1] != 0){
                    elOfComp += Integer.toString(component[i][j]) + ", ";
                } else {
                    elOfComp += Integer.toString(component[i][j]);
                }

            }

            Text components = new Text(300 + l, formul_y, elOfComp);
            components.setFont(Font.font ("Verdana", 14));
            pane.getChildren().add(components);
        }

        //Output condensation matrix

        Text titlematrix = new Text(80, 685, "Condensation");
        titlematrix.setFont(Font.font ("Verdana", 18));
        pane.getChildren().add(titlematrix);

        int e = 0;

        for(int i = 0; i < condens.length;  i++){

            int size = condens.length;

            String arr = "|" ;

            for(int j = 0; j < size; j++){

                if(j == size - 1) {
                    arr += Integer.toString(condens[i][j]) + "|";
                    break;
                }

                arr += Integer.toString(condens[i][j]) + " ";


            }

            Text components = new Text(30,730 + i*25 , arr);
            components.setFont(Font.font ("Verdana", 24));
            pane.getChildren().add(components);
        }
    }

    @FXML
    void сondensation(ActionEvent event) {

        int size = 7;
        int x = 110;
        int y = 500;

        int numVertex = condens.length;

        int[]line_diagonal2 = l_d.getEdgePos(numVertex);

        int line2 = line_diagonal2[0];
        int diagonal2 = line_diagonal2[1];

        int[][] coordinates2 = coord.getCoord(line2, diagonal2, x, y, size);

        int[][] vertex2 = coord_ver_line.getVertex(condens, numVertex);

        int n2 = coord_ver_line.numOfVertex(condens, numVertex);

        int[][] coorToDrawLine2 = coord_ver_line.getLineCoord(vertex2, coordinates2, n2);

        // Draw vertices in loop

        for(int i = 0; i < n; i++) {

            int x1 = coorToDrawLine2[i][0];
            int y1 = coorToDrawLine2[i][1];

            int x2 = coorToDrawLine2[i][2];
            int y2 = coorToDrawLine2[i][3];

            drawLines(x1, y1, x2, y2, coordinates2, r, i, false);
        }

        for(int i = 0; i<=coordinates2.length; i++){
            if(coordinates2[i][0] == 0 &&  coordinates2[i][1] == 0) break;
            Circle edge = new Circle(coordinates2[i][0], coordinates2[i][1], radius);
            edge.setFill(Color.WHITE);
            edge.setStroke(Color.BLACK);
            pane.getChildren().add(edge);
        }

        int[][] numCoord2 = inputText.TextToEdges(line2, diagonal2, x,y,size);

        for(int i = 0; i <= numCoord2.length; i++){
            if(numCoord2[i][0] == 0 && numCoord2[i][1] == 0) break;
            String snum = Integer.toString(i+1);
            Text num = new Text(numCoord2[i][0], numCoord2[i][1], snum);
            pane.getChildren().add(num);
        }

    }


}
