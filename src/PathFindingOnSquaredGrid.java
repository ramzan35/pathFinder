import java.util.*;

/*************************************************************************
 *  Author: Dr E Kapetanios
 *  Last update: 22-02-2017
 *
 *************************************************************************/

public class PathFindingOnSquaredGrid {

    public final static int matrixSize = getGridSize();
    ;

    static LinkedList<Integer>[] vertex;

    // given an N-by-N matrix of open cells, return an N-by-N matrix
    // of cells reachable from the top
    public static boolean[][] flow(boolean[][] open) {
        int N = open.length;

        boolean[][] full = new boolean[N][N];
        for (int j = 0; j < N; j++) {
            flow(open, full, 0, j);
        }

        return full;
    }

    // determine set of open/blocked cells using depth first search
    public static void flow(boolean[][] open, boolean[][] full, int i, int j) {
        int N = open.length;

        // base cases
        if (i < 0 || i >= N) return;    // invalid row
        if (j < 0 || j >= N) return;    // invalid column
        if (!open[i][j]) return;        // not an open cell
        if (full[i][j]) return;         // already marked as open

        full[i][j] = true;

        flow(open, full, i + 1, j);   // down
        flow(open, full, i, j + 1);   // right
        flow(open, full, i, j - 1);   // left
        flow(open, full, i - 1, j);   // up
    }

    // does the system percolate?
    public static boolean percolates(boolean[][] open) {
        int N = open.length;

        boolean[][] full = flow(open);
        for (int j = 0; j < N; j++) {
            if (full[N - 1][j]) return true;
        }

        return false;
    }

    // does the system percolate vertically in a direct way?
    public static boolean percolatesDirect(boolean[][] open) {
        int N = open.length;

        boolean[][] full = flow(open);
        int directPerc = 0;
        for (int j = 0; j < N; j++) {
            if (full[N - 1][j]) {
                // StdOut.println("Hello");
                directPerc = 1;
                int rowabove = N - 2;
                for (int i = rowabove; i >= 0; i--) {
                    if (full[i][j]) {
                        // StdOut.println("i: " + i + " j: " + j + " " + full[i][j]);
                        directPerc++;
                    } else break;
                }
                if (directPerc == N) return true;
            }
        }

        // StdOut.println("Direct Percolation is: " + directPerc);

        return false;
    }

    // draw the N-by-N boolean matrix to standard draw
    public static void show(boolean[][] a, boolean which) {
        int N = a.length;
        StdDraw.setXscale(-1, N);
        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (a[i][j] == which)
                    StdDraw.square(j, N - i - 1, .5);
                else StdDraw.filledSquare(j, N - i - 1, .5);
    }

    // draw the N-by-N boolean matrix to standard draw, including the points A (x1, y1) and B (x2,y2) to be marked by a circle
    public static void show(boolean[][] a, boolean which, int x1, int y1, int x2, int y2) {
        int N = a.length;
//        StdDraw.setXscale(-1, N);
//        ;
//        StdDraw.setYscale(-1, N);
        StdDraw.setPenColor(StdDraw.BLACK);
//        for (int i = 0; i < N; i++)
//            for (int j = 0; j < N; j++)
//                if (a[i][j] == which)
//                    if ((i == x1 && j == y1) || (i == x2 && j == y2)) {
        StdDraw.circle(y1, N - x1 - 1, .5);
        StdDraw.circle(y2, N - x2 - 1, .5);
//                    } else StdDraw.square(j, N - i - 1, .5);
//                else StdDraw.filledSquare(j, N - i - 1, .5);
//    }
    }

    // return a random N-by-N boolean matrix, where each entry is
    // true with probability p
    public static boolean[][] random(int N, double p) {
        vertex = new LinkedList[N];
        boolean[][] a = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = StdRandom.bernoulli(p);
                if (!a[i][j]) {
                    if (vertex[i] == null)
                        vertex[i] = new LinkedList<Integer>();
                    vertex[i].add(j);
                }
            }
        }
        return a;
    }

    public static int getGridSize() {
        System.out.println("Enter grid size");
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                return in.nextInt();
            } catch (Exception e) {
                System.err.println("Enter an integer number.");
                in.next();
            }
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println();
        System.out.println("Enter the probability");
        double probability;
        while (true) {
            try {
                probability = in.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.err.println("Enter a decimal value in between 0.1 - 0.9");
                in.next();
            }
        }
        System.out.println();

        // boolean[][] open = StdArrayIO.readBoolean2D();

        // The following will generate a 10x10 squared grid with relatively few obstacles in it
        // The lower the second parameter, the more obstacles (black cells) are generated

        boolean[][] randomlyGenMatrix = new boolean[matrixSize][matrixSize];

        while (true) {
            try {
                randomlyGenMatrix = random(matrixSize, probability);
                break;
            } catch (IllegalArgumentException e) {
                System.err.println(e);
                probability = in.nextDouble();
            }
        }

