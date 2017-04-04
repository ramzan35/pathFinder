/**
 * Created by Ramzan Dieze on 04/04/2017.
 */
public class assignHCost {

    public static void assign(boolean[][] arr, int endI,int endJ){

        int[][] arrx = new int[arr.length][arr.length];

        System.out.println("Manhattan");

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                arrx[i][j] = Math.abs(i - endI) + Math.abs(j - endJ);
            }
        }

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                System.out.print(arrx[i][j]+ "   ");
            }
            System.out.println();
        }

        System.out.println("Euclidean");

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                arrx[i][j] = (int) Math.sqrt(Math.pow(i - endI,2) + Math.pow(j - endJ,2));
            }
        }

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                System.out.print(arrx[i][j]+ "   ");
            }
            System.out.println();
        }

        System.out.println("Chebeyshev");

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                arrx[i][j] = Math.max(Math.abs(i - endI),Math.abs(j - endJ));
            }
        }

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                System.out.print(arrx[i][j]+ "   ");
            }
            System.out.println();
        }

    }
}
