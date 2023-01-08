import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void fillWithValues() {
        Matrix a = new Matrix(2, 3);
        double[][] data = {{1, 2, 4}, {3, 4, 5}};
        a.fillWithValues(data);
        System.out.println("Fill with values 'A'");
        a.print();
        System.out.println(a.hashCode());
        Matrix b = new Matrix(a);
        //b.setValueAt(0,0,5);
        System.out.println("");
        System.out.println("Fill with values 'B'");
        b.print();
        System.out.println(b.hashCode());
        System.out.println();
        ImmutableMatrix c = new ImmutableMatrix(b);
        //c.setValueAt(0,0,7);
        System.out.println("Fill with values 'C'");
        c.print();
        System.out.println(c.hashCode());
        System.out.println("");
        assertEquals(a, b);

    }
    @Test
    void setValueAt() {
        Matrix a = new Matrix(2, 2);
        a.setValueAt(0, 0, 7);
        System.out.println("Set value at [0,0]");
        a.print();
        assertEquals(a.get(0, 0), 7);
    }

    @Test
    void getRow() {
        Matrix a = new Matrix(2, 2);
        a.setValueAt(0, 0, 4);
        a.setValueAt(0, 1, 2);
        System.out.println("Get row 0");
        double[] row = a.getRow(0);
        for (int j = 0; j < row.length; ++j) {
            System.out.print(row[j] + " ");
        }
        System.out.println();
        assertArrayEquals(row, new double[]{4, 2});
    }

    @Test
    void getColumn() {
        Matrix a = new Matrix(2, 2);
        a.setValueAt(0, 0, 4);
        a.setValueAt(1, 0, 2);
        System.out.println("Get column 0");
        double[] column = a.getColumn(0);
        for (int j = 0; j < column.length; ++j) {
            System.out.print(column[j] + "\n");
        }
        System.out.println();
        assertArrayEquals(column, new double[]{4, 2});
    }

    @Test
    void get() {
        Matrix a = new Matrix(2, 2);
        a.setValueAt(0, 0, 4);
        assertEquals(a.get(0, 0), 4);
    }

    @Test
    void getNrows() {
        Matrix a = new Matrix(4, 3);
        assertEquals(a.getNrows(), 4);
    }

    @Test
    void getNcols() {
        Matrix a = new Matrix(4, 3);
        assertEquals(a.getNcols(), 3);
    }

    @Test
    void diagonalMatrix() {
        Matrix diag = Matrix.diagonalMatrix(new double[]{2.3, 4.6, 5.1});
        System.out.println("Diagonal matrix");
        diag.print();
        assertEquals(diag.get(0, 0), 2.3);
        assertEquals(diag.get(1, 1), 4.6);
        assertEquals(diag.get(2, 2), 5.1);
    }

    @Test
    void identityMatrix() {
        Matrix diag = Matrix.identityMatrix(4);
        System.out.println("Identity matrix");
        diag.print();
        assertEquals(diag.get(0, 0), 1);
        assertEquals(diag.get(1, 1), 1);
        assertEquals(diag.get(0, 1), 0);
        assertEquals(diag.get(1, 0), 0);

    }

    @Test
    void matrixRow() {
        Matrix matrRow = Matrix.matrixRow(4);
        System.out.println("Matrix-row");
        matrRow.print();
        assertEquals(matrRow.getNrows(), 1);
        assertEquals(matrRow.getNcols(), 4);

    }

    @Test
    void matrixColumn() {
        Matrix matrCol = Matrix.matrixColumn(7);
        System.out.println("Matrix-column");
        matrCol.print();
        assertEquals(matrCol.getNrows(), 7);
        assertEquals(matrCol.getNcols(), 1);
    }

    @Test
    void add() {
        System.out.println("Add(C=A+B)");
        Matrix a = new Matrix(2, 1);
        Matrix b = new Matrix(2, 1);
        Matrix c = new Matrix(2, 2);
        a.fillWithValues(new double[][]{{1.2}, {3}});
        b.fillWithValues(new double[][]{{6.2}, {-8}});
        System.out.println("Matrix A");
        a.print();
        System.out.println("Matrix B");
        b.print();
        Matrix d = a.add(b);
        System.out.println("Matrix C");
        d.print();
        assertEquals(d.get(0, 0), 7.4);
        assertEquals(d.get(1, 0), -5);
        assertThrows(RuntimeException.class, () -> {
            a.add(c);
        });
    }

    @Test
    void multiplication() {
        System.out.println("Multiplication");
        Matrix a = new Matrix(2, 2);
        a.setValueAt(0, 0, 4);
        a.setValueAt(0, 1, 2);
        a.setValueAt(1, 0, -1);
        System.out.println("Matrix A");
        a.print();
        a = a.multiplication(3.5);
        System.out.println("Matrix A*3.5");
        a.print();
        assertEquals(a.get(0, 0), 14);
        assertEquals(a.get(0, 1), 7);
        assertEquals(a.get(1, 0), -3.5);
        assertEquals(a.get(1, 1), 0);
        a = a.multiplication(1 / 3.5);
        Matrix b = new Matrix(new double[][]{{1}, {-6}});
        System.out.println("Matrix B");
        b.print();
        Matrix c = a.multiplication(b);
        System.out.println("Matrix C=A*B");
        c.print();
        Matrix d = new Matrix(new double[][]{{-8}, {-1}});
        assertEquals(c, d);

    }

    @Test
    void transpose() {
        System.out.println("Transpose");
        Matrix a = new Matrix(2, 2);
        a.setValueAt(0, 0, 4);
        a.setValueAt(0, 1, 2);
        a.setValueAt(1, 0, -1);
        System.out.println("Matrix A");
        a.print();
        a = a.transpose();
        System.out.println("Matrix AT");
        a.print();
        assertEquals(a.get(0, 0), 4);
        assertEquals(a.get(0, 1), -1);
        assertEquals(a.get(1, 0), 2);
        assertEquals(a.get(1, 1), 0);
    }

    @Test
    void determinant() {
        System.out.println("Determinant");
        Matrix a = new Matrix(2, 2);
        a.setValueAt(0, 0, 4);
        a.setValueAt(0, 1, 2);
        a.setValueAt(1, 0, -1);
        a.setValueAt(1, 1, 1);
        Matrix b = new Matrix(4, 4);
        double[][] arr = new double[][]{
                {1, 2, 3, 4},
                {4, 3, 2, 1},
                {5, 6, 35, 8},
                {34, -1, 3, 5}
        };
        b.fillWithValues(arr);
        System.out.println("Matrix B");
        b.print();
        System.out.println("det(B)=" + b.determinant());
        System.out.println("Matrix A");
        a.print();
        System.out.println("det(B)=" + a.determinant());
        assertEquals(a.determinant(), 6);
        assertEquals(b.determinant(), -10640);
    }

    @Test
    void inverse() {
        System.out.println("Inverse");
        double[][] arrRaw = new double[][]{
                {3, 4},
                {5, 7},
        };
        Matrix a = new Matrix(arrRaw);
        System.out.println("Matrix A");
        a.print();
        System.out.println("Matrix B=A^(-1)");
        //Обернена матриця позначається як степінь -1
        Matrix b = a.inverse();
        b.print();
        double[][] arr = new double[][]{
                {7, -4},
                {-5, 3},
        };
        assertTrue(Arrays.deepEquals(b.matrix, arr));
        arrRaw = new double[][]{
                {1,1,1,1,1},
                {6, 0, 4, 1, 1},
                {5, -2, -3, 1 ,1},
                {-6, 3, -4, 1, 1},
                {6, 3, 0, 1, 0}
        };
        a = new Matrix(arrRaw);
        arr = new double[][]{
                {1, -1, 1},
                {-38, 41, -34},
                {27, -29, 24}
        };
        System.out.println("Matrix A");
        a.print();
        System.out.println("Matrix B=A^(-1)");
        b = a.inverse();
        b.print();
        //assertTrue(Arrays.deepEquals(b.matrix, arr));

    }

    @Test
    void cofactor(){
        double[][] arrRaw = new double[][]{
                {2, 5, 7},
                {6, 3, 4},
                {5, -2, -3}
        };
        Matrix a = new Matrix(arrRaw);
        System.out.println("Matrix A");
        a.print();
        System.out.println("Cofactor Matrix");
        Matrix b = a.cofactor();
        b.print();

    }


    @Test
    void testEquals() {
        double[][] arrRaw = new double[][]{
                {3, 4},
                {5, 7},
        };
        Matrix a = new Matrix(arrRaw);
        arrRaw = new double[][]{
                {3, 4},
                {5, 7},
        };
        ImmutableMatrix b = new ImmutableMatrix(arrRaw);
        b.setValueAt(0, 0, 57);
        assertEquals(b, a);

    }

    @Test
    void testUpperTriangle() {
        System.out.println("Upper Triangle");
        double[][] arrRaw = new double[][]{
                {2, 4},
                {5, 7},
        };
        MutableMatrix a = new MutableMatrix(arrRaw);
        System.out.println("Matrix A");
        a.print();
        a.transformationIntoUpperTriangular();
        System.out.println("Matrix A(triangle)");
        a.print();
        assertTrue(Arrays.deepEquals(a.matrix, new double[][]{
                {2, 4},
                {0, -3}
        }));

    }

    @Test
    void testLowerTriangle() {
        System.out.println("Lower Triangle");
        double[][] arrRaw = new double[][]{
                {2, 1},
                {5, 2},
        };
        MutableMatrix a = new MutableMatrix(arrRaw);
        System.out.println("Matrix A");
        a.print();
        a.transformationIntoLowerTriangular();
        System.out.println("Matrix A(triangle)");
        a.print();
        assertTrue(Arrays.deepEquals(a.matrix, new double[][]{
                {-0.5, 0},
                {5, 2}
        }));
    }

    @Test
    void testGeneric() {
        System.out.println("Generic matrix");
        Integer[][] iArr = new Integer[][]{
                {2, 1},
                {5, 2},
        };
        Double[][] dArr = new Double[][]{
                {3.2, 1.4},
                {5.73, -2.05},
        };
        System.out.println("Integers");
        GenericMatrix<Integer> integerMatrix = new GenericMatrix<Integer>(iArr);
        integerMatrix.print();
        System.out.println("Doubles");
        GenericMatrix<Double> doubleMatrix = new GenericMatrix<Double>(dArr);
        doubleMatrix.print();

    }

    @Test
    void hashcode(){
        Matrix a = new Matrix(2, 2);
        double[][] data = {{1, 6}, {3, 4}};
        a.fillWithValues(data);
        a.print();
        int b=a.hashCode();
        System.out.println("Hash code:" + b);

    }

}