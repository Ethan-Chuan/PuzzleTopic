
import tw.chuan.ethan.algorithms.Matrix;

/**
 *
 * @author Ethan
 */
public class Algorithms {

    public static void main(String[] args) {
        Algorithms alg = new Algorithms();
        
        alg.showMatrixEx();
    }

    void showMatrixEx(){
        int N = 3;
        int flag = 10;
        double mat[][] = {{1, 3, 5},{1, 1, 3},{1, 1, 1}};
        System.out.println("----------------Matrix display----------------");
        Matrix.display(mat);

        System.out.println("----------------Matrix add----------------");
        double[][] result1 = Matrix.add(
                mat,
                mat
        );
        Matrix.display(result1);

        System.out.println("----------------Matrix adjoint----------------");
        double[][] result2 = new double[N][N];
        Matrix.adjoint(
                mat,
                result2
        );
        Matrix.display(result1);

        System.out.println("----------------Matrix determinant----------------");
        double result3 = Matrix.determinant(mat);
        System.out.println(result3);

        System.out.println("----------------Matrix submatrix----------------");
        int p = 1, q = 1;
        System.out.println("submatrix of (" + p + "," + q + ") is");
        double[][] result4 = Matrix.submatrix(mat,p,q);
        if(result4==null)
            System.out.println("It don't have submatrix.");
        else
            Matrix.display(result4);

        System.out.println("----------------Matrix getSize----------------");
        int[] result5 = Matrix.getSize(mat);
        System.out.println("row:" + result5[0] + "\ncolumn:" + result5[1]);

        System.out.println("----------------Matrix identify----------------");
        double[][] result6 = Matrix.identity(N);
        Matrix.display(result6);

        System.out.println("----------------Matrix inverse----------------");
        double[][] result7 = new double[N][N];
        if (Matrix.inverse(mat, result7)) {
            Matrix.display(result7);
        } else {
            System.out.println("It don't have inverse matrix.");
        }

        System.out.println("----------------Matrix multiply----------------");
        double[][] result8 = Matrix.multiply(
                mat,
                mat
        );
        Matrix.display(result8);
        
        System.out.println("----------------Matrix scalar----------------");
        double s = 0.1;
        System.out.println("multipling scalar of " + s + " is");
        double[][] result9 = Matrix.scalar(
                s,
                mat
        );
        Matrix.display(result9, flag);
        
        System.out.println("----------------Matrix transpose----------------");
        double[][] result10 = Matrix.transpose(mat);
        Matrix.display(result10);
        
        System.out.println("----------------Matrix Elementary Row Operation Switching----------------");
        int r1=0,r2=2;
        System.out.println("Switching row " + (r1+1) + " and row " + (r2+1));
        Matrix.eroSwitching(mat, r1, r2);
        Matrix.display(mat);        
        Matrix.eroSwitching(mat, r1, r2);
        
        System.out.println("----------------Matrix Elementary Row Operation Multiplication----------------");
        r1=1; s=2;
        System.out.println("Each element in row " + (r1+1) + " is multiplied by " + s);
        Matrix.eroMultiplication(mat, r1, s);
        Matrix.display(mat);        
        Matrix.eroMultiplication(mat, r1, 1/s);
        
        System.out.println("----------------Matrix Elementary Row Operation Addition----------------");
        r1=1; r2=2; s=2;
        System.out.println("A row " + (r1+1) + " is replaced by the sum of row " + (r1+1) + " a multiple " + s + " by row " + (r2+1));
        Matrix.eroAddition(mat, r1, r2, s);
        Matrix.display(mat);        
        Matrix.eroAddition(mat, r1, r2, -s);
        
        System.out.println("----------------Original Matrix----------------");
        Matrix.display(mat);
        
        System.out.println("----------------Upper Triangular Matrix----------------");
        double[][] result11 = Matrix.upperTriangular(mat);
        Matrix.display(result11);
        
        System.out.println("----------------Lower Triangular Matrix----------------");
        double[][] result12 = Matrix.lowerTriangular(mat);
        Matrix.display(result12);
        
        System.out.println("----------------Diagonal Matrix----------------");
        double[][] result13 = Matrix.diagonal(mat);
        Matrix.display(result13);
    }
}
