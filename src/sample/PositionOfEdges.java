package sample;

public class PositionOfEdges {

    public int [] getEdgePos (int topNum){
        int [] line_diagonal = new int[2];
        int line = 0, diagonal = 0;
        switch (topNum){
            case 10:
                line = 4;
                diagonal = 3;
                break;
            case 11:
                line = 5;
                diagonal = 3;
                break;
            case 12:
                line = 4;
                diagonal = 4;
                break;
            case 13:
                line = 5;
                diagonal = 4;
                break;
            case 14:
                line = 6;
                diagonal = 4;
                break;
            case 15:
                line = 5;
                diagonal = 5;
                break;
            case 16:
                line = 6;
                diagonal = 5;
                break;
            case 17:
                line = 7;
                diagonal = 5;
                break;
            case 18:
                line = 6;
                diagonal = 6;
                break;
            case 19:
                line = 7;
                diagonal = 6;
                break;
        }
        line_diagonal[0] = line;
        line_diagonal[1] = diagonal;
        return line_diagonal;
    }
}
