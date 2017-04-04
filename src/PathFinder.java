/**
 * Created by Ramzan Dieze on 14/03/2017.
 * 2015237
 * w1608461
 */

import java.util.*;

class PathFinder {

    public static int pathDesign=1;
    private static int DIAGONAL_COST=10;
    private static int Vertical_Horizontal_COST=10;
    //    private static ArrayList<Node> path;
    private static Node[][] cell;
    private static boolean[][] bloackedCells;
    private static PriorityQueue<Node> openedList;
    private static int startI, startJ;
    private static int endI, endJ;

    private static void findShortestPath(boolean forAllDirections) {

        //Create the staring node and add it to cell matrix
        cell[startI][startJ] = new Node(startI, startJ);
        cell[startI][startJ].hCost = Math.abs(startI - endI) + Math.abs(startJ - endJ);
        cell[startI][startJ].finalCost = 0;

        //add the start location to openedList list.
        openedList.add(cell[startI][startJ]);

        Node currentNode;

        while (true) {
//            System.out.println(openedList);

            //priority queue will return the node which has the lowest finalCost
            currentNode = openedList.poll();

            //if the list is empty break the loop
            if (currentNode == null) break;

            cell[currentNode.row][currentNode.col].visited = true;

            //if the end node is found return
            if (currentNode.equals(cell[endI][endJ])) {
                return;
            }

            Node neighbourNode;

            //check the currentNode nodes top 3 nodes

            //check the currentNode nodes top node
            if (currentNode.row - 1 >= 0) {

                //if the the current index is blocked or already created skip this process
                if (bloackedCells[currentNode.row - 1][currentNode.col] && cell[currentNode.row - 1][currentNode.col] == null) {
                    cell[currentNode.row - 1][currentNode.col] = new Node(currentNode.row - 1, currentNode.col);
                    //cell[currentNode.row - 1][currentNode.col].hCost = Math.abs(currentNode.row - 1 - endI) + Math.abs(currentNode.col - endJ);
                    assignHCost(cell[currentNode.row - 1][currentNode.col]);
                }
                neighbourNode = cell[currentNode.row - 1][currentNode.col];

                //check the surrounding node and add to openedList
                if (neighbourNode != null && !cell[neighbourNode.row][neighbourNode.col].visited)
                    addToOpenedList(currentNode, neighbourNode, currentNode.finalCost + Vertical_Horizontal_COST);

                //check the currentNode nodes top left node
                if (currentNode.col - 1 >= 0 && forAllDirections) {
                    if (bloackedCells[currentNode.row - 1][currentNode.col - 1] && cell[currentNode.row - 1][currentNode.col - 1] == null) {
                        cell[currentNode.row - 1][currentNode.col - 1] = new Node(currentNode.row - 1, currentNode.col - 1);
                        //cell[currentNode.row - 1][currentNode.col - 1].hCost = Math.abs(currentNode.row - 1 - endI) + Math.abs(currentNode.col - 1 - endJ);
                        assignHCost(cell[currentNode.row - 1][currentNode.col - 1]);
                    }
                    neighbourNode = cell[currentNode.row - 1][currentNode.col - 1];
                    if (neighbourNode != null && !cell[neighbourNode.row][neighbourNode.col].visited)
                        addToOpenedList(currentNode, neighbourNode, currentNode.finalCost + DIAGONAL_COST);
                }

                //check the currentNode nodes top right node
                if (currentNode.col + 1 < cell[0].length && forAllDirections) {
                    if (bloackedCells[currentNode.row - 1][currentNode.col + 1] && cell[currentNode.row - 1][currentNode.col + 1] == null) {
                        cell[currentNode.row - 1][currentNode.col + 1] = new Node(currentNode.row - 1, currentNode.col + 1);
//                        cell[currentNode.row - 1][currentNode.col + 1].hCost = Math.abs(currentNode.row - 1 - endI) + Math.abs(currentNode.col + 1 - endJ);
                        assignHCost(cell[currentNode.row - 1][currentNode.col + 1]);
                    }
                    neighbourNode = cell[currentNode.row - 1][currentNode.col + 1];
                    if (neighbourNode != null && !cell[neighbourNode.row][neighbourNode.col].visited)
                        addToOpenedList(currentNode, neighbourNode, currentNode.finalCost + DIAGONAL_COST);
                }
            }

            //check the currentNode nodes left node
            if (currentNode.col - 1 >= 0) {
                if (bloackedCells[currentNode.row][currentNode.col - 1] && cell[currentNode.row][currentNode.col - 1] == null) {
                    cell[currentNode.row][currentNode.col - 1] = new Node(currentNode.row, currentNode.col - 1);
//                    cell[currentNode.row][currentNode.col - 1].hCost = Math.abs(currentNode.row - endI) + Math.abs(currentNode.col - 1 - endJ);
                    assignHCost(cell[currentNode.row][currentNode.col - 1]);
                }
                neighbourNode = cell[currentNode.row][currentNode.col - 1];
                if (neighbourNode != null && !cell[neighbourNode.row][neighbourNode.col].visited)
                    addToOpenedList(currentNode, neighbourNode, currentNode.finalCost + Vertical_Horizontal_COST);
            }

            //check currentNode nodes right node
            if (currentNode.col + 1 < cell[0].length) {
                if (bloackedCells[currentNode.row][currentNode.col + 1] && cell[currentNode.row][currentNode.col + 1] == null) {
                    cell[currentNode.row][currentNode.col + 1] = new Node(currentNode.row, currentNode.col + 1);
//                    cell[currentNode.row][currentNode.col + 1].hCost = Math.abs(currentNode.row - endI) + Math.abs(currentNode.col + 1 - endJ);
                    assignHCost(cell[currentNode.row][currentNode.col + 1]);
                }
                neighbourNode = cell[currentNode.row][currentNode.col + 1];
                if (neighbourNode != null && !cell[neighbourNode.row][neighbourNode.col].visited)
                    addToOpenedList(currentNode, neighbourNode, currentNode.finalCost + Vertical_Horizontal_COST);
            }

            //check cuurent nodes bottom 3 nodes

            //check the currentNode nodes bottom node
            if (currentNode.row + 1 < cell.length) {
                if (bloackedCells[currentNode.row + 1][currentNode.col] && cell[currentNode.row + 1][currentNode.col] == null) {
                    cell[currentNode.row + 1][currentNode.col] = new Node(currentNode.row + 1, currentNode.col);
//                    cell[currentNode.row + 1][currentNode.col].hCost = Math.abs(currentNode.row + 1 - endI) + Math.abs(currentNode.col - endJ);
                    assignHCost(cell[currentNode.row + 1][currentNode.col]);
                }
                neighbourNode = cell[currentNode.row + 1][currentNode.col];
                if (neighbourNode != null && !cell[neighbourNode.row][neighbourNode.col].visited)
                    addToOpenedList(currentNode, neighbourNode, currentNode.finalCost + Vertical_Horizontal_COST);

                //check the currentNode nodes bottom left node
                if (currentNode.col - 1 >= 0 && forAllDirections) {
                    if (bloackedCells[currentNode.row + 1][currentNode.col - 1] && cell[currentNode.row + 1][currentNode.col - 1] == null) {
                        cell[currentNode.row + 1][currentNode.col - 1] = new Node(currentNode.row + 1, currentNode.col - 1);
//                        cell[currentNode.row + 1][currentNode.col - 1].hCost = Math.abs(currentNode.row + 1 - endI) + Math.abs(currentNode.col - 1 - endJ);
                        assignHCost(cell[currentNode.row + 1][currentNode.col - 1]);
                    }
                    neighbourNode = cell[currentNode.row + 1][currentNode.col - 1];
                    if (neighbourNode != null && !cell[neighbourNode.row][neighbourNode.col].visited)
                        addToOpenedList(currentNode, neighbourNode, currentNode.finalCost + DIAGONAL_COST);
                }

                //check the currentNode nodes bottom right node
                if (currentNode.col + 1 < cell[0].length && forAllDirections) {
                    if (bloackedCells[currentNode.row + 1][currentNode.col + 1] && cell[currentNode.row + 1][currentNode.col + 1] == null) {
                        cell[currentNode.row + 1][currentNode.col + 1] = new Node(currentNode.row + 1, currentNode.col + 1);
//                        cell[currentNode.row + 1][currentNode.col + 1].hCost = Math.abs(currentNode.row + 1 - endI) + Math.abs(currentNode.col + 1 - endJ);
                        assignHCost(cell[currentNode.row + 1][currentNode.col + 1]);
                    }
                    neighbourNode = cell[currentNode.row + 1][currentNode.col + 1];
                    if (neighbourNode != null && !cell[neighbourNode.row][neighbourNode.col].visited)
                        addToOpenedList(currentNode, neighbourNode, currentNode.finalCost + DIAGONAL_COST);
                }
            }
        }
    }

