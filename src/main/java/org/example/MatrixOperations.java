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

    public double[][] multiplyByIdentityMatrix(double[][] matrix) {
        double[][] identityMatrix =
                {{1,0,0,0},
                {0,1,0,0},
                {0,0,1,0},
                {0,0,0,1}};

        return multiplyMatrices(matrix, identityMatrix);
    }

    public double[][] transposeMatrix(double[][] matrix) {
        double[][] result = new double[matrix.length][matrix[0].length];
        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[0].length; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }


    public double[][] submatrix(double[][] matrix, int row, int column) {
        double[][] result = new double[matrix.length - 1][matrix[0].length - 1];
        int p = 0;
        for(int i = 0; i < matrix.length; i++) {
            if(i == row) continue;
            int q = 0;
            for(int j = 0; j < matrix[0].length; j++) {
                if(j == column) continue;
                result[p][q] = matrix[i][j];
                q++;
            }
            p++;
        }
        return result;
    }

    public double minor(double[][] matrix, int row, int column) {
        double[][] submtx = submatrix(matrix, row, column);
        return determinant(submtx);
    }

    public double cofactor(double[][] matrix, int row, int column) {
        double[][] smaller = new double[matrix.length - 1][matrix.length - 1];
        for (int i = 0; i < matrix.length; i++) {
            if (i == row) continue;
            for (int j = 0; j < matrix.length; j++) {
                if (j == column) continue;
                int smallerRow = i < row ? i : i - 1;
                int smallerCol = j < column ? j : j - 1;
                smaller[smallerRow][smallerCol] = matrix[i][j];
            }
        }
        return Math.pow(-1, row + column) * determinant(smaller);
    }

    public double determinant(double[][] matrix) {
        if(matrix.length == 2) {
            /*
             * | a b |
             * | c d |
             *  = ad - bc
             */
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        double det = 0;
        for(int column = 0; column < matrix.length; column++) {
            det += (matrix[0][column] * cofactor(matrix, 0, column));
        }
        return det;
    }
}
