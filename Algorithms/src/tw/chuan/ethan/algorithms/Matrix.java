
package tw.chuan.ethan.algorithms;

/**
 *
 * @author Ethan
 */
public class Matrix {
     
    // Return matC = matA + matB
    public static double[][] add(double[][] matA, double[][] matB)
    {
        int[] sizeA = getSize(matA);
        int[] sizeB = getSize(matB);
        if(sizeA[0]!=sizeB[0] || sizeA[1]!=sizeB[1]) throw new RuntimeException("Illegal matrix dimensions");
        double[][] matC = new double[sizeA[0]][sizeA[1]];
        for (int i = 0; i < sizeA[0]; i++)
            for (int j = 0; j < sizeA[1]; j++)
                matC[i][j] = matA[i][j] + matB[i][j];
        return matC;
    }
    
    // Function to get adjoint of mat[N][N] in adj[N][N].
    public static void adjoint(double[][] mat, double[][] adj)
    {
        int[] size = getSize(mat);
        int N;
        if (((N=size[0]) == 1) && N==size[1]){
            adj[0][0] = 1;
            return;
        }
        
        if(N!=size[1]) throw new RuntimeException("Illegal matrix dimensions.");

        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                // Get cofactor of mat[i][j]
                // Interchanging rows and columns to get the
                // transpose of the cofactor matrix
                adj[j][i] = cofactor(mat, i, j);
            }
        }
    }
    
    // Return cofactor of mat[p][q].
    public static double cofactor(double[][] mat, int p, int q){
        double[][] submatrix = submatrix(mat, p, q);
        if(submatrix==null) throw new RuntimeException("Illegal matrix dimensions or row index.");
        // sign of adj[j][i] positive if sum of row
        // and column indexes is even.
        int sign = ((p+q)%2==0)? 1: -1;
        return sign*determinant(submatrix);
    }
    
    // Recursive function for finding determinant
    // of matrix. n is current dimension of mat[][].
    public static double determinant(double[][] mat)
    {
        int[] size = getSize(mat);
        if(size[0]!=size[1]) throw new RuntimeException("Illegal matrix dimensions.");
        return determinant(mat, mat.length);
    }
    private static double determinant(double[][] mat, int n)
    {
        int D = 0; // Initialize result
     
        // Base case : if matrix contains single
        // element
        if (n == 1)
            return mat[0][0];
     
        // Iterate for each element of first row
        for (int f = 0; f < n; f++){
            // Getting Cofactor of mat[0][f] and calculating determinant.
            D += mat[0][f] * cofactor(mat, 0, f);
        }
        return D;
    }
    
    // Return matD that is Diagonal matrix
    public static double[][] diagonal(double[][] mat){
        int[] size = getSize(mat);
        if(size[0]!=size[1]) throw new RuntimeException("Illegal matrix dimensions.");
        
        int N = size[0];
        double[][] matD = new double[N][N];
        
        for(int i=0;i<N;i++)
            matD[i] = mat[i].clone();
        
        for(int i=0;i<N-1;i++)
            for(int j=N-1;j>i;j--)
                matD[i][j] = 0;
        for(int i=1;i<N;i++)
            for(int j=0;j<i;j++)
                matD[i][j] = 0;  
        
        return matD;
    }
    
    // Function for displaying the matrix
    public static void display(double[] x, int flag)
    {   
        for (int i = 0; i < x.length; i++)
                System.out.print(String.format("%."+flag+"f\t", x[i]));
        System.out.print("\n");
    }
    public static void display(double[][] mat)
    {
        // default flag = 0
        display(mat, 0);
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
    
    // Elementary Row Operation Addition
    // specific row r1 replaced by sum of that row r1 and a multiple s of another row r2.
    public static void eroAddition(double[][] mat, int r1, int r2, double s)
    {
        if(mat[r1].length!=mat[r2].length) throw new RuntimeException("Illegal row dimensions.");
        double[] tmpColumn = mat[r2].clone();
        eroMultiplication(mat, r2, s);
        for(int i=0;i<tmpColumn.length;i++)
            mat[r1][i] += mat[r2][i];
        mat[r2] = tmpColumn;
    }
    // Elementary Row Operation Multiplication
    // each element in specific row r is multiplied by a non-zero constant c.
    public static void eroMultiplication(double[][] mat, int r, double c)
    {
        int[] size = getSize(mat);
        for(int i=0;i<size[1];i++)
            mat[r][i] *= c;
    }
    // Elementary Row Operation Switching
    // a specific row r1 is switched with another row r2.
    public static void eroSwitching(double[][] mat, int r1, int r2)
    {
        double[] tmpColumn = mat[r1].clone();
        mat[r1] = mat[r2].clone();
        mat[r2] = tmpColumn;
    }
    
    // Return matrix size. Row:size[0] Column:size[1]
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
    
    // Return n-by-n identity matrix I
    public static double[][] identity(int n)
    {
        double[][] I = new double[n][n];
        for (int i = 0; i < n; i++)
            I[i][i] = 1;
        return I;
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
    
    // Return matLT that is lower triangular matrix
    public static double[][] lowerTriangular(double[][] mat){
        int[] size = getSize(mat);
        if(size[0]!=size[1]) throw new RuntimeException("Illegal matrix dimensions.");
        
        int N = size[0];
        double[][] matLT = new double[N][N];
        
        for(int i=0;i<N;i++)
            matLT[i] = mat[i].clone();
        
        for(int i=0;i<N-1;i++)
            for(int j=N-1;j>i;j--)
                matLT[i][j] = 0;
        
        return matLT;
    }
    
    // Return matC = matA * matB
    public static double[][] multiply(double[][] matA, double[][] matB)
    {
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
    
    // Return y = matA * x. Matrix-vector multiplication 
    public static double[] multiply(double[][] matA, double[] x)
    {
        int[] sizeA = getSize(matA);
        if (x.length != sizeA[1]) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[sizeA[0]];
        for (int i = 0; i < sizeA[0]; i++)
            for (int j = 0; j < sizeA[1]; j++)
                y[i] += matA[i][j] * x[j];
        return y;
    }


    // Return y = x^T * A. Vector-matrix multiplication
    public static double[] multiply(double[] x, double[][] matA)
    {
        int[] sizeA = getSize(matA);
        if (x.length != sizeA[0]) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[sizeA[1]];
        for (int j = 0; j < sizeA[1]; j++)
            for (int i = 0; i < sizeA[0]; i++)
                y[j] += matA[i][j] * x[i];
        return y;
    }
    
    // Return matB = s * matA. Scalar-matrix multiplication
    public static double[][] scalar(double s, double[][] matA)
    {
        int[] sizeA = getSize(matA);
        double[][] matB = new double[sizeA[0]][sizeA[1]];
        for(int i=0;i<sizeA[0];i++)
            for(int j=0;j<sizeA[1];j++)
                matB[i][j] = s * matA[i][j];
        return matB;
    }
    
    // Return submatrix of mat[p][q].
    public static double[][] submatrix(double[][] mat, int p, int q)
    {
        int[] size = getSize(mat);
        if(size[0]-1==0 || size[1]-1==0 || p<0 || p>=size[0] || q<0 || q>=size[1])
            return null;
        
        double[][] submatrix = new double[size[0]-1][size[1]-1];
        
        int i = 0, j = 0;

        // Looping for each element of the matrix
        for (int row = 0; row < size[0]; row++){
            for (int col = 0; col < size[1]; col++){
                
                // Copying into temporary matrix 
                // only those element which are 
                // not in given row and column
                if (row != p && col != q){
                    submatrix[i][j++] = mat[row][col];
                    
                    // Row is filled, so increase row index 
                    // and reset col index
                    if (j == size[1] - 1){
                        j = 0;
                        i++;
                    }
                }
            }
        }
        return submatrix;
    }
    
    // Return matB = matA^T
    public static double[][] transpose(double[][] matA)
    {
        int[] sizeA = getSize(matA);
        double[][] matB = new double[sizeA[0]][sizeA[1]];
        for (int i = 0; i < sizeA[1]; i++)
            for (int j = 0; j < sizeA[0]; j++)
                matB[j][i] = matA[i][j];
        return matB;
    }
    
    // Return matUT that is upper triangular matrix
    public static double[][] upperTriangular(double[][] mat){
        int[] size = getSize(mat);
        if(size[0]!=size[1]) throw new RuntimeException("Illegal matrix dimensions.");
        
        int N = size[0];
        double[][] matUT = new double[N][N];
        
        for(int i=0;i<N;i++)
            matUT[i] = mat[i].clone();
        
        for(int i=1;i<N;i++)
            for(int j=0;j<i;j++)
                matUT[i][j] = 0;        
        
        return matUT;
    }
}
