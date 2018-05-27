
import tw.chuan.ethan.algorithms.Matrix;


/**
 *
 * @author Ethan
 */
public class Algorithms {
    
    public static void main(String[] args) {
        int N = 3;
        int flag = 10;
        double mat[][] = {{1, 3, 5},
                       {1, 1, 3},
                       {1, 1, 1}};
         Matrix.display(mat, flag);
         System.out.println("Determinant " + 
                    "of the matrix is : "+
             Matrix.determinant(mat, N));
         
         
         
         System.out.println();
         double[][] result = Matrix.multiply(
                 new double[][]{
                     {1, 0, 0},
                     {0, 1, 0},
                     {0, 0, 1}},
                 mat
                 );
         Matrix.display(result, flag);
         
         
    }
    
}
