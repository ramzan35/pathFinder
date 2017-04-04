/**
 * Created by Ramzan Dieze on 14/03/2017.
 * 2015237
 * w1608461
 */
public class Node {

    int hCost = 0;
    double finalCost = 0;
    int row, col;
    boolean visited;
    Node previous;

    Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return this.row + " " + this.col;
    }

}

