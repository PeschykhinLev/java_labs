import java.util.Arrays;
//Незмінний клас(final)
public final class ImmutableMatrix extends Matrix{
    //Конструктори
    public ImmutableMatrix (){
        this.matrix=new double[1][1];
    }

    public ImmutableMatrix (int rows,int columns){
        this.matrix=new double[rows][columns];
    }

    public ImmutableMatrix (double[][] arr){
        this.matrix=arr.clone();
    }

    public ImmutableMatrix (Matrix a){
        this.matrix=a.matrix.clone();
    }
    //Перевизначаємо методи, щоб не мати конфлітів
    @Override
    public void fillWithValues(double[][] arr) {
    }
    @Override
    public void setValueAt(int i,int j,double n) {
    }
}