    static void Man() {
        Vertical_Horizontal_COST = 10;
        pathDesign = 1;

        //assign a new matrix object
        cell = new Node[PathFindingOnSquaredGrid.matrixSize][PathFindingOnSquaredGrid.matrixSize];

        //clear the priority queue
        openedList.clear();

        findShortestPath(false);

        //will Show green filledSquares if there is a path


        StdDraw.setPenColor(StdDraw.GREEN);
        System.out.println("Cost for the manhattan path : " + calculateCost());
        //        showSquarePath();
    }

    static void Ec() {
        DIAGONAL_COST = 14;
        pathDesign = 2;

        //assign a new matrix object
        cell = new Node[PathFindingOnSquaredGrid.matrixSize][PathFindingOnSquaredGrid.matrixSize];

        //clear the priority queue
        openedList.clear();

//        calculateDistanceForEuclidean();
        findShortestPath(true);

        //will Show blue circles if there is a path


        StdDraw.setPenColor(StdDraw.BLUE);
        System.out.println("Cost for the euclidean path : " + calculateCost());
        //        showCirclePath();
    }

    static void Che() {
        DIAGONAL_COST = 10;
        pathDesign = 3;

        //assign a new matrix object
        cell = new Node[PathFindingOnSquaredGrid.matrixSize][PathFindingOnSquaredGrid.matrixSize];

        //clear the priority queue
        openedList.clear();

//        calculateDistanceForChebyshev();
        findShortestPath(true);

        //will Show yellow line if there is a path

        StdDraw.setPenColor(StdDraw.YELLOW);
        System.out.println("Cost for the chebeshev path : " + calculateCost());
        //        showLinePath();
    }

