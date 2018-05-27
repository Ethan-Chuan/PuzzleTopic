
package tw.chuan.ethan.algorithms;

/**
 *
 * @author Ethan
 */
public class Matrix {
    
    public static int[] getSize(int[][] mat)
    {
        int n = mat.length;
        int m = mat[0].length;
        return new int[]{n,m};
    }
    
    public static int[] getSize(double[][] mat)
    {
        int n = mat.length;
        int m = mat[0].length;
        return new int[]{n,m};
    }
    
    
    /* function for displaying the matrix */
    public static void display(double[] x, int flag)
    {   
        for (int i = 0; i < x.length; i++)
                System.out.print(String.format("%."+flag+"f\t", x[i]));
        System.out.print("\n");
    }
    public static void display(double[][] mat, int flag)
    {
        int[] size = getSize(mat);
        
        for (int i = 0; i < size[0]; i++){
            for (int j = 0; j < size[1]; j++)
                System.out.print(String.format("%."+flag+"f\t", mat[i][j]));
            
            System.out.print("\n");
        }
    }
    
    // Function to get cofactor of 
    // mat[p][q] in temp[][].
    public static void getCofactor(double[][] mat, 
             double[][] cofactor, int p, int q, int n)
    {
        int i = 0, j = 0;

        // Looping for each element of 
        // the matrix
        for (int row = 0; row < n; row++){
            for (int col = 0; col < n; col++){
                
                // Copying into temporary matrix 
                // only those element which are 
                // not in given row and column
                if (row != p && col != q){
                    cofactor[i][j++] = mat[row][col];
                    
                    // Row is filled, so increase 
                    // row index and reset col 
                    //index
                    if (j == n - 1){
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }
    
    
    /* Recursive function for finding determinant
    of matrix. n is current dimension of mat[][]. */
    public static double determinant(double mat[][], int n)
    {
        int D = 0; // Initialize result
     
        // Base case : if matrix contains single
        // element
        if (n == 1)
            return mat[0][0];
         
        // To store cofactors
        double [][] cofactor = new double[n-1][n-1]; 
         
        // To store sign multiplier
        int sign = 1; 
     
        // Iterate for each element of first row
        for (int f = 0; f < n; f++){
            // Getting Cofactor of mat[0][f]
            getCofactor(mat, cofactor, 0, f, n);
            D += sign * mat[0][f] 
               * determinant(cofactor, n - 1);
     
            // terms are to be added with 
            // alternate sign
            sign = -sign;
        }
     
        return D;
    }
    
    
    // Function to get adjoint of A[N][N] in adj[N][N].
    public static void adjoint(double[][] mat, double[][] adj){
        int[] size = getSize(mat);
        int N;
        if (((N=size[0]) == 1) || N!=size[1]){
            adj[0][0] = 1;
            return;
        }

        // temp is used to store cofactors of A[][]
        int sign;
        double[][] cofactor = new double[N][N];

        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                // Get cofactor of A[i][j]
                getCofactor(mat, cofactor, i, j, N);

                // sign of adj[j][i] positive if sum of row
                // and column indexes is even.
                sign = ((i+j)%2==0)? 1: -1;

                // Interchanging rows and columns to get the
                // transpose of the cofactor matrix
                adj[j][i] = (sign)*(determinant(cofactor, N-1));
            }
        }
    }
    
    // Function to calculate and store inverse, returns false if
    // matrix is singular
    public static boolean inverse(double[][] mat, double[][] inverse)
    {
        int[] size = getSize(mat);
        int N;
        if((N=size[0])!=size[1]){
            // Not a matrix, can't find its inverse.
            return false;
        }
        
        // Find determinant of A[][]
        double det = determinant(mat, N);
        if (det == 0){
            // Singular matrix, can't find its inverse.
            return false;
        }

        // Find adjoint
        double[][] adj = new double[N][N];
        adjoint(mat, adj);

        // Find Inverse using formula "inverse(A) = adj(A)/det(A)"
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++)
                inverse[i][j] = adj[i][j] / (double)det;

        return true;
    }
    
    // return matC = matA * matB
    public static double[][] multiply(double[][] matA, double[][] matB){
        int[] sizeA = getSize(matA);
        int[] sizeB = getSize(matB);
        if (sizeA[1] != sizeB[0]) throw new RuntimeException("Illegal matrix dimensions.");
        double[][] matC = new double[sizeA[0]][sizeB[1]];
        for(int i=0;i<sizeA[0];i++)
            for(int j=0;j<sizeB[1];j++)
                for(int k=0;k<sizeA[1];k++)
                    matC[i][j] += matA[i][k] * matB[k][j];
        return matC;
    }
    
    // matrix-vector multiplication (y = matA * x)
    public static double[] multiply(double[][] matA, double[] x) {
        int[] sizeA = getSize(matA);
        if (x.length != sizeA[1]) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[sizeA[0]];
        for (int i = 0; i < sizeA[0]; i++)
            for (int j = 0; j < sizeA[1]; j++)
                y[i] += matA[i][j] * x[j];
        return y;
    }


    // vector-matrix multiplication (y = x^T A)
    public static double[] multiply(double[] x, double[][] matA) {
        int[] sizeA = getSize(matA);
        if (x.length != sizeA[0]) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[sizeA[1]];
        for (int j = 0; j < sizeA[1]; j++)
            for (int i = 0; i < sizeA[0]; i++)
                y[j] += matA[i][j] * x[i];
        return y;
    }
}
