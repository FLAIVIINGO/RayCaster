package org.example;

public class MatrixOperations {

    public double[][] multiplyMatrices(double[][] m1, double[][] m2) {
        double[][] result = new double[m1.length][m2[0].length];
        for(int i = 0; i < m1.length; i++) {
            for(int j = 0; j < m2[0].length; j++) {
                result[i][j] = multiplyMatricesCell(m1, m2, i, j);
            }
        }
        return result;
    }

    private double multiplyMatricesCell(double[][] m1, double[][] m2, int row, int col) {
        double cell = 0;
        for(int i = 0; i < m2.length; i++) {
            cell += m1[row][i] * m2[i][col];
        }
        return cell;
    }

    public Tuple multiplyMatrixByTuple(double[][] matrix, Tuple tuple) {
        Tuple result = new Tuple();
        for(int i = 0; i < matrix.length; i++) {
            result.setAtIndex(i, multiplyTupleMatrixCell(tuple, matrix, i));
        }
        return result;
    }

    private double multiplyTupleMatrixCell(Tuple tuple, double[][] matrix, int row) {
        double cell = 0;
        for(int i = 0; i < matrix.length; i++) {
            cell += matrix[row][i] * tuple.getAtIndex(i);
        }
        return cell;
    }
}
