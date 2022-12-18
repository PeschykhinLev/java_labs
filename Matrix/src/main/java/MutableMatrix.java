import java.util.Arrays;

public class MutableMatrix extends Matrix{
    //Епсилон-перевірка чи елемент на головній діагоналі не близький до нуля
    private static final double EPSILON = 1e-10;
    //Знову конструктори
    public MutableMatrix (){
        this.matrix=new double[1][1];
    }

    public MutableMatrix (int rows,int columns){
        this.matrix=new double[rows][columns];
    }

    public MutableMatrix(double[][] arr){
        this.matrix=Arrays.stream(arr).map(double[]::clone).toArray(double[][]::new);
    }

    public MutableMatrix(Matrix a){
        this.matrix=Arrays.stream(a.matrix).map(double[]::clone).toArray(double[][]::new);
    }
    //Приведення матриці до верхнього трикутного вигляду(методом Гауса)
    public void transformationIntoUpperTriangular(){
        if(this.matrix.length!=this.matrix[0].length){
            throw new RuntimeException("This matrix is not square");
        }
        int n =  getNrows();

        for (int p = 0; p < n; p++) {

            if (Math.abs(matrix[p][p]) <= EPSILON) {
                throw new ArithmeticException("Matrix is singular or nearly singular");
            }
            for (int i = p + 1; i < n; i++) {
                double alpha = matrix[i][p] / matrix[p][p];
                for (int j = p; j < n; j++) {
                    matrix[i][j] -= alpha * matrix[p][j];
                }
            }
        }
    }
    //Приведення матриці до нижнього трикутного вигляду(методом Гауса)
    public void transformationIntoLowerTriangular(){
        if(this.matrix.length!=this.matrix[0].length){
            throw new RuntimeException("This matrix is not square");
        }
        int n =  getNrows();

        for (int p = n-1; p >= 0; p--) {

            if (Math.abs(matrix[p][p]) <= EPSILON) {
                throw new ArithmeticException("Matrix is singular or nearly singular");
            }
            for (int i = p-1 ; i >= 0; i--) {
                double alpha = matrix[i][p] / matrix[p][p];
                for (int j = p; j >= 0; j--) {
                    matrix[i][j] -= alpha * matrix[p][j];
                }
            }
        }
    }

}
