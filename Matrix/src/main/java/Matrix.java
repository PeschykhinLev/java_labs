import java.util.Arrays;
import java.util.Random;

public class Matrix{

    protected double[][] matrix;
    //Конструктор за замовчуванням
    public Matrix(){
        this.matrix=new double[1][1];
    }
    //Конструктор з параметрами
    public Matrix(int rows,int columns){
        this.matrix=new double[rows][columns];
    }
    //Конструктор з параметрами
    public Matrix(double[][] arr){
        this.matrix=Arrays.stream(arr).map(double[]::clone).toArray(double[][]::new);
    }
    //Конструктор копіювання
    public Matrix(Matrix a){
        this.matrix=Arrays.stream(a.matrix).map(double[]::clone).toArray(double[][]::new);
    }
    //Функція для заповнення вже створеної матриці значеннями
    public void fillWithValues(double[][] arr){
        if(matrix.length!=arr.length||matrix[0].length!=arr[0].length){
            throw new RuntimeException("These are matrices of different dimensions");
        }
        this.matrix=Arrays.stream(arr).map(double[]::clone).toArray(double[][]::new);
    }
    //змінна значення комірки за координатами arr[i][j]
    public void setValueAt(int i,int j,double n){
        if(j<0||j>=this.matrix[0].length||i<0||i>=this.matrix.length){
            throw new RuntimeException("Incorrect set index");
        }
        matrix[i][j]=n;
    }
    //Отримання двовимірного масиву значень
    public double[][] getMatrix(){
        return matrix;
    }
    //Отримання і-того рядка матриці
    public double[] getRow(int i){
        if(i<0||i>=this.matrix.length){
            throw new RuntimeException("Incorrect row index");
        }
        return this.matrix[i];
    }
    //Отримання j-того стовпця матриці
    public double[] getColumn(int j){
        if(j<0||j>=this.matrix[0].length){
            throw new RuntimeException("Incorrect column index");
        }
        double[] column=new double[matrix.length];
        for(int i=0;i<this.matrix.length;++i){
            column[i]=this.matrix[i][j];
        }
        return column;
    }
    //Отримання елемента arr[i][j]
    public double get(int i,int j){
        if(j<0||j>=this.matrix[0].length||i<0||i>=this.matrix.length){
            throw new RuntimeException("Incorrect get index");
        }
        return this.matrix[i][j];
    }
    //Отримання кількості рядків матриці
    public int getNrows(){
        return this.matrix.length;
    }
    //Отримання кількості стовців(довжини рядка) матриці
    public int getNcols(){
        return this.matrix[0].length;
    }
    //Статичний метод, що створює діагональну матрицю
    public static Matrix diagonalMatrix(double[] a){
        Matrix res=new Matrix(a.length,a.length);
        for(int i=0;i<a.length;++i){
            res.matrix[i][i]=a[i];
        }
        return res;
    }
    //Статичний метод, що створює одиничну
    public static Matrix identityMatrix(int size){
        double[] a=new double[size];
        Arrays.fill(a,1);
        return diagonalMatrix(a);
    }
    //Статичний метод, що створює матрицю-рядок з випадковими значеннями
    public static Matrix matrixRow(int size){
        Matrix res=new Matrix(1,size);
        Random rand=new Random();
        for(int i=0;i<size;++i){
            res.matrix[0][i]=Math.floor(rand.nextDouble()*10000) / 100;;
        }
        return res;
    }
    //Статичний метод, що створює матрицю-стовпець з випадковими значеннями
    public static Matrix matrixColumn(int size){
        Matrix res=new Matrix(size,1);
        Random rand=new Random();
        for(int i=0;i<size;++i){
            res.matrix[i][0]=Math.floor(rand.nextDouble()*10000) / 100;;
        }
        return res;
    }
    //Фукнція додавання матриць(за визначенням-додавання відповідних елементів c[i][j]=a[i][j]+b[i][j])
    public Matrix add(Matrix other){
        if(this.matrix.length!=other.matrix.length||
                this.matrix[0].length!=other.matrix[0].length){
            throw new RuntimeException("These are matrices of different dimensions");
        }
        Matrix res=new Matrix(this);
        for(int i=0;i<res.matrix.length;++i){
            for(int j=0;j<res.matrix[0].length;++j){
                res.matrix[i][j]+=other.matrix[i][j];
            }
        }
        return res;
    }
    //Фукнція додавання матриць(за визначенням сума вигляду c[i][j]=Sum(r->n){a[i][r]*b[r][j]})
    public Matrix multiplication(Matrix other){
        if(this.matrix[0].length!=other.matrix.length){
            throw new RuntimeException("Matrices of this dimension cannot be multiplied");
        }
        Matrix res=new Matrix(this.matrix.length,other.matrix[0].length);
        for(int i=0;i<res.getNrows();++i){
            for(int j=0;j<res.getNcols();++j){
                res.matrix[i][j]=0;
                for(int r=0;r<this.matrix[0].length;++r){
                    res.matrix[i][j]+=this.matrix[i][r]*other.matrix[r][j];
                }
            }
        }
        return res;
    }
    //Фукнція множення матриці на скаляр(за визначенням-c[i][j]=a[i][j]*k)
    public Matrix multiplication(double a){
        Matrix res=new Matrix(this);
        for(int i=0;i<res.matrix.length;++i){
            for(int j=0;j<res.matrix[0].length;++j){
                res.matrix[i][j]*=a;
            }
        }
        return res;
    }
    //Функція транспонування матриці(за визначенням b[i][j]=a[j][i])
    public Matrix transpose(){
        Matrix res=new Matrix(this.matrix[0].length,this.matrix.length);
        for(int i=0;i<res.matrix.length;++i){
            for(int j=0;j<res.matrix[0].length;++j){
                res.matrix[i][j]=this.matrix[j][i];
            }
        }
        return res;
    }
    //Перевірка чи матриця квадратна
    private boolean isSquare(){
        return matrix.length==matrix[0].length;
    }
    //Обчислення визначника матриці(за рангу матриці 1 та 2 за спрощеними формулами та за іншого за домопогою мінорів)
    public double determinant() {
        if (!isSquare())
            throw new RuntimeException("Matrix need to be square.");
        if (matrix.length==1) {
            return matrix[0][0];
        }
        if (matrix.length==2) {
            return (matrix[0][0] * matrix[1][1] ) -
                    (matrix[0][1]  * matrix[1][0] );
        }
        double sum = 0.0;
        for (int i=0; i<this.getNcols(); i++) {
            sum += Math.pow(-1,i) * matrix[0][i] *
                    createSubMatrix( 0, i).determinant();
        }
        return sum;
    }
    //Мінор(матриця рангу n-1)
    private Matrix createSubMatrix(int excluding_row, int excluding_col) {
        Matrix mat = new Matrix(getNrows()-1, getNcols()-1);
        int r = -1;
        for (int i=0;i<getNrows();i++) {
            if (i==excluding_row)
                continue;
            r++;
            int c = -1;
            for (int j=0;j<getNcols();j++) {
                if (j==excluding_col)
                    continue;
                mat.setValueAt(r, ++c, matrix[i][j]);
            }
        }
        return mat;
    }
    //Союзна матриця
    public Matrix cofactor() {
        Matrix mat = new Matrix(getNrows(), getNcols());
        for (int i=0;i<getNrows();i++) {
            for (int j=0; j<getNcols();j++) {
                mat.setValueAt(i, j, Math.pow(-1,i+j)*
                        this.createSubMatrix(i, j).determinant());
            }
        }
        return mat;
    }
    //Обернена матриця(союзна транспонована матриця поділена на визначник)
    public Matrix inverse() {
        Matrix b=this.cofactor();
        b=b.transpose();
        return (b.multiplication(1.0/determinant()));
    }
    //Перевизначені методи перевірки ідентичності та отримання хеш-коду
    @Override
    public boolean equals(Object o){
        Matrix other=(Matrix) o;
        if (this.getClass().equals(o.getClass())){
            if (Arrays.deepEquals(this.matrix,other.matrix) == true){
                System.out.println("Equal");
                return true;
            }else {
                System.out.println("Classes are the same, but entries are different");
                return false;
            }
        } else if (Arrays.deepEquals(this.matrix,other.matrix) == true) {
            System.out.println("Different classes, but entries are the same");
            return false;
        }else {
            System.out.println("Not equal");
            return false;
        }
    }
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.matrix);
    }
    //Друк матриці в консоль для дебагу
    public void print(){
        for(int i=0;i<getNrows();++i){
            for(int j=0;j<getNcols();++j){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.print("\n");
        }
    }
}