//        StdArrayIO.print(randomlyGenMatrix);
        show(randomlyGenMatrix, true);

        System.out.println();
//        System.out.println("The system percolates: " + percolates(randomlyGenMatrix));

        System.out.println();
//        System.out.println("The system percolates directly: " + percolatesDirect(randomlyGenMatrix));
        System.out.println();

        int Ai, Aj, Bi, Bj;

        // Reading the coordinates for points A and B on the input squared grid.
        while (true) {
            try {
                System.out.println("Enter i for A > ");
                Ai = in.nextInt();

                System.out.println("Enter j for A > ");
                Aj = in.nextInt();

                System.out.println("Enter i for B > ");
                Bi = in.nextInt();

                System.out.println("Enter j for B > ");
                Bj = in.nextInt();

                break;

            } catch (InputMismatchException e) {
                System.err.println("Enter a valid node cordinates.");
                in.next();
            }
        }

        //display coordinates of starting and end points
        System.out.println();
        System.out.println("Coordinates for A: [" + Ai + "," + Aj + "]");
        System.out.println("Coordinates for B: [" + Bi + "," + Bj + "]");
        System.out.println();

        // THIS IS AN EXAMPLE ONLY ON HOW TO USE THE JAVA INTERNAL WATCH
        // Stop the clock ticking in order to capture the time being spent on inputting the coordinates
        // You should position this command accordingly in order to perform the algorithmic analysis

        //        LinkedList<Integer>[] arr = vertex;
        //  assignHCost.assign(randomlyGenMatrix,Bi,Bj);

        Stopwatch timerFlow = null;

        if ((randomlyGenMatrix[Ai][Aj] && randomlyGenMatrix[Bi][Bj])) {

            try {
//                AStar.myMethod(Ai, Aj, Bi, Bj, vertex, randomlyGenMatrix);
                timerFlow = new Stopwatch();

                //initialize variables in PathFinder class
                PathFinder.initializeVar(Ai, Aj, Bi, Bj, randomlyGenMatrix);

                //show path based on Manhattan
//                PathFinder.Man();

//                PathFinder.Ec();
//                PathFinder.Che();

//                System.out.println();

//                StdOut.println("Elapsed time = " + timerFlow.elapsedTime());

            } catch (Exception e) {
                e.printStackTrace();
            }

            //show the starting and end nodes with circles
            show(randomlyGenMatrix, true, Ai, Aj, Bi, Bj);

            System.out.println();
            System.out.println("=========> Enter [E] for Euclidean path");
            System.out.println();
            System.out.println("=========> Enter [C] for Chebeyshev path");
            System.out.println();
            System.out.println("=========> Enter [M] for Manhattan path");

            //get input from user for display a particular path
            String command = in.next();

            while (!command.equals("A")) {
                StdDraw.clear(StdDraw.WHITE);
                switch (command) {
                    case "M" :
                        PathFinder.pathDesign=1;
                        timerFlow = new Stopwatch();
                        PathFinder.Man();
                        break;
                    case "E":
                        PathFinder.pathDesign=2;
                        timerFlow = new Stopwatch();
                        PathFinder.Ec();
                        break;
                    case "C":
                        PathFinder.pathDesign=3;
                        StdDraw.setPenRadius(0.006);
                        timerFlow = new Stopwatch();
                        PathFinder.Che();
                        break;
                    case "Q":
                        break;
                    default:
                        System.out.println("Enter a valid selection");
                }
                StdDraw.setPenRadius(0.001);
//                show the starting and end nodes with circle
                show(randomlyGenMatrix, true, Ai, Aj, Bi, Bj);

                StdOut.println("Elapsed time = " + timerFlow.elapsedTime());

                //draw the matrix again
                show(randomlyGenMatrix, true);

                command = in.next();
            }

            //if the starting or ending point is blocked
        } else {
            System.out.println("Enter valid Starting and Ending point.");
        }
    }
}