    private static void addToOpenedList(Node currentNode, Node neighbourNode, double cost) {

        //add path cost and current node hCost
        double neighbourNodeFinalCost = neighbourNode.hCost + cost;

        boolean inOpenedList = openedList.contains(neighbourNode);

        //if the node in openedList or node's finalCost is less than current cost
        if (!inOpenedList || neighbourNodeFinalCost < neighbourNode.finalCost) {
            neighbourNode.finalCost = neighbourNodeFinalCost;
            neighbourNode.previous = currentNode;
            if (!inOpenedList)
                openedList.add(neighbourNode);
        }

    }

    //static void initializeVar(int sI, int sJ, int eI, int eJ, LinkedList<Integer>[] map, boolean[][] arrx)
    static void initializeVar(int sI, int sJ, int eI, int eJ, boolean[][] array) {

        //Set start Location
        startI = sI;
        startJ = sJ;

        //Set End Location
        endI = eI;
        endJ = eJ;

        //return the node which has lowest finalCost
        openedList = new PriorityQueue(new Comparator() {
            @Override
            public int compare(Object c1, Object c2) {
                return ((Node) c1).finalCost < ((Node) c2).finalCost ? -1 :
                        ((Node) c1).finalCost > ((Node) c2).finalCost ? 1 : 0;
            }
        });

        cell = new Node[PathFindingOnSquaredGrid.matrixSize][PathFindingOnSquaredGrid.matrixSize];

        //assign randomly generated boolean matrix
        bloackedCells = array;
    }

    private static double calculateCost() {

        double costForThePath = 0;

        //find the shortest path
        try {
//            path = new ArrayList<>();

            if (cell[endI][endJ].visited) {
                Node currentNode = cell[endI][endJ];
//                path.add(currentNode);
                showPath(currentNode);

                while (currentNode.previous != null) {

                    if (currentNode.previous.col == currentNode.col) {
                        costForThePath += Vertical_Horizontal_COST;
                    }
                    else if (currentNode.previous.row == currentNode.row) {
                        costForThePath += Vertical_Horizontal_COST;
                    }
                    else {
                        costForThePath += DIAGONAL_COST;
                    }
//                    path.add(currentNode.previous);
                    showPath(currentNode.previous);
                    currentNode = currentNode.previous;
                }
                System.out.println();
            }

        } catch (NullPointerException e) {
            System.out.println();
            System.out.println("No possible path");
        }

        return costForThePath / 10.0;
    }

    private static void showPath(Node cell) {

        if (pathDesign == 1)
            StdDraw.filledSquare(cell.col, PathFindingOnSquaredGrid.matrixSize - cell.row - 1, .3);

        else if (pathDesign == 2)
            StdDraw.circle(cell.col, PathFindingOnSquaredGrid.matrixSize - cell.row - 1, .3);

        else {
            if (cell.previous != null)
                StdDraw.line(cell.col, PathFindingOnSquaredGrid.matrixSize - cell.row - 1, cell.previous.col,
                        PathFindingOnSquaredGrid.matrixSize - cell.previous.row - 1);
        }
    }

    private static void assignHCost(Node vertex){
        if(pathDesign==1){
            cell[vertex.row][vertex.col].hCost = Math.abs(vertex.row - endI) + Math.abs(vertex.col - endJ);
        }
        else if(pathDesign==2){
            cell[vertex.row][vertex.col].hCost = (int) Math.sqrt(Math.pow(vertex.row - endI,2) + Math.pow(vertex.col - endJ,2));
        }
        else{
            cell[vertex.row][vertex.col].hCost = Math.max(Math.abs(vertex.row - endI),Math.abs(vertex.col - endJ));
        }
    }
}