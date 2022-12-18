import java.util.ArrayList;
import java.util.Arrays;

public class GenericMatrix <T> {
    protected T[][] matrix;

    public GenericMatrix(T[][] arr) {
        deepClone(arr);
    }
    private void deepClone(T[][] arr){
        matrix=arr.clone();
        for(int i=0;i<arr.length;++i){
            matrix[i]=arr[i].clone();
        }
    }

    public void setValueAt(int i, int j, T n) {
        matrix[i][j]=n;
    }

    public T[][] getMatrix() {
        return matrix;
    }

    public T[] getRow(int i){
        if(i<0||i>=this.matrix.length){
            throw new RuntimeException("Incorrect row index");
        }
        return matrix[i];
    }


    public T[] getColumn(int j){
        if(j<0||j>=this.matrix[0].length){
            throw new RuntimeException("Incorrect column index");
        }
        ArrayList<T> column=new ArrayList<T>();
        for(int i=0;i<this.matrix.length;++i){
            column.add(matrix[i][j]);
        }
        T[] columnArr=(T[])column.toArray();
        return columnArr;
    }

    public T get(int i, int j){
        if(j<0||j>=this.matrix[0].length||i<0||i>=this.matrix.length){
            throw new RuntimeException("Incorrect get index");
        }
        return matrix[i][j];
    }


    public int getNrows(){
        return matrix.length;
    }


    public int getNcols(){
        return matrix[0].length;
    }

    public void print(){
        for(int i=0;i<getNrows();++i){
            for(int j=0;j<getNcols();++j){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.print("\n");
        }
    }
}
